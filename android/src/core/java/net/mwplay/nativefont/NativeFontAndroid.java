package net.mwplay.nativefont;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by tian on 2016/10/2.
 */

public class NativeFontAndroid implements NativeFontListener {
    @Override
    public Pixmap getFontPixmap(String txt, net.mwplay.nativefont.NativeFontPaint vpaint) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize((float) vpaint.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        int w = (int) paint.measureText(txt);
        int h = (int) (fm.descent - fm.ascent);
        if (w == 0) {
            h = vpaint.getTextSize();
            w = h;
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (vpaint.getStrokeColor() != null) {
            paint.setColor(getColor(vpaint.getStrokeColor()));
            paint.setStrokeWidth((float) vpaint.getStrokeWidth());
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setFakeBoldText(true);
            canvas.drawText(txt, 0.0f, -fm.ascent, paint);
            paint.setFakeBoldText(false);
        } else {
            paint.setUnderlineText(vpaint.getUnderlineText());
            paint.setStrikeThruText(vpaint.getStrikeThruText());
            paint.setFakeBoldText(vpaint.getFakeBoldText());
        }
        paint.setStrokeWidth(0.0f);
        paint.setColor(getColor(vpaint.getColor()));
        canvas.drawText(txt, 0.0f, -fm.ascent, paint);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, buffer);
        byte[] encodedData = buffer.toByteArray();
        return new Pixmap(encodedData, 0, encodedData.length);
    }

    private int getColor(Color color) {
        return (((((int) (color.a * 255.0f)) << 24) | (((int) (color.r * 255.0f)) << 16)) | (((int) (color.g * 255.0f)) << 8)) | ((int) (color.b * 255.0f));
    }
}
