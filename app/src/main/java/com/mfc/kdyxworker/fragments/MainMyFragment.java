package com.mfc.kdyxworker.fragments;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mfc.kdyxworker.R;
import com.mfc.kdyxworker.base.BaseFragment;
import com.mfc.kdyxworker.base.BaseTitlebarFragment;
import com.mfc.kdyxworker.listeners.OnMyClickListener;
import com.mfc.kdyxworker.widget.MyButton;
import com.mfc.kdyxworker.widget.MyListItems;

/**
 * Created by Administrator on 2016/10/31.
 */
public class MainMyFragment extends BaseTitlebarFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_my;
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

        MyListItems mLogin = view.findViewById(R.id.m_login);
        MyListItems mPhone = view.findViewById(R.id.m_phone);
        MyListItems mVerson = view.findViewById(R.id.m_verson);
        mLogin.setTvName("登录密码");
        mLogin.setTvDes("修改");
        mPhone.setTvName("手机号码");
        mPhone.setTvDes("");
        mVerson.setTvName("版本信息");
        mVerson.setTvDes("");
        Button btnExit = view.findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new OnMyClickListener() {
            @Override
            public void onMyClick(View view) {
                Toast.makeText(getActivity(), "执行了"+(++i), Toast.LENGTH_SHORT).show();
            }
        });
    }
    int i = 0;
}
