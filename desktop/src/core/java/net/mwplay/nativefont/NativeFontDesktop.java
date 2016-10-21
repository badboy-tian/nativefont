package net.mwplay.nativefont;

import com.badlogic.gdx.graphics.Pixmap;

import net.mwplay.nativefont.NativeFontListener;
import net.mwplay.nativefont.NativeFontPaint;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import static javax.swing.UIManager.getColor;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeFontDesktop implements NativeFontListener {
    Map<Integer, FontMetrics> metrics = new HashMap<Integer, FontMetrics>();
    Map<Integer, Font> fonts = new HashMap<Integer, Font>();
    private AttributedString as;

    @Override
    public Pixmap getFontPixmap(String txt, NativeFontPaint freePaint) {
        int textSize = freePaint.getTextSize();
        boolean z = freePaint.getFakeBoldText() || freePaint.getStrokeColor() != null;
        Font font = getFont(textSize, z);
        FontMetrics fm = this.metrics.get(freePaint.getTextSize());
        int strWidth = fm.stringWidth(txt);
        int strHeight = fm.getAscent() + fm.getDescent();
        if (strWidth == 0) {
            strHeight = freePaint.getTextSize();
            strWidth = strHeight;
        }
        BufferedImage bi = new BufferedImage(strWidth, strHeight, 6);
        Graphics2D g = bi.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);
        if (freePaint.getStrokeColor() != null) {
            Shape shape = font.createGlyphVector(fm.getFontRenderContext(), txt).getOutline();
            g.setColor(getColor(freePaint.getColor()));
            g.translate(0, fm.getAscent());
            g.fill(shape);
            g.setStroke(new BasicStroke((float) freePaint.getStrokeWidth()));
            g.setColor(getColor(freePaint.getStrokeColor()));
            g.draw(shape);
        } else if (freePaint.getUnderlineText()) {
            as = new AttributedString(txt);
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            g.setColor(getColor(freePaint.getColor()));
            g.drawString(as.getIterator(), 0, fm.getAscent());
        } else if (freePaint.getStrikeThruText()) {
            as = new AttributedString(txt);
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            g.setColor(getColor(freePaint.getColor()));
            g.drawString(as.getIterator(), 0, fm.getAscent());
        } else {
            g.setColor(getColor(freePaint.getColor()));
            g.drawString(txt, 0, fm.getAscent());
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Pixmap(buffer.toByteArray(), 0, buffer.toByteArray().length);
    }

    private Font getFont(int defaultFontSize, boolean isBolo) {
        Font font = this.fonts.get(defaultFontSize);
        if (font == null) {
            font = new Font("", isBolo ? 1 : 0, defaultFontSize);
            this.fonts.put(defaultFontSize, font);
            Graphics2D g = new BufferedImage(1, 1, 6).createGraphics();
            g.setFont(font);
            this.metrics.put(defaultFontSize, g.getFontMetrics());
        }
        return font;
    }

}
