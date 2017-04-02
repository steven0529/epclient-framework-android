package com.chuck.epclient.model.autovalue;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface AutoGson {
    // A reference to the AutoValue-generated class (e.g. AutoValue_MyClass). This is
    // necessary to handle obfuscation of the class names.
    public Class autoValueClass();
}