package com.mfc.kdyxworker.activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Response;
import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;
import com.mfc.kdyxworker.base.BaseEmptyActivity;
import com.mfc.kdyxworker.base.BaseReuestActivity;
import com.mfc.kdyxworker.listeners.OnMyItemChildClickListener;
import com.mfc.mylibrary.widget.loadingview.XLoadingView;
import com.mfc.mylibrary.widget.loadingview.XLoadingViewConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品售后页面
 */
public class GoodsAfterSaleActivity extends BaseReuestActivity {
    private List<String> lists = new ArrayList<>();
    private RecyclerView ry_view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_after_sale;
    }

    @Override
    protected void onSucceed(Response<String> stringResponse) {
//        XLoadingView
    }

    @Override
    public void initView() {
        super.initView();
        setTitlebar(getDefautTitlebar("商品售后"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                container.showContent();
            }
        }, 2000);


        ry_view = findViewById(R.id.ry_view);
        ry_view.setLayoutManager(new LinearLayoutManager(this));

        GoodsAfterAdapter adapter = new GoodsAfterAdapter(R.layout.activity_goods_after_sale_item, lists);
        adapter.openLoadAnimation();
        View view = LayoutInflater.from(this).inflate(R.layout.activity_transit_backet_header, null);
        TextView tv_add = view.findViewById(R.id.tv_add);
        tv_add.setText("添加售后商品");
        adapter.addHeaderView(view);
        ry_view.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new OnMyItemChildClickListener() {
            @Override
            public void onMyItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(GoodsAfterSaleActivity.this, TransitBasketEditActivity.class));
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i<100; i++){
            lists.add("友  好  的  问  候  "+i);
        }
    }

    private class GoodsAfterAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public GoodsAfterAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.addOnClickListener(R.id.btn_edit);
        }
    }
}
