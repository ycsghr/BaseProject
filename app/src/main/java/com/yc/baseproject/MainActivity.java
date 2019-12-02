package com.yc.baseproject;


import com.yc.baselib.view.BaseActivity;
import com.yc.baseproject.databinding.ActivityMainBinding;
import com.yc.repository.dto.ArticleChaptersDto;
import com.yc.repository.manager.NetCallBack;
import com.yc.repository.manager.NetRepository;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {



    @Override
    protected void init() {
        NetRepository.init("https://www.wanandroid.com/");
        NetRepository.articleChapters(new NetCallBack<ArrayList<ArticleChaptersDto>>() {


            @Override
            public void onSuccess(ArrayList<ArticleChaptersDto> data) {

            }
        });

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
