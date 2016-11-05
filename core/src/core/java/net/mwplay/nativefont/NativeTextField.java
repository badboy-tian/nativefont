package net.mwplay.nativefont;

/**
 * Created by tian on 2016/11/5.
 */

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/** 输入框 */
public class NativeTextField extends TextField {
    static private final char BACKSPACE = 8;
    static private final char TAB = '\t';
    NativeTextField field;
    boolean passwordMode;

    public NativeTextField (String text, TextFieldStyle style) {
        super(append(text, style), style);
        setOnlyFontChars(false);
        field = this;
        ClickListener appendListener = new ClickListener() {
            public boolean keyTyped (InputEvent event, char character) {
                if (isDisabled()) return false;
                switch (character) {
                    case BACKSPACE:
                    case TAB:
                    case ENTER_ANDROID:
                    case ENTER_DESKTOP:
                        break;
                    default:
                        if (character < 32) return false;
                }

                Stage stage = getStage();
                if (stage == null || stage.getKeyboardFocus() != NativeTextField.this) return false;
                if (character == TAB || character == ENTER_ANDROID) {
                } else {
                    boolean enter = character == ENTER_DESKTOP || character == ENTER_ANDROID;
                    boolean add = enter ? writeEnters : true;
                    if (add) {
                        append(getText(), getStyle());
                        setPasswordMode(field.isPasswordMode());
                    }
                }
                return true;
            }
        };
        addListener(appendListener);
    }

    public void setPasswordCharacter (String passwordCharacter) {
        append(passwordCharacter, getStyle());
        super.setPasswordCharacter(passwordCharacter.charAt(0));
        super.setPasswordMode(true);
    }

    public void setMessageText (String messageText) {
        super.setMessageText(append(messageText, getStyle()));
    }

    private static String append (String text, TextFieldStyle style) {
        ((NativeFont)style.font).appendText(text);
        return text;
    }

    public void appendText (String str) {
        super.appendText(append(str, getStyle()));
    }

    public void setText (String str) {
        super.setText(append(str, getStyle()));
    }

}