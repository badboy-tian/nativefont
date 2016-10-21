package net.mwplay.nativefont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSMutableAttributedString;
import org.robovm.apple.foundation.NSNumber;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.NSAttributedStringAttribute;
import org.robovm.apple.uikit.NSUnderlineStyle;
import org.robovm.apple.uikit.UIColor;
import org.robovm.apple.uikit.UIFont;
import org.robovm.apple.uikit.UIGraphics;
import org.robovm.apple.uikit.UIImage;
import org.robovm.apple.uikit.UILabel;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeFontIOS implements NativeFontListener {

    private UIColor getColor(Color color) {
        return UIColor.fromRGBA((double) color.r, (double) color.g, (double) color.b, (double) color.a);
    }

    @Override
    public Pixmap getFontPixmap(String strings, NativeFontPaint vpaint) {
        UIFont font;
        if (vpaint.getFakeBoldText() || vpaint.getStrokeColor() != null) {
            font = UIFont.getBoldSystemFont((double) vpaint.getTextSize());
        } else {
            font = UIFont.getSystemFont((double) vpaint.getTextSize());
        }

        CGSize dim = new NSString(strings).getSize(font);
        UILabel label = new UILabel(new CGRect(0.0d, 0.0d, dim.getWidth(), dim.getHeight()));
        UILabel label2 = null;
        label.setText(strings);
        label.setBackgroundColor(UIColor.fromRGBA(1.0d, 1.0d, 1.0d, 0.0d));
        label.setTextColor(getColor(vpaint.getColor()));
        label.setFont(font);
        label.setOpaque(false);
        label.setAlpha(1.0d);
        NSRange nSRange = new NSRange(0, (long) strings.length());
        NSMutableAttributedString nSMutableAttributedString = new NSMutableAttributedString(strings);
        nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.ForegroundColor, getColor(vpaint.getColor()), nSRange);
        if (vpaint.getStrokeColor() != null) {
            UILabel uILabel = new UILabel(new CGRect(0.0d, 0.0d, dim.getWidth(), dim.getHeight()));
            uILabel.setText(strings);
            uILabel.setBackgroundColor(UIColor.fromRGBA(1.0d, 1.0d, 1.0d, 0.0d));
            uILabel.setTextColor(getColor(vpaint.getColor()));
            uILabel.setFont(font);
            uILabel.setOpaque(false);
            uILabel.setAlpha(1.0d);
            nSMutableAttributedString = new NSMutableAttributedString(strings);
            nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.StrokeColor, getColor(vpaint.getStrokeColor()), nSRange);
            nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.StrokeWidth, NSNumber.valueOf(vpaint.getStrokeWidth()), nSRange);
            uILabel.setAttributedText(nSMutableAttributedString);
        } else if (vpaint.getUnderlineText()) {
            nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.UnderlineStyle, NSNumber.valueOf(NSUnderlineStyle.StyleSingle.value()), nSRange);
        } else if (vpaint.getStrikeThruText()) {
            nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.StrikethroughStyle, NSNumber.valueOf(NSUnderlineStyle.StyleSingle.value() | NSUnderlineStyle.PatternSolid.value()), nSRange);
        }
        label.setAttributedText(nSMutableAttributedString);
        UIGraphics.beginImageContext(dim, false, 1.0d);
        label.getLayer().render(UIGraphics.getCurrentContext());
        if (vpaint.getStrokeColor() != null) {
            label2.getLayer().render(UIGraphics.getCurrentContext());
        }
        UIImage image = UIGraphics.getImageFromCurrentImageContext();
        UIGraphics.endImageContext();
        NSData nsData = image.toPNGData();
        return new Pixmap(nsData.getBytes(), 0, nsData.getBytes().length);
    }
}
