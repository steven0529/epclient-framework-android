package com.chuck.epclient.dagger.modules.networking;

import android.content.Context;

import com.chuck.epclient.dagger.modules.context.ContextModule;
import com.chuck.epclient.dagger.scope.ApplicationScope;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */

@Module(includes = ContextModule.class)
public class OkHttp3Module {

    private static final String OKHTTP_CACHE_NAME = "okhttp-cache";

    long cacheSizeKb;
    long timeoutSecs;
    HttpLoggingInterceptor.Level logLevel;


    public OkHttp3Module(long cacheSizeKb, long timeoutSecs, HttpLoggingInterceptor.Level logLevel) {
        this.cacheSizeKb = cacheSizeKb;
        this.timeoutSecs = timeoutSecs;
        this.logLevel = logLevel;
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(timeoutSecs, TimeUnit.SECONDS);

        if (cache != null)
            okHttpClientBuilder.cache(cache);

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);

        return okHttpClientBuilder.build();
    }

    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, cacheSizeKb * 1000);
    }

    @Provides
    @ApplicationScope
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), OKHTTP_CACHE_NAME);
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                Timber.i(message);
            }
        }).setLevel(logLevel);
    }
}
