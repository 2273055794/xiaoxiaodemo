package com.mfc.kdyxworker.base;
/**
 * 创建于mfc
 * 用于需要联网初始化的页面。
 */

import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.mfc.kdyxworker.api.ServerApi;
import com.mfc.kdyxworker.utils.XNetworkUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class BaseReuestActivity extends BaseEmptyActivity {

    private int page = 1;//默认为1。
    protected abstract void onSucceed(Response<String> stringResponse);
    protected int getPage(){
        return page;
    }
    protected void setPage(int page){
        this.page = page;
    }
    /**
     * 网络请求 默认使用常用的post请求 其它请求可在serverApi定义。
     * 默认第一次页面初始化时显示加载等待布局，其它情况均使用显示弹出框。在显示原有数据的基础上显示半透明的弹出框
     * 进行动态在线更新数据，用户取消弹出框取消网络请求， 保持元数据不变，提高用户体验。
     * @param url
     * @param json
     */
    protected void requestDataString(String url, String json){
        //判断网络是否可用。
        if (!XNetworkUtils.isConnected()||!XNetworkUtils.ping()){
            container.showNoNetwork();
            return;
        }
        ServerApi.getString(url, json)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
//                        if (!isLoaded){
//                            container.showLoading();
//                        }
                    }
                })
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
//                        container.showContent();
                        //此无法判断请求的数据是否真正意义上的成功。故分页由子类去实现。
                        onSucceed(stringResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();            //请求失败
                        showToast("请求失败");
                        container.showError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    //把每一个请求的Disposable对象都交给由统一的CompositeDisposable对象去管理。
    private CompositeDisposable compositeDisposable;
    public void addDisposable(Disposable disposable){
        if (compositeDisposable== null){
            compositeDisposable= new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
    //取消网络请求
    public void dispose(){
        if (compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消网络请求
        dispose();
    }
}
