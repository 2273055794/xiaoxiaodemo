package com.mfc.kdyxworker.fragments;

import android.os.Handler;
import android.view.View;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseFragment;
import com.mfc.kdyxworker.base.BaseTitlebarFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MainHomeFragment extends BaseTitlebarFragment {
    ArrayList lists = new ArrayList();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initView(View view) {
        setTitlebar(getDefautTitlebar("蝌蚪优选"));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fl_container.showContent();
            }
        }, 2000);
//        RecyclerView ry_view = view.findViewById(R.id.ry_view);
//        ry_view.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ry_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        ry_view.setAdapter(new MyAdapter(R.layout.activity_screen_info, lists));
    }

    @Override
    public void initData() {
        super.initData();
        for (int i=0;i<100;i++){
            lists.add("友  好  的  问  候  "+i);
        }
    }
//    private class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
//
//        public MyAdapter(int layoutResId, @Nullable List<String> data) {
//            super(layoutResId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, String item) {
//            helper.setText(R.id.tv_info, item+"");
//        }
//    }
}
