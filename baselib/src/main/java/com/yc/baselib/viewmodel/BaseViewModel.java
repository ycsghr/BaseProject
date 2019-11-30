package com.yc.baselib.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.yc.baselib.action.UIChangeAction;
import com.yc.baselib.interfaces.IUIchangView;
import com.yc.baselib.view.BaseActivity;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-11-29
 */
public class BaseViewModel extends AndroidViewModel implements LifecycleObserver, IUIchangView {

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
        Log.d("----","onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        Log.d("----","onDestroy");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart() {
        Log.d("----","onStart");

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


    public void init() {

    }
}
