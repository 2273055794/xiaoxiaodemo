package com.mfc.kdyxworker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
        setContentView(this.getLayoutId());
        ButterKnife.bind(this);
        this.initView();
        initData();
        initListener();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init(){
    }
    public void initView(){
    }
    public void initData() {
    }
    public void initListener() {
    }
    //得到当前界面的布局文件id(由子类实现)
    public abstract int getLayoutId();
}
