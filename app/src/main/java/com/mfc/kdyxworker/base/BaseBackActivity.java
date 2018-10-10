package com.mfc.kdyxworker.base;

import android.view.View;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.widget.TitleBar;
import com.mfc.kdyxworker.widget.XToast;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

public abstract class BaseBackActivity extends BaseActivity {
    @Override
    public void initListener() {
        super.initListener();
        TitleBar titleBar = findViewById(R.id.titlebar);
        if (titleBar==null){
            XToast.error("请设置标题栏！");
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
}
