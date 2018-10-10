package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mfc.kdyxworker.MainActivity;
import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseActivity;
import com.mfc.kdyxworker.listeners.OnMyClickListener;

public class LoginActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        super.initView();
        TextView tv_register = findViewById(R.id.tv_register);
        TextView tv_forgetpw = findViewById(R.id.tv_forgetpw);
        Button btn_login = findViewById(R.id.btn_login);
        //立即注册
        tv_register.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //忘记密码
        tv_forgetpw.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
        //登录
        btn_login.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
