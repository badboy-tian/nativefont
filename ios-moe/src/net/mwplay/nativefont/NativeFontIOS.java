package net.mwplay.nativefont;

import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import org.moe.natj.general.Pointer;

import apple.coregraphics.struct.CGPoint;
import apple.coregraphics.struct.CGRect;
import apple.coregraphics.struct.CGSize;
import apple.foundation.NSData;
import apple.foundation.NSMutableAttributedString;
import apple.foundation.NSNumber;
import apple.foundation.NSString;
import apple.foundation.struct.NSRange;
import apple.uikit.UIColor;
import apple.uikit.UIFont;
import apple.uikit.UIImage;
import apple.uikit.UILabel;
import apple.uikit.c.UIKit;
import apple.uikit.enums.NSUnderlineStyle;


/**
 * Created by tian on 2016/10/2.
 */

public class NativeFontIOS extends IOSApplication.Delegate implements NativeFontListener{

    protected NativeFontIOS(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        return null;
    }

    private UIColor getColor(Color color) {
        return UIColor.colorWithRedGreenBlueAlpha(color.r, color.g, color.b, color.a);
       // return UIColor.fromRGBA((double) color.r, (double) color.g, (double) color.b, (double) color.a);
    }

    @Override
    public Pixmap getFontPixmap(String strings, NativeFontPaint vpaint) {
        UIFont font;
        if (vpaint.getFakeBoldText() || vpaint.getStrokeColor() != null) {
            font = UIFont.boldSystemFontOfSize(vpaint.getTextSize());

            //font = UIFont.getBoldSystemFont((double) vpaint.getTextSize());
        } else {
            //font = UIFont.getSystemFont((double) vpaint.getTextSize());
            font = UIFont.systemFontOfSize(vpaint.getTextSize());
        }

        CGSize dim = NSString.stringWithString(strings).sizeWithFont(font);
        //CGSize dim = new NSString(strings).getSize(font);

        //UILabel label = new UILabel(new CGRect(new CGPoint(0, 0), new CGSize(dim.width(), dim.height())));
        UILabel label = UILabel.alloc().initWithFrame(new CGRect(new CGPoint(0, 0), new CGSize(dim.width(), dim.height())));
        UILabel label2 = null;
        label.setText(strings);
        label.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha(1.0d, 1.0d, 1.0d, 0.0d));
        label.setTextColor(getColor(vpaint.getColor()));
        label.setFont(font);
        label.setOpaque(false);
        label.setAlpha(1.0d);
        NSRange nSRange = new NSRange(0, (long) strings.length());

        //NSMutableAttributedString nSMutableAttributedString = new NSMutableAttributedString(strings);
        NSMutableAttributedString nSMutableAttributedString = NSMutableAttributedString.alloc().initWithString(strings);

        //nSMutableAttributedString.addAttribute(NSAttributedStringAttribute.ForegroundColor, getColor(vpaint.getColor()), nSRange);
        nSMutableAttributedString.addAttributeValueRange("ForegroundColor", getColor(vpaint.getColor()), nSRange);

        if (vpaint.getStrokeColor() != null) {
            UILabel uILabel = UILabel.alloc().initWithFrame(new CGRect(new CGPoint(0.0f, 0.0f), new CGSize(dim.width(), dim.height())));
            uILabel.setText(strings);
            uILabel.setBackgroundColor(UIColor.colorWithRedGreenBlueAlpha(1.0d, 1.0d, 1.0d, 0.0d));
            uILabel.setTextColor(getColor(vpaint.getColor()));
            uILabel.setFont(font);
            uILabel.setOpaque(false);
            uILabel.setAlpha(1.0d);
            nSMutableAttributedString = NSMutableAttributedString.alloc().initWithString(strings);
            nSMutableAttributedString.addAttributeValueRange("StrokeColor", getColor(vpaint.getStrokeColor()), nSRange);
            nSMutableAttributedString.addAttributeValueRange("StrokeWidth", NSNumber.numberWithInt(vpaint.getStrokeWidth()), nSRange);
            uILabel.setAttributedText(nSMutableAttributedString);
        } else if (vpaint.getUnderlineText()) {
            nSMutableAttributedString.addAttributeValueRange("UnderlineStyle", NSNumber.numberWithLong(NSUnderlineStyle.StyleSingle), nSRange);
        } else if (vpaint.getStrikeThruText()) {
            nSMutableAttributedString.addAttributeValueRange("StrikethroughStyle", NSNumber.numberWithLong(NSUnderlineStyle.StyleSingle | NSUnderlineStyle.PatternSolid), nSRange);
        }
        label.setAttributedText(nSMutableAttributedString);

        UIKit.UIGraphicsBeginImageContext(dim);

        //UIGraphics.beginImageContext(dim, false, 1.0d);
        label.layer().renderInContext(UIKit.UIGraphicsGetCurrentContext());

        //label.getLayer().render(UIGraphics.getCurrentContext());
        if (vpaint.getStrokeColor() != null) {

            //label2.getLayer().render(UIGraphics.getCurrentContext());
            label2.layer().renderInContext(UIKit.UIGraphicsGetCurrentContext());
        }
        //UIImage image = UIGraphics.getImageFromCurrentImageContext();
        UIImage image = UIKit.UIGraphicsGetImageFromCurrentImageContext();
        //UIGraphics.endImageContext();
        UIKit.UIGraphicsEndImageContext();
        //NSData nsData = image.toPNGData();
        NSData nsData = UIKit.UIImagePNGRepresentation(image);
        //return new Pixmap(nsData.getBytes(), 0, nsData.getBytes().length);

        byte[] types = nsData.bytes().getBytePtr().toByteArray((int) nsData.length());

        return new Pixmap(types, 0, types.length);
    }
}
