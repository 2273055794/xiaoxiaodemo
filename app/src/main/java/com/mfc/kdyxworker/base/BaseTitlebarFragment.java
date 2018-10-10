package com.mfc.kdyxworker.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.widget.LoadDialog;
import com.mfc.kdyxworker.widget.TitleBar;
import com.mfc.mylibrary.widget.loadingview.XLoadingView;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseTitlebarFragment extends Fragment {
    private View rootView;
    private Unbinder butterKnife;
    private Dialog loadDialog;
    protected XLoadingView fl_container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_base_empty, container, false);
            fl_container = rootView.findViewById(R.id.fl_container);
            fl_container.setContentView(this.getLayoutId());
            fl_container.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fl_container.showLoading();
                    initData();
                }
            });
//            rootView = inflater.inflate(this.getLayoutId(), container, false);
            butterKnife = ButterKnife.bind(this, rootView);
            initView(rootView);
            initData();
        }else {
            butterKnife = ButterKnife.bind(this, rootView);
            reflesh();
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }
    protected View getContentView() {
        return rootView;
    }
    /**
     *
     * @param view 自定义的titlebar
     */
    protected void setTitlebar(View view){
        RelativeLayout rl_titlebar = rootView.findViewById(R.id.rl_titlebar);
        rl_titlebar.addView(view);
    }

    /**
     * 这里提供一个默认的titlebar , 如有其它要求可重新组合titlebar.
     * @param titleName
     */
    protected View getDefautTitlebar(String titleName) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_titlebar, null, false);
        TitleBar titleBar = view.findViewById(R.id.titlebar);
        titleBar.getCenterTextView().setText(titleName);
        return view;
    }

    /**
     * 加载框
     *
     * @return
     */
    protected void showDialog() {
        if (loadDialog == null) {
            loadDialog = LoadDialog.createProgressDialog(getActivity());
//            loadDialog.show();
        } else if (!loadDialog.isShowing()) {//
            loadDialog.show();
        }
    }

    protected void dismissDialog() {
        //正在显示
        if (loadDialog != null && loadDialog.isShowing())
            loadDialog.dismiss();
    }
    /**
     * 隐藏软键盘
     */
    protected void hideKey(EditText tvMoney){
        if (Build.VERSION.SDK_INT <= 10) {//4.0以下 danielinbiti
            tvMoney.setInputType(InputType.TYPE_NULL);
        } else {
            getActivity().getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(tvMoney, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //销毁前操作
    protected void destroyView() {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
        if (butterKnife!=null){
            butterKnife.unbind();
        }
    }

    public abstract int getLayoutId();

    public void initView(View rootView){
    }
    public void initData() {
    }
    //页面重复加载时调用该方法，用于页面数据刷新。
    public void reflesh(){
    }

}
