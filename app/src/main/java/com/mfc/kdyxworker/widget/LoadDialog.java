package com.mfc.kdyxworker.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mfc.kdyxworker.R;

public class LoadDialog {
	
	public static Dialog createProgressDialog(Context context) {
		return create(context);
	}
	private static Dialog create(Context context){
		View layout = LayoutInflater.from(context).inflate(
				R.layout.load_data, null);
		Dialog dialog = new Dialog(context, R.style.dialog_progress);
		dialog.setContentView(layout);
		dialog.setCancelable(true);
		// 获得当前窗体
		Window window = dialog.getWindow();

		// 重新设置
		WindowManager.LayoutParams lp = window.getAttributes();
		window.setGravity(Gravity.CENTER | Gravity.CENTER);
//		lp.x = 0; // 新位置X坐标
//		lp.y = 100; // 新位置Y坐标
//		 lp.alpha = 0.7f; // 透明度
		window.setAttributes(lp);
		dialog.show();
		return dialog;
	}
	
	public static void dissmis(Dialog dialog){
		if(dialog!=null){
			dialog.dismiss();
		}
	}
}
