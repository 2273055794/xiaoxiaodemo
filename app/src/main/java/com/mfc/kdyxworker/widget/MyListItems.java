package com.mfc.kdyxworker.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mfc.kdyxworker.R;
import com.wuhenzhizao.titlebar.utils.ScreenUtils;

public class MyListItems extends RelativeLayout {

    private TextView tvName;
    private TextView tvDes;
    private ImageView ivGoto;

    public MyListItems(Context context) {
        super(context);
        init(context);
    }

    public MyListItems(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_mylistitems,this);
        tvName = findViewById(R.id.tv_name);
        tvDes = findViewById(R.id.tv_des);
        ivGoto = findViewById(R.id.iv_goto);
    }
    public void setTvName(String name){
        tvName.setText(TextUtils.isEmpty(name)?"":name);
    }

    public void setTvDes(String des){
        tvDes.setText(TextUtils.isEmpty(des)?"": des);
    }

    public void isShowIcon(boolean isShow){
        ivGoto.setVisibility(isShow?VISIBLE:GONE);
    }

    /**
     * 设置布局底部分割线的宽度
     * @param value  分割线的宽度   单位：dp
     */
    public void setLine(int value){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.getLayoutParams();
        layoutParams.bottomMargin = value<0? 0: ScreenUtils.dp2PxInt(getContext(), value);
        setLayoutParams(layoutParams);
    }
}
