package com.chuck.epclient.dagger.modules.networking;

import com.chuck.epclient.dagger.scope.ApplicationScope;
import com.chuck.epclient.model.autovalue.AutoValueTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */
@Module
public class GsonModule {

    @Provides
    @ApplicationScope
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueTypeAdapterFactory.create())
                .create();
    }
}
