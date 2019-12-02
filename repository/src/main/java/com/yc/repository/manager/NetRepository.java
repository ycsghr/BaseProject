package com.yc.repository.manager;

import android.annotation.SuppressLint;
import com.yc.repository.BuildConfig;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public class NetRepository {


    private static volatile NetRepository repositoryManager;

    private NetRepository(String baseUrl, boolean isDebug) {
        RequestManager.init(baseUrl, isDebug);
    }

    public static NetRepository init(String baseUrl) {
        if (null == repositoryManager) {
            synchronized (NetRepository.class) {
                if (null == repositoryManager) {
                    repositoryManager = new NetRepository(baseUrl, BuildConfig.DEBUG);
                }
            }
        }
        return repositoryManager;
    }


    public static void articleChapters(NetCallBack callBack) {
        packageCallback(RequestManager.get(IService.class).articleChapters(),
            callBack);
    }

    /**
     * 包装请求回调
     */
    @SuppressLint("CheckResult")
    public static void packageCallback(Observable observable, NetCallBack callBack) {
        observable
            .compose(schedulersTransformer())
            .subscribe(callBack);
    }

    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
