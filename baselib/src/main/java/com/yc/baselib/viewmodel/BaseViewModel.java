package com.yc.baselib.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.yc.baselib.action.UIChangeAction;
import com.yc.baselib.interfaces.IUIchangView;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-11-29
 */
public abstract class BaseViewModel extends AndroidViewModel implements LifecycleObserver, IUIchangView {

    private UIChangeAction uiChange = new UIChangeAction();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public UIChangeAction getUiChange() {
        return uiChange;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause() {
    }
    @Override
    public void stopLoading(){
        uiChange.stopLoading();
    }
    @Override
    public void startLoading(){
        uiChange.startLoading();
    }


    public abstract void init();
}
