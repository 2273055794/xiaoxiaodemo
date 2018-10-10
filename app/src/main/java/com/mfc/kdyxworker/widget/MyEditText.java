package com.mfc.kdyxworker.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/11/1/001.
 * 控件焦点改变时回调。
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {
    private OnImputCompleteListener mOnImputCompleteListener;
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (!focused) {
            mOnImputCompleteListener.onImputComplete();
        }
    }
    public void setOnImputCompleteListener(OnImputCompleteListener l) {
        this.mOnImputCompleteListener = l;
    }
   public interface OnImputCompleteListener {
        void onImputComplete();
    }
}

