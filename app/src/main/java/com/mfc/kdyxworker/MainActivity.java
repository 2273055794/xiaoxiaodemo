package com.mfc.kdyxworker;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.mfc.kdyxworker.base.BaseActivity;
import com.mfc.kdyxworker.fragments.MainHomeFragment;
import com.mfc.kdyxworker.fragments.MainManagerFragment;
import com.mfc.kdyxworker.fragments.MainMyFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private Class fragmentArray[] = {MainHomeFragment.class, MainManagerFragment.class, MainMyFragment.class};
    private int imageViewArray[] = {R.drawable.selector_nav_home, R.drawable.selector_nav_money, R.drawable.selector_nav_my};
    private String textViewArray[] = {"首页", "配送任务", "我的"};
    private LayoutInflater inflater;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        tabhost.setup(this, getSupportFragmentManager(), R.id.container);
        inflater = LayoutInflater.from(this);
        int count = textViewArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(textViewArray[i]).setIndicator(getTabItemView(i));
            tabhost.addTab(tabSpec, fragmentArray[i], null);
            tabhost.setTag(i);
//            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_while_bg);
        }
    }
    private View getTabItemView(int i) {
        //将xml布局转换为view对象
        View view = inflater.inflate(R.layout.tab_content, null);
        //利用view对象，找到布局中的组件,并设置内容，然后返回视图
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }
}
