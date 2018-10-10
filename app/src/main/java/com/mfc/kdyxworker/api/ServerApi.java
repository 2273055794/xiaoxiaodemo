package com.mfc.kdyxworker.api;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.mfc.kdyxworker.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ServerApi {
    public static Observable<Response<String>> getString(String url, String json) {
        return OkGo.<String>post(url)//
                .params(Constants.Tadpole, json)//
                .converter(new StringConvert())//
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
//    public static <T> Observable<T> getData(String url, String json) {
//        return OkGo.<String>post(url)//
//                .params(Constants.Tadpole, json)//
//                .converter(new JsonConvert<T>())//
//                .adapt(new ObservableBody<T>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
