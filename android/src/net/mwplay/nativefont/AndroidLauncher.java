package net.mwplay.nativefont;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends NativeFontAndroid {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new net.mwplay.nativefont.test.FontTestGame(this), config);
    }

}
