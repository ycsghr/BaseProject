package com.yc.repository.manager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public abstract  class NetCallBack<S> implements Observer<Result<S>>{
    private int successCode=0;
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Result<S> result) {
        if (result.getErrorCode()==successCode){
            onSuccess(result.getData());
        }else {
            onFailure(result);
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
    public abstract void onSuccess(S data);
    public  void onFailure(Result result){

    }



}
