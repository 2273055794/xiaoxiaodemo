package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;
import com.mfc.kdyxworker.listeners.OnMyClickListener;

public class ForgetPasswordActivity extends BaseBackActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void initView() {
        super.initView();
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                startActivity(new Intent(ForgetPasswordActivity.this, ForgetPw2Activity.class));
            }
        });
    }
}
