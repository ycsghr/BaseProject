package com.yc.baselib.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.yc.baselib.interfaces.IBaseView;
import com.yc.baselib.interfaces.IUIchangView;
import com.yc.baselib.viewmodel.BaseFragmentViewModel;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public abstract class BaseFragment<VM extends BaseFragmentViewModel,V extends ViewDataBinding> extends Fragment implements IBaseView, IUIchangView {
    protected V mBinding;
    protected VM mViewModel;
    private ViewGroup mRootView;
    private int viewModelId;
    private ViewGroup mContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        this.mContainer = container;
        if (getRootViewId() == 0) {
            throw new RuntimeException("RootViewId == 0 ,请设置RootViewId");
        } else {
            return bindingView(getRootViewId());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelId = getVariableId();
        if (mViewModel == null) {
            Class targetViewModel;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                targetViewModel = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                targetViewModel = BaseFragmentViewModel.class;
            }
            mViewModel = (VM) getViewModel(targetViewModel);
        }
        if (viewModelId != 0) {
            mBinding.setVariable(viewModelId, mViewModel);
        } else {
            throw new NullPointerException("viewModelId为0");
        }

        getLifecycle().addObserver(mViewModel);
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

    private ViewGroup bindingView(int layoutId) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutId, mContainer, false);
        if (mBinding == null) {
            mRootView = null;
        } else {
            mRootView = (ViewGroup) mBinding.getRoot();
            bindingLifecycle();
        }
        return mRootView;
    }

    private void bindingLifecycle() {
        if (mBinding != null) {
            mBinding.setLifecycleOwner(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            getLifecycle().removeObserver(mViewModel);
        }
        if (mBinding != null) {
            mBinding.unbind();
        }
    }
    private VM getViewModel(Class<VM> viewModelClass) {
            return ViewModelProviders.of(this).get(viewModelClass);
    }
}
