package net.mwplay.nativefont.test;

import net.mwplay.nativefont.NativeFont;
import net.mwplay.nativefont.NativeFontListener;

/**
 * Created by tian on 2016/10/2.
 */

public class FontTestGame extends net.mwplay.nativefont.NativeGame {

    public FontTestGame(NativeFontListener fontListener) {
        super(fontListener);
    }

    @Override
    public void create() {
        super.create();

        NativeFont font50 = new NativeFont(fontListener, new net.mwplay.nativefont.NativeFontPaint(50));
        font50.appendText(DEFAULT_CHARS);
        fonts.put("font50", font50);

        setScreen(new FontTest(this));
    }
}
