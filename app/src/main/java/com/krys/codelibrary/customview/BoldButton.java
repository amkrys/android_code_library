package com.krys.codelibrary.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by obadmin on 8/3/18.
 */

@SuppressLint("AppCompatCustomView")
public class BoldButton extends Button {
    public BoldButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoldButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "Montserrat-Bold.ttf");
        setTypeface(tf);
    }
}
