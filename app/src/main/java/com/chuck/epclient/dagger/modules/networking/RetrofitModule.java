package com.chuck.epclient.dagger.modules.networking;

import com.chuck.epclient.dagger.scope.ApplicationScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */

@Module (includes = {OkHttp3Module.class, GsonModule.class})
public class RetrofitModule {

    String baseUrl;

    public RetrofitModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Converter.Factory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @ApplicationScope
    public GsonConverterFactory gsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

}
