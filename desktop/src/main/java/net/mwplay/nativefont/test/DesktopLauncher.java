package net.mwplay.nativefont.test;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class DesktopLauncher implements Handler{
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 800;


        DesktopLauncher desktopLauncher = new DesktopLauncher();

        new LwjglApplication(new FontTestGame(desktopLauncher), config);
    }

    @Override
    public void login() {

    }
}
