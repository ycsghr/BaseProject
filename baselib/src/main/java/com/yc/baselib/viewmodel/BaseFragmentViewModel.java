package com.yc.baselib.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public abstract class BaseFragmentViewModel extends BaseViewModel implements LifecycleObserver {


    public BaseFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
