package com.mfc.kdyxworker.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.activitys.AccomplishDetailActivity;
import com.mfc.kdyxworker.activitys.AfterSaleActivity;
import com.mfc.kdyxworker.activitys.AnotherPayActivity;
import com.mfc.kdyxworker.activitys.DistributionedActivity;
import com.mfc.kdyxworker.listeners.OnMyItemChildClickListener;
import com.mfc.kdyxworker.listeners.OnMyItemClickListener;
import com.mfc.kdyxworker.utils.log.XLog;
import com.mfc.kdyxworker.widget.Custom2Dialog;
import com.mfc.kdyxworker.widget.XToast;

import java.util.ArrayList;
import java.util.List;

public class AccomplishFragment extends LazyAFragment {

    private RecyclerView ry_view;
    private List<String> lists = new ArrayList<>();
    private String aa = "{\"Tadpole\":{\"Head\":{\"code\":\"S0000\",\"msg\":\"操作成功！\",\"state\":\"succ\",\"reqTime\":1509418364},\"Body\":[{\"goods_id\":\"390\",\"id\":\"11\",\"code_goods\":\"ceshi2\",\"storage_total\":\"13\",\"gstandard_id\":\"7437\",\"goods_name\":\"卡伊娜手足防裂霜护手霜\",\"price_sell\":\"200.00\",\"image_original\":\"http:\\/\\/127.0.0.1\\/tshop\\/data\\/upload\\/admin\\/20170825\\/599fbd441f148.png\"},{\"goods_id\":\"387\",\"id\":\"10\",\"code_goods\":\"ceshi1\",\"storage_total\":\"28\",\"gstandard_id\":\"7435,7436\",\"goods_name\":\"Neutrogena\\/露得清 深层滋润护手霜\",\"price_sell\":\"160.00\",\"image_original\":\"http:\\/\\/127.0.0.1\\/tshop\\/data\\/upload\\/admin\\/20170825\\/599fbd18d62f8.png\"},{\"goods_id\":\"388\",\"id\":\"10\",\"code_goods\":\"ceshi1\",\"storage_total\":\"28\",\"gstandard_id\":\"7435,7436\",\"goods_name\":\"嘉媚乐护手霜男女玫瑰丝滑精油润手霜\",\"price_sell\":\"180.00\",\"image_original\":\"http:\\/\\/127.0.0.1\\/tshop\\/data\\/upload\\/admin\\/20170825\\/599fbd18d62f8.png\"}],\"Page\":{\"totalcount\":\"2\",\"totalpage\":1,\"pagesize\":\"20\",\"pagenum\":\"1\"}}}\n";

    @Override
    protected void start() {
        View rootView= getContentView();
        ry_view = rootView.findViewById(R.id.ry_view);
        ry_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        MyAdapter adapter = new MyAdapter(R.layout.fragment_accomplish_item, lists);
        adapter.openLoadAnimation();
//        ry_view.setAdapter(adapter);
        adapter.bindToRecyclerView(ry_view);
        adapter.setOnItemClickListener(new OnMyItemClickListener() {
            @Override
            public void onMyItemClick(BaseQuickAdapter adapter, View view, int position) {
                getActivity().startActivity(new Intent(getActivity(), AccomplishDetailActivity.class));
            }
        });
        adapter.setOnItemChildClickListener(new OnMyItemChildClickListener() {
            @Override
            public void onMyItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()== R.id.btn_another_pay){//代付
                    getActivity().startActivity(new Intent(getActivity(), AnotherPayActivity.class));
                    Custom2Dialog.createOKDialog(getActivity(), "nihaoa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
//                    adapter.setNewData(null);
//                    adapter.setEmptyView(R.layout.layout_prograssbar, (ViewGroup) ry_view.getParent());
                }else if (view.getId()== R.id.btn_distribution){//开始配送

                }else if (view.getId()== R.id.btn_distributioned){//完成配送
                    getActivity().startActivity(new Intent(getActivity(), DistributionedActivity.class));
                }else if (view.getId()== R.id.btn_after_sale){//售后
                    getActivity().startActivity(new Intent(getActivity(), AfterSaleActivity.class));
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fl_container.showContent();
            }
        }, 2000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_accomplish;
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i<100; i++){
            lists.add("友  好  的  问  候  "+i);
        }
    }

    private class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.addOnClickListener(R.id.btn_another_pay)
                    .addOnClickListener(R.id.btn_distribution)
                    .addOnClickListener(R.id.btn_distributioned)
                    .addOnClickListener(R.id.btn_after_sale);
//            helper.setText(R.id.btn_distribution, item+"");
        }
    }
}
