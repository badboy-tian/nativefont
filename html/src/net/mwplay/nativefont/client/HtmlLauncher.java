package net.mwplay.nativefont.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;

import net.mwplay.nativefont.NativeFontListener;
import net.mwplay.nativefont.NativeFontPaint;
import net.mwplay.nativefont.test.FontTestGame;

public class HtmlLauncher extends GwtApplication implements NativeFontListener {

    @Override
    public GwtApplicationConfiguration getConfig() {
        return new GwtApplicationConfiguration(480, 800);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new FontTestGame();
    }

    @Override
    public Pixmap getFontPixmap(String str, NativeFontPaint freePaint) {
        return null;
    }
}