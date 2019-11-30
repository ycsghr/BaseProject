package com.yc.baselib.action;

import androidx.lifecycle.MutableLiveData;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-11-30
 */
public class UIChangeliveEvent extends MutableLiveData<Boolean> {

//    @Override
//    public void setValue(Boolean value) {
//        Boolean value1 = getValue();
//        super.setValue(null== value1 ?value:!getValue());
//    }
//    public void set(Boolean value){
//        Boolean getVaule = getValue();
//        setValue(getVaule==null?value:getVaule);
//    }

    public void call() {
        Boolean getVaule = getValue();
        setValue(getVaule == null || (!getVaule));
    }
}
