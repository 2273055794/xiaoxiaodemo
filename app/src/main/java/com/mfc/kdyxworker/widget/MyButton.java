package com.mfc.kdyxworker.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 此类可设置 边线和边线颜色 边线粗细 边线弧度 宽和高等背景操作。特殊要求请重设背景。
 */
public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context) {
        super(context);
        initMyButton(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyButton(context);
    }

    private void initMyButton(Context context) {

    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }
}
