package net.mwplay.nativefont;

import com.badlogic.gdx.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeGame extends Game{
    protected NativeFontListener fontListener;
    private int fontSize = 30;
    public Map<String, net.mwplay.nativefont.NativeFont> fonts;
    public static final String DEFAULT_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*";

    public NativeGame(NativeFontListener fontListener){
        this.fontListener = fontListener;
    }

    @Override
    public void create() {
        if (fontListener != null){
            fonts = new HashMap<String, net.mwplay.nativefont.NativeFont>();
            net.mwplay.nativefont.NativeFont font = new net.mwplay.nativefont.NativeFont(fontListener, new net.mwplay.nativefont.NativeFontPaint(getDefaultFontSize()));
            font.appendText(DEFAULT_CHARS);
            this.fonts.put("font", font);
        }
    }

    public int getDefaultFontSize() {
        return this.fontSize;
    }
}
