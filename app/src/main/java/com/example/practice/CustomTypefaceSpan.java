package com.example.practice;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Type;

public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface mNewType;

    public CustomTypefaceSpan(@Nullable String family, Typeface type) {
        super(family);
        this.mNewType = type;
    }

    public CustomTypefaceSpan(Typeface type) {
        super("");
        this.mNewType = type;
    }

    @Override
    public void updateDrawState(TextPaint ds){ applyCustomTypeFace(ds,mNewType);}

    @Override
    public void updateMeasureState(TextPaint paint) { applyCustomTypeFace(paint,mNewType);}

    private static void applyCustomTypeFace(Paint paint, Typeface tf){
        int oldStyle;
        Typeface old = paint.getTypeface();

        if(old == null){
            oldStyle = 0;
        }
        else{
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if((fake & Typeface.BOLD) != 0){
            paint.setFakeBoldText(true);
        }
        if((fake & Typeface.ITALIC) !=0){
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(tf);

    }

}
