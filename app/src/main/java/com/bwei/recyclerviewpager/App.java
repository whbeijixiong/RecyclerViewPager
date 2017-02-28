package com.bwei.recyclerviewpager;

import android.app.Application;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作    者 ： 文欢
 * 时    间 ： 2017/2/24.
 * 描    述 ：
 * 修改时间 ：
 */

public class App extends Application {

    public static ImageLoader imageLoader;
    public static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        gson = new Gson();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(new ImageLoaderConfiguration.Builder(getApplicationContext()).build());
    }
}
