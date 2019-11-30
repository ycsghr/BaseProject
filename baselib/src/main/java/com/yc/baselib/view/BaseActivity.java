package com.yc.baselib.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.yc.baselib.interfaces.IBaseView;
import com.yc.baselib.interfaces.IUIchangView;
import com.yc.baselib.viewmodel.BaseViewModel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel, V
    extends ViewDataBinding> extends AppCompatActivity
    implements IBaseView, IUIchangView {

    protected V mBinding;
    protected VM mViewModel;
    protected ViewGroup mRootView;
    protected int viewModelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
        setContentView(getRootViewId());


    }

    protected void initActivity() {
        initBindingView();
        initViewModel();
        initLifecycle();
        initComponent();
    }

    private void initComponent() {
        initAction();
        mViewModel.init();
        init();

    }

    private void initAction() {
        mViewModel.getUiChange().startLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
        mViewModel.getUiChange().stopLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });

    }

    protected abstract void init();

    private void initLifecycle() {
        viewModelId = getVariableId();
        if (viewModelId != 0) {
            if (mBinding == null) {
                Log.e("BaseActivity", "mBinding 为null");
            } else {
                mBinding.setVariable(viewModelId, mViewModel);
                mBinding.setLifecycleOwner(this);
            }
        }

        getLifecycle().addObserver(mViewModel);
    }

    private void initViewModel() {
        if (mViewModel == null) {
            Class targetViewModel;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                targetViewModel = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                targetViewModel = BaseViewModel.class;
            }
            mViewModel = (VM) getViewModel(targetViewModel);
        }
    }

    private void initBindingView() {
        if (getRootViewId() == 0) {
            throw new RuntimeException("RootViewId == 0 ,请设置布局id");
        }
        bindingView(getRootViewId());
    }

    /**
     * databingding相关
     */

    public ViewGroup bindingView(int layoutId) {
        mBinding = DataBindingUtil.setContentView(this, layoutId);
        //如果mBinding为空则引入window的decorView一般情况下不可能发生
        if (mBinding == null) {
            View decorView = this.getWindow().getDecorView();
            mRootView = decorView.findViewById(android.R.id.content);
        } else {
            mRootView = (ViewGroup) mBinding.getRoot();
            bindingLifecycle();
        }
        return mRootView;
    }

    /**
     * databind绑定生命周期
     */
    private void bindingLifecycle() {
        if (mBinding != null) {
            mBinding.setLifecycleOwner(this);
        }
    }


    public VM getViewModel(Class<VM> viewModelClass) {
        return ViewModelProviders.of(this).get(viewModelClass);
    }

    @Override
    public void stopLoading() {
        mViewModel.getUiChange().stopLoading();
    }
    @Override
    public void startLoading() {
        mViewModel.getUiChange().stopLoading();
    }

}
