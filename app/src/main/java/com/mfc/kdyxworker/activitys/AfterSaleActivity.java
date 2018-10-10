package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;
import com.mfc.kdyxworker.listeners.OnMyClickListener;

public class AfterSaleActivity extends BaseBackActivity {
    private boolean isChecked = false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_after_sale;
    }

    @Override
    public void initView() {
        super.initView();
        Button btn_ok = findViewById(R.id.btn_ok);
        final CheckBox cb_aftersale = findViewById(R.id.cb_aftersale);
        final CheckBox cb_return = findViewById(R.id.cb_return);
        RelativeLayout rl_after_sale = findViewById(R.id.rl_after_sale);
        RelativeLayout rl_return = findViewById(R.id.rl_return);
        btn_ok.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                if (cb_aftersale.isChecked()){
                    startActivity(new Intent(AfterSaleActivity.this, GoodsAfterSaleActivity.class));
                }else if (cb_return.isChecked()){
                    startActivity(new Intent(AfterSaleActivity.this, TransitBasketActivity.class));
                }
            }
        });
        rl_after_sale.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                cb_aftersale.setChecked(true);
                cb_return.setChecked(false);
            }
        });
        rl_return.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                cb_aftersale.setChecked(false);
                cb_return.setChecked(true);
            }
        });

    }
}
