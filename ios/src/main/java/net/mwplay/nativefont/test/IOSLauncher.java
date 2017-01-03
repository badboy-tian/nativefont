package net.mwplay.nativefont.test;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import net.mwplay.nativefont.NativeFont;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;


public class IOSLauncher extends IOSApplication.Delegate implements /*SuperSdkOpenApiDelegate,*/ Handler {

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        FontTestGame game = new FontTestGame(this);
        NativeFont.setRobovm();

        return new IOSApplication(game, config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

    @Override
    public void didFinishLaunching(UIApplication application) {
        super.didFinishLaunching(application);
        System.out.println("didFinishLaunching.........");
    }

    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        /*if (launchOptions != null)
        SuperSdkOpenApi.sharedInstance().application(application, launchOptions.getDictionary());

        SuperSdkOpenApi.sharedInstance().initPlatformWithDeleagte(this, SuperSdkUrlType.SuperSdkUrlTypeDomestic);

        SuperSdkOpenApi.sharedInstance().initAdSDK();
        SuperSdkOpenApi.sharedInstance().trackEvent("ad_open", null);


        SuperSdkOpenApi.sharedInstance().OPSuperSdkSetShowSDKLog(true);*/


        return super.didFinishLaunching(application, launchOptions);
    }

    @Override
    public void willResignActive(UIApplication application) {
        super.willResignActive(application);
        //if (application != null)
        //SuperSdkOpenApi.sharedInstance().applicationWillResignActive(application);
    }

    @Override
    public void didBecomeActive(UIApplication application) {
        //super.didBecomeActive(application);
        //if (application != null){
        //    SuperSdkOpenApi.sharedInstance().applicationDidBecomeActive(application);
       // }
    }

    @Override
    public void didEnterBackground(UIApplication application) {
        super.didEnterBackground(application);
    }

    @Override
    public void willEnterForeground(UIApplication application) {
        super.willEnterForeground(application);
    }

    @Override
    public void willTerminate(UIApplication application) {
        super.willTerminate(application);
    }


    @Override
    public void login() {
    }
}