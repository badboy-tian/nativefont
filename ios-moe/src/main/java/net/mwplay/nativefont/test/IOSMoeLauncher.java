package net.mwplay.nativefont.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Mapped;
import org.moe.natj.objc.map.ObjCObjectMapper;

import apple.foundation.NSDictionary;
import apple.foundation.NSURL;
import apple.uikit.UIApplication;
import apple.uikit.c.UIKit;

public class IOSMoeLauncher extends IOSApplication.Delegate implements /*SuperSdkOpenApiDelegate,*/ Handler {

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
        /*boolean value = super.applicationDidFinishLaunchingWithOptions(application, launchOptions);
        SuperSdkOpenApi.sharedInstance().applicationDidFinishLaunchingWithOptions(application, launchOptions);
        SuperSdkOpenApi.sharedInstance().initPlatformWithDeleagteWithSuperSdkUrlType(this, SuperSdkUrlType.SuperSdkUrlTypeDomestic);
        SuperSdkOpenApi.sharedInstance().initAdSDK();*/
        return true;
    }

    public static void main(String[] argv) {
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
    }

    /*@Override
    public void OPInitFinishWithParam(int i, @Mapped(ObjCObjectMapper.class) Object o) {
        System.out.println("OPInitFinishWithParam " + i);
    }

    @Override
    public void OPLoginPlatformWithParam(int i, @Mapped(ObjCObjectMapper.class) Object o) {
        System.out.println("OPLoginPlatformWithParam " + i);
    }*/

    @Override
    public void login() {
        //SuperSdkOpenApi.sharedInstance().showLoginMode2();
    }

    @Override
    public void applicationWillResignActive(UIApplication application) {
        super.applicationWillResignActive(application);
        //SuperSdkOpenApi.sharedInstance().applicationWillResignActive(application);
    }

    @Override
    public void applicationWillTerminate(UIApplication application) {
        super.applicationWillTerminate(application);
        //SuperSdkOpenApi.sharedInstance().applicationWillTerminate(application);
    }

    @Override
    public boolean applicationHandleOpenURL(UIApplication application, NSURL url) {
        //return SuperSdkOpenApi.sharedInstance().applicationOpenURLSourceApplicationAnnotation(application, url, null, null);
        return super.applicationHandleOpenURL(application, url);
    }

    @Override
    public boolean applicationOpenURLSourceApplicationAnnotation(UIApplication application, NSURL url, String sourceApplication, @Mapped(ObjCObjectMapper.class) Object annotation) {
        //return SuperSdkOpenApi.sharedInstance().applicationOpenURLSourceApplicationAnnotation(application, url, sourceApplication, annotation);
        return super.applicationOpenURLSourceApplicationAnnotation(application, url, sourceApplication, annotation);
    }

    @Override
    public boolean applicationOpenURLOptions(UIApplication app, NSURL url, NSDictionary<String, ?> options) {
        Gdx.app.log("xxxxx", "applicationOpenURLOptions: " + url.toString() + " options: " + options.description());
        //UIApplicationOpenURLOptionsSourceApplicationKey
        //UIApplicationOpenURLOptionsOpenInPlaceKey
        //return SuperSdkOpenApi.sharedInstance().applicationOpenURLSourceApplicationAnnotation(app, url, (String)options.objectForKey(UIKit.UIApplicationOpenURLOptionsSourceApplicationKey()), null);
        return super.applicationOpenURLOptions(app, url, options);
    }


    @Override
    public void applicationDidBecomeActive(UIApplication application) {
        super.applicationDidBecomeActive(application);
        //SuperSdkOpenApi.sharedInstance().applicationDidBecomeActive(application);
    }

    @Override
    public void applicationDidEnterBackground(UIApplication application) {
        //SuperSdkOpenApi.sharedInstance().applicationDidEnterBackground(application);
    }

    @Override
    public void applicationWillEnterForeground(UIApplication application) {
        super.applicationWillEnterForeground(application);
        //SuperSdkOpenApi.sharedInstance().applicationWillEnterForeground(application);
    }


}


