package com.chuck.epclient.dagger.components;

import com.chuck.epclient.dagger.modules.networking.RetrofitModule;
import com.chuck.epclient.dagger.scope.ApplicationScope;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */
@ApplicationScope
@Component(modules = RetrofitModule.class)
public interface NetworkComponent {

    Retrofit getRetrofit();

}
