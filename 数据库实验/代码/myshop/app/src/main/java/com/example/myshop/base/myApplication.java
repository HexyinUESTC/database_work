package com.example.myshop.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.FileUtils;
import android.util.LruCache;

import com.example.myshop.R;
import com.example.myshop.utils.photosUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class myApplication extends Application {
//    public static photosUtils photoUtils = new photosUtils();
    private static Context mContext;
    private static String Account = "Default";
    private static int id = 1;
    private static String email="";
    private static String phone="";
    private static String gender="女";
    private static String password;
    private static String avator_Path;

    public static Context getmContext() {
        return mContext;
    }

    public static void setmContext(Context mContext) {
        myApplication.mContext = mContext;
    }

    public static String getAvator_Path() {
        return avator_Path;
    }

    public static void setAvator_Path(String avator_Path) {
        myApplication.avator_Path = avator_Path;
    }

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
    private static byte[] avator;
    public static byte[] getAvator() {
        return avator;
    }

    public static void setAvator(byte[] avator) {
        myApplication.avator = avator;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        phone = phone;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        gender = gender;
    }

    public static String getAccount() {
        return Account;
    }

    public static void setAccount(String account) {
        Account = account;
    }
    private static OkHttpClient okHttpClient;
    private static LruCache<String, Bitmap> mMemoryCache;

    public static String getPassword() {
        return password;
    }
    public void setPassword(String word) {
        password = word;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        int maxMemory = (int) (Runtime.getRuntime().totalMemory()/1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getRowBytes() * bitmap.getHeight()/1024;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                if (!oldValue.isRecycled()) {
                    oldValue.recycle();
                }
            }
        };
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
