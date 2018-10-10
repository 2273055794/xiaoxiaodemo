package com.mfc.kdyxworker.activitys;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseBackActivity;

import java.util.ArrayList;
import java.util.List;

public class AccomplishDetailActivity extends BaseBackActivity {

    private RecyclerView ry_view;
    private List<String> lists = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_accomplish_detail;
    }

    @Override
    public void initView() {
        super.initView();
        ry_view = findViewById(R.id.ry_view);
        ry_view.setLayoutManager(new LinearLayoutManager(this));

        View view = LayoutInflater.from(this).inflate(R.layout.activity_accomplish_detail_header,null);
        AccomplishDtailAdapter adapter = new AccomplishDtailAdapter(R.layout.activity_accomplish_detail_item, lists);
        adapter.openLoadAnimation();
        adapter.addHeaderView(view);
        ry_view.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i<50; i++){
            lists.add("友  好  的  问  候  "+i);
        }
    }
    private class AccomplishDtailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public AccomplishDtailAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
