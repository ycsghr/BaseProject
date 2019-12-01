package com.yc.repository.manager;

import io.reactivex.Observable;
import java.util.ArrayList;
import retrofit2.http.GET;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public interface ISystemService {
    @GET("wxarticle/chapters/json  ")
    Observable<Result<ArrayList<data>>> configGet();


}
