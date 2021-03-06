package com.strategic.africa.aslc.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.strategic.africa.aslc.R;


/**
 * Created by diego on 5/22/16.
 */
public class CustomTextView extends AppCompatTextView{
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(attrs);
    }

    private void setCustomFont(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomFont,
                0, 0);

        try {
            String family = a.getString(R.styleable.CustomFont_fontFamily);
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + family);
            setTypeface(myTypeface);
        } finally {
            a.recycle();
        }
    }
}
