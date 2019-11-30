package com.yc.baselib.action;

import androidx.lifecycle.MutableLiveData;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-11-30
 */
public class UIChangeAction {

   public UIChangeliveEvent startLoading=new UIChangeliveEvent();
    public UIChangeliveEvent stopLoading=new UIChangeliveEvent();

    public void stopLoading() {
        this.stopLoading.call();
    }
    public void startLoading(){
        this.startLoading.call();
    }
}
