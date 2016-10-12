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

Core
```
dependencies {
	        compile 'com.github.tianqiujie.nativefont:core:2.1.2'
	}
```

Android
```
dependencies {
	        compile 'com.github.tianqiujie.nativefont:android:2.1.2'
	}
```   

Robovm
```
dependencies {
	        compile 'com.github.tianqiujie.nativefont:ios:2.1.2'
	}
```

Moe
```
dependencies {
	        compile 'com.github.tianqiujie.nativefont:ios-moe:2.1.2'
	}
```

Please see the test/[FontTest.java](core/src/net/mwplay/nativefont/test/FontTest.java)
```
NativeFont font50 = new NativeFont(fontListener, new NativeFontPaint(50));
        font50.appendText(DEFAULT_CHARS);
        fonts.put("font50", font50);
```
```
label = new NativeLabel("", new Label.LabelStyle(game.fonts.get("font50"), Color.BLACK));
        label.setText(text);
        label.setPosition(pos.x, pos.y);
        stage.addActor(label);
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
