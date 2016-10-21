package net.mwplay.nativefont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeLabel extends Label {

    private float[] dxs = new float[]{1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 0.0f, 0.0f};
    private float[] dys = new float[]{1.0f, -1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f};
    private boolean isStroke = false;
    private Color strokeColor;
    private float strokeWidth;


    public NativeLabel(CharSequence text, NativeFont font) {
        this(text, font, Color.WHITE);
    }

    public NativeLabel(CharSequence text, NativeFont font, Color color) {
        this(text, new LabelStyle(font, color));
    }

    public NativeLabel(CharSequence text, LabelStyle style) {
        super(append(text, style), style);
        setSize(getPrefWidth(), getPrefHeight());
        setColor(style.fontColor);
    }

    private static CharSequence append(CharSequence text, LabelStyle style) {
        ((NativeFont) style.font).appendText(text + "");
        return text;
    }

    @Override
    public void setText(CharSequence newText) {
        super.setText(append(newText, getStyle()));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.strokeColor = color.cpy();
    }

    public void setBold(float width) {
        setStroke(getColor().cpy(), width);
    }

    public void setStroke(Color strokeColor, float strokeWidth) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.isStroke = true;
    }

    public TextureRegion getRegion() {
        return getBitmapFontCache().getFont().getRegion();
    }

    @Override
    public void setFontScale(float fontScale) {
        super.setFontScale(fontScale);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.isStroke) {
            validate();
            for (int i = 0; i < this.dxs.length; i++) {
                getBitmapFontCache().tint(this.strokeColor);
                getBitmapFontCache().setPosition(getX() + (this.dxs[i] * this.strokeWidth), (getY() + (this.dys[i] * this.strokeWidth)) + this.strokeWidth);
                getBitmapFontCache().draw(batch, getColor().a);
            }
            getBitmapFontCache().tint(getColor());
            getBitmapFontCache().setPosition(getX(), getY() + this.strokeWidth);
            getBitmapFontCache().draw(batch);
            return;
        }

        super.draw(batch, parentAlpha);
    }

}
