package com.mfc.kdyxworker.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mfc.kdyxworker.R;

/**
 * Created by mfc on 2018/5/9.
 */

public class SuccDialog extends RelativeLayout {

    private ImageView iv_icon;
    private TextView tv_des;
    private AlertDialog dialog;
    private Handler mHandler = new Handler();

    public SuccDialog(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_succ, null);
        iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        tv_des = (TextView) view.findViewById(R.id.tv_des);
        dialog = new AlertDialog.Builder(context).setView(view).create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mHandler!=null){
                    mHandler.removeCallbacksAndMessages(null);
                }
            }
        });
    }
    public Dialog setIconType(int resource){
        iv_icon.setImageResource(resource);
        return dialog;
    }
    public Dialog setTv_des(String des){
        tv_des.setText(TextUtils.isEmpty(des)?"":des);
        return dialog;
    }
    public void dismissDialog(){
        if (dialog!=null){
            dialog.dismiss();
        }
    }
    public Dialog showDialog(){
        if (dialog!=null){
            dialog.show();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissDialog();
            }
        }, 2000);
        return dialog;
    }
}
