package com.yc.repository.manager;

import com.yc.repository.dto.ArticleChaptersDto;
import io.reactivex.Observable;
import java.util.ArrayList;
import retrofit2.http.GET;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public interface IService {
    @GET("wxarticle/chapters/json")
    Observable<Result<ArrayList<ArticleChaptersDto>>> articleChapters();


}
