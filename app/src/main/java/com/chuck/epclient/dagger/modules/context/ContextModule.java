package com.chuck.epclient.dagger.modules.context;

import android.content.Context;

import com.chuck.epclient.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 02/04/2017
 */
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
