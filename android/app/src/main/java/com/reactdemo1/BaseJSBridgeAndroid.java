package com.reactdemo1;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class BaseJSBridgeAndroid extends ReactContextBaseJavaModule {
    public BaseJSBridgeAndroid(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        //1.作用：用于Js端调用Android端的桥梁（你可以理解为一个全局变量）
        //2.命名：避免使用时混淆，最好是以类名同名。
        //3.使用：在js端通过 NativeModules.BaseJSBridgeAndroid.xxxandroid方法xx();
        return "BaseJSBridgeAndroid";
    }

    @ReactMethod
    public void testAndroidToast(String msg){
        Toast.makeText(getReactApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
