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

##Widget
* NativeLabel
* NativeButton
* NativeTextField
* NativeTextArea

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
	        compile 'com.github.tianqiujie.nativefont:2.4.3'
	}
```
In Moe
add ```-keep class net.mwplay.nativefont.** { *; }```in proguard.append.cfg

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
add ```<pattern>net.mwplay.nativefont.*</pattern> ```in robovm.xml.

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
        
NativeLabel nativeLabel = new NativeLabel("as", game.fonts.get("font"));
        nativeLabel.pos(300, 500).color(Color.BLACK).text("xxxxxxx").drag().enableTouch();
        nativeLabel.addTo(stage);
```

## ScreenShot

###Android:
![Android](doc/android.jpg)
###IOS:
![IOS](doc/ios.jpg)
###Desktop:
![Desktop](doc/desktop.jpg)

##Costum Font
####NativeFont can use a costum font by .ttf
1.Placed the .ttf file under the asset folder.

2.In Robovm,Under the ios project, open the info.plist.xml file,in < dict > write the following code behind.
```
    <key>UIAppFonts</key>
    <array>
        <string>your_file.ttf</string>
    </array>
```
3.Than you can use ,like this
```
   NativeFont ttfFont = new NativeFont(new NativeFontPaint("test.ttf"));
   NativeLabel label = new NativeLabel("Hello World",ttfFont,Color.BLACK);
```


## Thanks: 
@Var3d: 
          QQ: 348705644
	  Email: var3d@qq.com
          Var3d: [http://var3d.net/](http://var3d.net/)    
        
## License

NativeFont is licensed under the [Apache 2 License](http://www.apache.org/licenses/LICENSE-2.0.html), meaning you can use it free of charge, without strings attached in commercial and non-commercial projects. 
