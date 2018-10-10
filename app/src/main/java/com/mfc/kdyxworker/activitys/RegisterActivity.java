package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;
import com.mfc.kdyxworker.listeners.OnMyClickListener;

public class RegisterActivity extends BaseBackActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        super.initView();
        //返回登录页
        TextView tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                finish();
            }
        });
        //下一步
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                startActivity(new Intent(RegisterActivity.this, InfoPerfectActivity.class));
            }
        });
    }
}
