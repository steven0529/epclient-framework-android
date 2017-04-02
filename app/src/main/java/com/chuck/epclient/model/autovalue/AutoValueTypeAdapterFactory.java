package com.chuck.epclient.model.autovalue;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 01/04/2017
 */
public abstract class AutoValueTypeAdapterFactory implements TypeAdapterFactory {

    /*
    * WARNING: THIS WILL ONLY WORK WITH AUTOVALUE MODELS WITHOUT GENERICS
    *
    * To trigger TypeAdapter generation, you need include a non-private static factory method that
    * accepts a Gson parameter and returns a TypeAdapter for your AutoValue type. From within this
    * method you can instantiate a new GsonTypeAdapter which will have been generated as an inner
    * class of your AutoValue generated implementation.
    *
    */
    private static final String TYPEADAPTER_METHOD_NAME = "typeAdapter";

    @SuppressWarnings("unchecked")
    public static TypeAdapterFactory create() {
        return new AutoValueTypeAdapterFactory() {
            @Override
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                Class<? super T> rawType = type.getRawType();
                if (!rawType.isAnnotationPresent(AutoGson.class)) {
                    return null;
                }

                String packageName = rawType.getPackage().getName();
                String className = rawType.getName().substring(packageName.length() + 1).replace('$', '_');
                String autoValueName = packageName + ".AutoValue_" + className;

                try {
                    Class<?> autoValueType = Class.forName(autoValueName);
                    Method typeAdapterMethod = autoValueType.getMethod(TYPEADAPTER_METHOD_NAME, Gson.class);
                    return (TypeAdapter<T>) typeAdapterMethod.invoke(gson, gson);
//            return (TypeAdapter<T>) gson.getAdapter(autoValueType);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("Could not load AutoValue type " + autoValueName, e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("typeAdapter method not found in class " + className, e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Invoke target exception in class " + className, e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Illegal access exception in class " + className, e);
                }
            }
        };
    }

}
