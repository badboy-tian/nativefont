package net.mwplay.nativefont.test;

import com.badlogic.gdx.Game;

import net.mwplay.nativefont.NativeFont;
import net.mwplay.nativefont.NativeFontListener;
import net.mwplay.nativefont.NativeFontPaint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tian on 2016/10/2.
 */

public class FontTestGame extends Game {
    protected NativeFontListener fontListener;
    private int fontSize = 30;
    public Map<String, NativeFont> fonts = new HashMap<String, NativeFont>();
    public static final String DEFAULT_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*";

    public FontTestGame() {
    }


    public int getDefaultFontSize() {
        return this.fontSize;
    }


    @Override
    public void create() {
        NativeFont font = new NativeFont(new NativeFontPaint(getDefaultFontSize()));
        font.appendText(DEFAULT_CHARS);
        this.fonts.put("font", font);

        NativeFont font50 = new NativeFont(new NativeFontPaint(50));
        font50.appendText(DEFAULT_CHARS);
        fonts.put("font50", font50);

        NativeFont ttfFont = new NativeFont(new NativeFontPaint("test.ttf"));
        ttfFont.appendText(DEFAULT_CHARS);
        this.fonts.put("ttffont",ttfFont);

        setScreen(new FontTest(this));
    }
}
