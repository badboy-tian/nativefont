package net.mwplay.nativefont;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeFontPaint {
    private Color color = Color.WHITE;
    private boolean isFakeBoldText = false;
    private boolean isStrikeThruText = false;
    private boolean isUnderlineText = false;
    private Color strokeColor = null;
    private int strokeWidth = 3;
    private int textSize = 30;

    public String getName() {
        StringBuffer name = new StringBuffer();
        name.append(this.textSize);
        name.append("_");
        name.append(this.color.toIntBits());
        name.append("_");
        name.append(booleanToInt(this.isFakeBoldText));
        name.append("_");
        name.append(booleanToInt(this.isUnderlineText));
        if (this.strokeColor != null) {
            name.append("_");
            name.append(this.strokeColor.toIntBits());
            name.append("_");
            name.append(this.strokeWidth);
        }
        return name.toString();
    }

    private int booleanToInt(boolean b) {
        return b ? 0 : 1;
    }

    public NativeFontPaint() {
    }

    public NativeFontPaint(int textSize, Color color, Color stroke, int strokeWidth, boolean bold, boolean line, boolean thru) {
        this.textSize = textSize;
        this.color = color;
        this.strokeColor = stroke;
        this.strokeWidth = strokeWidth;
        this.isFakeBoldText = bold;
        this.isUnderlineText = line;
        this.isStrikeThruText = thru;
    }

    public NativeFontPaint(int size) {
        this.textSize = size;
    }

    public NativeFontPaint(Color color) {
        this.color = color;
    }

    public NativeFontPaint(int size, Color color) {
        this.textSize = size;
        this.color = color;
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getFakeBoldText() {
        return this.isFakeBoldText;
    }

    public void setFakeBoldText(boolean isFakeBoldText) {
        this.isFakeBoldText = isFakeBoldText;
    }

    public boolean getUnderlineText() {
        return this.isUnderlineText;
    }

    public void setUnderlineText(boolean isUnderlineText) {
        this.isUnderlineText = isUnderlineText;
    }

    public boolean getStrikeThruText() {
        return this.isStrikeThruText;
    }

    public void setStrikeThruText(boolean isStrikeThruText) {
        this.isStrikeThruText = isStrikeThruText;
    }

    public Color getStrokeColor() {
        return this.strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

}
