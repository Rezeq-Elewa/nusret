package com.example.rezeq.nusret.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Rezeq on 12/19/2017.
 * Email : rezeq.elewa@gmail.com
 */

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context) {
        super(context);
//        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/swissra_normal.otf");
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/jf_flat.ttf");
        this.setTypeface(face);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/swissra_normal.otf");
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/jf_flat.ttf");
        this.setTypeface(face);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        Typeface face=Typeface.createFromAsset(context.getAssets(), "fonts/swissra_normal.otf");
        Typeface face= Typeface.createFromAsset(context.getAssets(), "fonts/jf_flat.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}
