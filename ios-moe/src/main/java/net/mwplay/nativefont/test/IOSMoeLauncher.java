package net.mwplay.nativefont.test;

import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;

import org.moe.natj.general.Pointer;

import apple.foundation.NSDictionary;
import apple.uikit.UIApplication;
import apple.uikit.c.UIKit;


public class IOSMoeLauncher extends IOSApplication.Delegate implements Handler {


    protected IOSMoeLauncher(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = false;

        return new IOSApplication(new FontTestGame(this), config);
    }

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary<?, ?> launchOptions) {
        boolean value = super.applicationDidFinishLaunchingWithOptions(application, launchOptions);

        return value;
    }

    public static void main(String[] argv) {
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
    }

    @Override
    public void login() {

    }
}


