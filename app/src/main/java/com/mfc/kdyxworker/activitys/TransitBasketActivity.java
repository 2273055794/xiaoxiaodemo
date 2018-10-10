package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;
import com.mfc.kdyxworker.listeners.OnMyClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 中转筐退货页面
 */
public class TransitBasketActivity extends BaseBackActivity {
    private List<String> lists = new ArrayList<>();
    private RecyclerView ry_view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transit_basket;
    }

    @Override
    public void initView() {
        super.initView();
        ry_view = findViewById(R.id.ry_view);
        ry_view.setLayoutManager(new LinearLayoutManager(this));

        Button btn_edit = findViewById(R.id.btn_edit);
//        btn_edit.setOnClickListener(new OnMyClickListener() {
//            @Override
//            public void onMyClick(View view) {
//            }
//        });

        TransitBacketAdapter adapter = new TransitBacketAdapter(R.layout.activity_transit_basket_item, lists);
        adapter.openLoadAnimation();
        View view = LayoutInflater.from(this).inflate(R.layout.activity_transit_backet_header, null);
        adapter.addHeaderView(view);
        ry_view.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i<100; i++){
            lists.add("友  好  的  问  候  "+i);
        }
    }
    private class TransitBacketAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public TransitBacketAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
