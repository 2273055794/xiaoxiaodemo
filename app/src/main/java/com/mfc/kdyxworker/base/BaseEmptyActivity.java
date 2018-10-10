package com.mfc.kdyxworker.base;
/**
 * 创建于mfc
 * 用于需要联网初始化的页面。
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.widget.TitleBar;
import com.mfc.mylibrary.widget.loadingview.XLoadingView;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import butterknife.ButterKnife;

public abstract class BaseEmptyActivity extends AppCompatActivity {

    protected XLoadingView container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.init();
        setContentView(R.layout.activity_base_empty);
        ButterKnife.bind(this);
        this.initView();
        initData();
        initListener();
    }
    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init(){}
    public void initView(){
        container = findViewById(R.id.fl_container);
        container.setContentView(this.getLayoutId());
        container.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.showLoading();
                initData();
            }
        });
    }

    public void initData() {
    }
    public void initListener() {
        TitleBar titleBar = findViewById(R.id.titlebar);
        if (titleBar==null){
            return;
        }
        titleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON){
                    finish();
                }
            }
        });
    }
    //得到当前界面的布局文件id(由子类实现)
    public abstract int getLayoutId();
    /**
     *
     * @param view 自定义的titlebar
     */
    protected void setTitlebar(View view){
        RelativeLayout rl_titlebar = findViewById(R.id.rl_titlebar);
        rl_titlebar.addView(view);
    }

    /**
     * 这里提供一个默认的titlebar , 如有其它要求可重新组合titlebar.
     * @param titleName
     */
    protected View getDefautTitlebar(String titleName) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_defaut_titlebar, null, false);
        TitleBar titleBar = view.findViewById(R.id.titlebar);
        titleBar.getCenterTextView().setText(titleName);
        return view;
    }
}
