package com.mfc.kdyxworker.listeners;

import android.view.View;

import com.mfc.kdyxworker.Constants;

/**
 * 此抽象类用于防抖操作mfc。
 */
public abstract class OnMyClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        if (isContinuousClick()){
            return;
        }
        onMyClick(view);
    }
    public abstract void onMyClick(View view);
    /**
     * 是否为连续点击，用于过滤连续多次点击.
     */
    public double DOUBLE_CLICK_TIME = 0L;//按键点击时间
    public boolean isContinuousClick(){
        if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > Constants.CLICKTIME) {//这里测试1500ms比较合适
            DOUBLE_CLICK_TIME = System.currentTimeMillis();
            return false;
        }
        DOUBLE_CLICK_TIME = System.currentTimeMillis();
        return true;
    }
}
