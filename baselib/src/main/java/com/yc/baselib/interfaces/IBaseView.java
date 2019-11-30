package com.yc.baselib.interfaces;

import androidx.annotation.LayoutRes;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-11-30
 */
public interface IBaseView {

    /**
     * 抽象类无需实现
     * @return
     */
    @LayoutRes
    int getRootViewId();

    /**
     * 给的值应该为br.xxxx
     * @return
     */
    int getVariableId();



}
