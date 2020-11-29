package com.krys.codelibrary.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.google.android.material.button.MaterialButton;


public class RegularButton extends MaterialButton {

    public RegularButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RegularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "Montserrat-Regular.ttf");
        setTypeface(tf);
    }
}
