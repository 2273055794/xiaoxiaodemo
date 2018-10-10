package com.mfc.kdyxworker.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.adapter.FragmentAdapter;
import com.mfc.kdyxworker.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MainManagerFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_manager;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        tabLayout = rootView.findViewById(R.id.tablayout);
        viewPager = rootView.findViewById(R.id.viewpager);
        showTabs(0);
    }
    private void showTabs(int current_tab) {
        //设置tablayout的被禁颜色
        tabLayout.setBackgroundColor(getResources().getColor(R.color.color_white));
        String[] names = new String[]{"未完成", "已完成", "全部"};
        List<Fragment> fragments = new ArrayList<>();
        for (int i=0;i<3;i++){
            Bundle argument = new Bundle();
            argument.putString("keyType", i+"");
            AccomplishFragment fragment = new AccomplishFragment();
            fragment.setArguments(argument);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new FragmentAdapter(getActivity().getSupportFragmentManager(), fragments, Arrays.asList(names)));
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(viewPager.getAdapter());
        //设置默认选项
        viewPager.setCurrentItem(current_tab);
        tabLayout.getTabAt(current_tab).select();
    }
}
