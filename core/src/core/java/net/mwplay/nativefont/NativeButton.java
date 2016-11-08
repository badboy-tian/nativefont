package net.mwplay.nativefont;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;

/**
 * NativeButton
 * Created by tian on 2016/11/8.
 */

public class NativeButton extends Button{

    private final NativeLabel label;
    private TextButton.TextButtonStyle style;

    public NativeButton(String text, Skin skin) {
        this(text, skin.get(TextButton.TextButtonStyle.class));
        setSkin(skin);
    }

    public NativeButton(String text, Skin skin, String styleName) {
        this(text, skin.get(styleName, TextButton.TextButtonStyle.class));
        setSkin(skin);
    }

    public NativeButton(String text, TextButton.TextButtonStyle style) {
        super();
        setStyle(style);
        this.style = style;

        if (style.fontColor == null){
            style.fontColor = Color.WHITE;
        }
        label = new NativeLabel(text, new Label.LabelStyle(style.font, style.fontColor));
        label.setAlignment(Align.center);
        add(label).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle (ButtonStyle style) {
        if (style == null) throw new NullPointerException("style cannot be null");
        if (!(style instanceof TextButton.TextButtonStyle)) throw new IllegalArgumentException("style must be a TextButtonStyle.");
        super.setStyle(style);
        this.style = (TextButton.TextButtonStyle)style;
        if (label != null) {
            TextButton.TextButtonStyle textButtonStyle = (TextButton.TextButtonStyle)style;
            Label.LabelStyle labelStyle = label.getStyle();
            labelStyle.font = textButtonStyle.font;
            labelStyle.fontColor = textButtonStyle.fontColor;
            label.setStyle(labelStyle);
        }
    }

    public TextButton.TextButtonStyle getStyle () {
        return style;
    }

    public void draw (Batch batch, float parentAlpha) {
        Color fontColor;
        if (isDisabled() && style.disabledFontColor != null)
            fontColor = style.disabledFontColor;
        else if (isPressed() && style.downFontColor != null)
            fontColor = style.downFontColor;
        else if (isChecked() && style.checkedFontColor != null)
            fontColor = (isOver() && style.checkedOverFontColor != null) ? style.checkedOverFontColor : style.checkedFontColor;
        else if (isOver() && style.overFontColor != null)
            fontColor = style.overFontColor;
        else
            fontColor = style.fontColor;
        if (fontColor != null) label.getStyle().fontColor = fontColor;
        super.draw(batch, parentAlpha);
    }

    public Label getLabel () {
        return label;
    }

    public Cell<NativeLabel> getLabelCell () {
        return getCell(label);
    }

    public void setText (String text) {
        label.setText(text);
    }

    public CharSequence getText () {
        return label.getText();
    }

    /** The style for a text button, see {@link TextButton}.
     * @author Nathan Sweet */
    static public class TextButtonStyle extends ButtonStyle {
        public BitmapFont font;
        /** Optional. */
        public Color fontColor, downFontColor, overFontColor, checkedFontColor, checkedOverFontColor, disabledFontColor;

        public TextButtonStyle () {
        }

        public TextButtonStyle (Drawable up, Drawable down, Drawable checked, BitmapFont font) {
            super(up, down, checked);
            this.font = font;
        }

        public TextButtonStyle (TextButton.TextButtonStyle style) {
            super(style);
            this.font = style.font;
            if (style.fontColor != null) this.fontColor = new Color(style.fontColor);
            if (style.downFontColor != null) this.downFontColor = new Color(style.downFontColor);
            if (style.overFontColor != null) this.overFontColor = new Color(style.overFontColor);
            if (style.checkedFontColor != null) this.checkedFontColor = new Color(style.checkedFontColor);
            if (style.checkedOverFontColor != null) this.checkedFontColor = new Color(style.checkedOverFontColor);
            if (style.disabledFontColor != null) this.disabledFontColor = new Color(style.disabledFontColor);
        }
    }
}
