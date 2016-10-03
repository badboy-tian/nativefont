package net.mwplay.nativefont;


import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;

import net.mwplay.nativefont.test.FontTestGame;

import org.moe.natj.general.Pointer;

import apple.uikit.c.UIKit;


public class IOSMoeLauncher extends NativeFontIOS  {

    protected IOSMoeLauncher(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        super.createApplication();
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = false;

        return new IOSApplication(new FontTestGame(this), config);
    }

    public static void main(String[] argv) {
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
    }
}
