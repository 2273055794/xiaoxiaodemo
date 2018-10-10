package com.mfc.kdyxworker.listeners;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mfc.kdyxworker.Constants;

public abstract class OnMyItemClickListener implements BaseQuickAdapter.OnItemClickListener {
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (isContinuousClick()){
            return;
        }
        onMyItemClick(adapter, view, position);
    }
    public abstract void onMyItemClick(BaseQuickAdapter adapter, View view, int position);
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
