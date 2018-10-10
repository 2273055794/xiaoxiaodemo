package com.mfc.kdyxworker.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mfc.kdyxworker.R;

/**
 * Created by mfc on 2016/08/04.
 */
public class Custom2Dialog extends Dialog {
    private Context context;

    public Custom2Dialog(Context context) {
        super(context);
        this.context = context;
    }

    public Custom2Dialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public static class Builder {
        private Context context;
        private String msg;
        private int buttonNum;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private Boolean cancleAble = true;

        private OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;
        private OnKeyListener keyListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context, Boolean cancleAble) {
            this.context = context;
            this.cancleAble = cancleAble;
        }

        private Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        private Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        private Builder setMsg(int msg) {
            this.msg = (String) context.getText(msg);
            return this;
        }

        private Builder setButtonNum(int buttonNum) {
            this.buttonNum = buttonNum;
            return this;
        }

        private Builder setContentView(View setContentView) {
            this.contentView = setContentView;
            return this;
        }

        private Builder setPositiveButtonClickListener(String positiveButtonText, OnClickListener positiveButtonClickListener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        private Builder setPositiveButtonClickListener(int positiveButtonText, OnClickListener positiveButtonClickListener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        private Builder setNegativeButtonClickListener(String negativeButtonText, OnClickListener negativeButtonClickListener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        private Builder setNegativeButtonClickListener(int negativeButtonText, OnClickListener negativeButtonClickListener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        private Builder setKeyListener(OnKeyListener keyListener) {
            this.keyListener = keyListener;
            return this;
        }
        private Custom2Dialog create(View view){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = inflater.inflate(layout, null);
            Custom2Dialog dialog = new Custom2Dialog(context, R.style.dialog);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(view);
            dialog.setCancelable(cancleAble);
            return dialog;
        }

        private Custom2Dialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_custom2, null);
            final Custom2Dialog dialog = new Custom2Dialog(context, R.style.dialog);
            dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            switch (buttonNum) {
                case 1:
                    view.findViewById(R.id.div_neutral).setVisibility(View.GONE);
                    view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                    break;
                case 2:
                    break;
            }
            if (msg != null) {
                ((TextView) view.findViewById(R.id.message)).setText(msg);
            }
            if (negativeButtonText == null) {
                view.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            } else {
                ((Button) view.findViewById(R.id.negativeButton)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) view.findViewById(R.id.negativeButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }

            if (positiveButtonText == null) {
                view.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            } else {
                ((Button) view.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) view.findViewById(R.id.positiveButton)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            }
            if (keyListener != null) {
                dialog.setOnKeyListener(keyListener);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) view.findViewById(R.id.dialogcon))
                        .removeAllViews();
                ((LinearLayout) view.findViewById(R.id.dialogcon)).addView(
                        contentView, new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(view);
            dialog.setCancelable(cancleAble);
            return dialog;
        }
    }

    /**
     * 创建一个确认按钮的dialog
     *
     * @param context
     * @param message
     * @param positiveBtnOnClickListener
     */
    public static void createOKDialog(Context context,
                                      String message,
                                      OnClickListener positiveBtnOnClickListener) {
        Custom2Dialog.Builder builder = new Custom2Dialog.Builder(context).setButtonNum(1).setMsg(message).setPositiveButtonClickListener(R.string.positiveButtonText, positiveBtnOnClickListener);
        builder.create().show();
    }
    //弹窗时 手机返回键是否有效
    public static void createOKDialog(Context context,
                                      String message,
                                      OnClickListener positiveBtnOnClickListener, boolean isCancel) {
        Custom2Dialog.Builder builder = new Custom2Dialog.Builder(context).setButtonNum(1).setMsg(message).setPositiveButtonClickListener(R.string.positiveButtonText, positiveBtnOnClickListener);
        Custom2Dialog dialog = builder.create();
        dialog.setCancelable(isCancel);
        dialog.show();
    }

    public static void createOneButtonDialog(Context context,
                                             int messageId, String buttontext,
                                             OnClickListener positiveBtnOnClickListener) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(1).setMsg(messageId)
                .setPositiveButtonClickListener(buttontext, positiveBtnOnClickListener);
        customBuilder.create().show();
    }

    public static void createOneButtonDialog(Context context,
                                             String message, String buttontext,
                                             OnClickListener positiveBtnOnClickListener) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(1).setMsg(message)
                .setPositiveButtonClickListener(buttontext, positiveBtnOnClickListener);
        customBuilder.create().show();
    }

    /**
     * 创建两个按钮的dialog
     * @param context
     * @param messageId
     * @param positiveBtnOnClickListener
     * @param negativeBtnOnClickListener
     */
    public static void createTwoButtonDialog(Context context,
                                             int messageId,
                                             OnClickListener positiveBtnOnClickListener,
                                             OnClickListener negativeBtnOnClickListener) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(2).setMsg(messageId)
                .setNegativeButtonClickListener("取消", negativeBtnOnClickListener)
                .setPositiveButtonClickListener(R.string.positiveButtonText, positiveBtnOnClickListener);
        customBuilder.create().show();
    }

    public static void createTwoButtonDialog(Context context,
                                             String message,
                                             OnClickListener positiveBtnOnClickListener,
                                             OnClickListener negativeBtnOnClickListener) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(2).setMsg(message)
                .setNegativeButtonClickListener("取消", negativeBtnOnClickListener)
                .setPositiveButtonClickListener(R.string.positiveButtonText, positiveBtnOnClickListener);
        customBuilder.create().show();
    }

    public static void createTwoButtonDialog(Context context,
                                             String message, String positiveBtn, String negativeBtn,
                                             OnClickListener positiveBtnOnClickListener,
                                             OnClickListener negativeBtnOnClickListener) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(2).setMsg(message)
                .setNegativeButtonClickListener(negativeBtn, negativeBtnOnClickListener)
                .setPositiveButtonClickListener(positiveBtn, positiveBtnOnClickListener);
        customBuilder.create().show();
    }
    /**
     * 创建一个带有"取消"按钮的下载进度对话框
     *
     * @param context
     * @param titleId
     *            对话框标题
     * @param
     * @param positiveBtnOnClickListener
     *            "取消"按钮处理事件
     * @param v
     * 			  中间部分的试图
     */
    public static Builder CreateDownloadDialogWithCal(Context context, String titleId,
                                                      OnClickListener positiveBtnOnClickListener, View v) {
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        customBuilder.setButtonNum(1)
                .setMsg(titleId)
                .setContentView(v)
                .setPositiveButtonClickListener("取消", positiveBtnOnClickListener);
        return customBuilder;
    }
    public static Dialog creatDialog(Builder builder){
        return builder.create();
    }

    public static Custom2Dialog creatDialog(Context context, View view){
        Custom2Dialog.Builder customBuilder = new Custom2Dialog.Builder(context);
        return customBuilder.create(view);
    }

}
