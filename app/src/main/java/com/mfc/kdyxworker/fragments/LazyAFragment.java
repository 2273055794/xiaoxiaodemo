package com.mfc.kdyxworker.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mfc.kdyxworker.base.BaseEmptyFragment;
import com.mfc.kdyxworker.base.BaseFragment;

/**
 * Created by Administrator on 2017/01/04.
 */

public abstract class LazyAFragment extends BaseEmptyFragment {

    protected boolean isInit = false;
    private Bundle savedInstanceState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //一旦isVisibleToUser==true即可对真正需要的显示内容进行加载
        if (getUserVisibleHint() && !isInit) {
            this.savedInstanceState = savedInstanceState;
            onCreateViewLazy(savedInstanceState);
            isInit = true;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //可见，但还没被初始化
        if (isVisibleToUser && !isInit) {
            mHandler.sendEmptyMessageDelayed(100, 1000);
        }else if(!isVisibleToUser){
            Log.e("aa", "start: "+"执行了  group" );
            mHandler.removeCallbacksAndMessages(null);
        }
    }
    private boolean isFirst = false;
    private boolean isStart = false;//是否已经执行一次
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        Log.d("TAG", "onCreateViewLazy() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        if (getContentView()!=null){
            start();
        }else {//第一次执行
            isFirst = true;
        }
    }
    protected abstract void start();

    @Override
    public void onStart() {
        super.onStart();
        if(isFirst && getUserVisibleHint() && isInit && !isStart){
            isStart = true;
            start();
        }
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!isInit) {
                onCreateViewLazy(savedInstanceState);
                isInit = true;
            }
        }
    };

    @Override
    protected void destroyView() {
        super.destroyView();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void reflesh() {

    }
}
