package com.yc.baseproject;


import com.yc.baselib.view.BaseActivity;
import com.yc.baseproject.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {



    @Override
    protected void init() {
        startLoading();
        startLoading();

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public int getVariableId() {
        return BR.nihao;
    }
}
