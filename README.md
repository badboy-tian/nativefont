[![](https://jitpack.io/v/tianqiujie/nativefont.svg)](https://jitpack.io/#tianqiujie/nativefont)
##Welcome to NativeFont
NativeFont is an custom bitmapfont library that is used to create freedom Labels.

####Welcome to join us !!!

## About Nativefont

Make Native bitmapFont from Native code. Android Bitmap or IOS UILabel or Desktop Graphics2D

##Features

- [x] No External ttf
- [x] Multi-language
- [x] Cross-platform
- [x] Convenience
- [x] Open Source

## Requirements

* Libgdx 1.6+
* Robovm fork 2.0+
* MOE 1.1+

## Usage
```
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```


```
dependencies {
	        compile 'com.github.tianqiujie.nativefont:2.2.9'
	}
```
In Robovm
```
@Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        FontTestGame game = new FontTestGame();
        //important!!!
        NativeFont.setRobovm();

        return new IOSApplication(game, config);
    }
```

Please see the test/[FontTest.java](core/src/main/java/net/mwplay/nativefont/test/FontTest.java)
```
NativeFont font50 = new NativeFont(new NativeFontPaint(50));
        font50.appendText(DEFAULT_CHARS);
        fonts.put("font50", font50);
```
```
label = new NativeLabel("", game.fonts.get("font50"), Color.BLACK);
        label.setText(text);
        label.setPosition(pos.x, pos.y);
        stage.addActor(label);
```
If you use the jdk1.8, or android studio 2.2+. You should add some code in android/build.gradle
```
defaultConfig {
        applicationId "com.yuyointeractive.jiangximahjong"
        minSdkVersion 8
        targetSdkVersion 24
        jackOptions {
            enabled true
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```
## ScreenShot

###Android:
![Android](doc/android.jpg)
###IOS:
![IOS](doc/ios.jpg)
###Desktop:
![Desktop](doc/desktop.jpg)

## Thanks: 
@Var3d: 
          FreeFont: [http://blog.csdn.net/aijiuziji/article/details/48074193](http://blog.csdn.net/aijiuziji/article/details/48074193)
          Var3d: [http://var3d.net/](http://var3d.net/)    
        
## License

NativeFont is licensed under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0.html), meaning you can use it free of charge, without strings attached in commercial and non-commercial projects. 
