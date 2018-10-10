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

import com.mfc.kdyxworker.widget.LoadDialog;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private View rootView;
    private Unbinder butterKnife;
    private Dialog loadDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(this.getLayoutId(), container, false);
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
