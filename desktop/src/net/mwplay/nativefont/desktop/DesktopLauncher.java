package net.mwplay.nativefont.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher extends NativeFontDesktop {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;

		DesktopLauncher desktopLauncher = new DesktopLauncher();
		new LwjglApplication(new net.mwplay.nativefont.test.FontTestGame(desktopLauncher), config);
	}
}
