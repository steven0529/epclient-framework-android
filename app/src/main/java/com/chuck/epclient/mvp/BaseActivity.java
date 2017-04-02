package com.chuck.epclient.mvp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */

public abstract class BaseActivity extends AppCompatActivity {

    abstract void initDependencyInjector();

}
