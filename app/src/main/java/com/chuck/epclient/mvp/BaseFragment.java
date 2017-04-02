package com.chuck.epclient.mvp;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */

public abstract class BaseFragment extends Fragment {

    protected ProgressDialog progressDialog;

    abstract void initDependencyInjector();

}
