package com.yc.repository.manager;

import androidx.annotation.MainThread;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author yangchao
 * @description: TODO
 * @date 2019-12-01
 */
public class RequestManager {

    public static volatile RequestManager mInstance = null;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    private String mBaseUrl = "";
    private Retrofit mRetrofit;
    private boolean mDebug;
    private OkHttpClient mCurrentClient;

    private RequestManager( String basrUrl, boolean isDebug) {

        this.mDebug = isDebug;
        this.mBaseUrl = basrUrl;

    }

    public static RequestManager init( String baseUrl, boolean isDebug) {
        if (mInstance == null) {
            synchronized (RequestManager.class) {
                if (mInstance == null) {
                    mInstance = new RequestManager(baseUrl, isDebug);
                }
            }
        }
        return mInstance;
    }

    private <T> T getService(final Class<T> service) {
        if (mRetrofit==null) {
            mRetrofit =
                new Retrofit.Builder()
                    .client(getOKHttpClient())
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(
//                    JsonConverterFactory.create(TokenManager.packageTokenWithHeader()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
//        }
        return mRetrofit.create(service);
    }

    private OkHttpClient getOKHttpClient() {
        mCurrentClient =
            new OkHttpClient.Builder()
//                .certificatePinner(getCertificatePinner())//https认证
//                    .sslSocketFactory(getSSLSocketFactory(), getX509TrustManager())
                .addInterceptor(getLoggingInterceptor()).build();
        return mCurrentClient;
    }

    /**
     * 打印log intercept 和 更改header
     */
    private LoggingInterceptor getLoggingInterceptor() {
        return new LoggingInterceptor.Builder()
            .loggable(mDebug)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("NetLog:Request")
            .response("NetLog:Result")
//            .addHeader()
            .executor(executor) // 启动线程打印日志
            .build();
    }
    /**
     * 主线程调用,异步线程可能会有问题,绝大多数调用者也为主线程
     */
    @MainThread
    public static <T> T get(final Class<T> service) {
        return mInstance.getService(service);
    }

}
