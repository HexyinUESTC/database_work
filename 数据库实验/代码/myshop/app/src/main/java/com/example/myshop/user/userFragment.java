package com.example.myshop.user;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.User;
import com.example.myshop.cart.mapActivity;
import com.example.myshop.utils.PhotoUtils;
import com.example.myshop.utils.RoundImageView;
import com.example.myshop.utils.constants;
import com.example.myshop.utils.photosUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class userFragment extends baseFragment implements View.OnClickListener {
    private final String TAG = "userFragment";
    private photosUtils photosUtils = new photosUtils();
    private TextView tv_username;
    private PopupWindow mPopWindow;
    private RoundImageView ib_user_icon_avator;
    private TextView tv_user_location;
    private TextView tv_user_receive;
    private final int LOGIN = 0;
    private final int EDIT = 1;

    private void findviews(View view) {
        tv_username = view.findViewById(R.id.tv_username);
        ib_user_icon_avator = view.findViewById(R.id.ib_user_icon_avator);
        tv_user_location = view.findViewById(R.id.tv_user_location);
        tv_user_receive = view.findViewById(R.id.tv_user_receive);
        tv_username.setText(myApplication.getAccount());
        ib_user_icon_avator.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
        tv_user_receive.setOnClickListener(this);
        tv_user_location.setOnClickListener(this);
        ib_user_icon_avator.setOnClickListener(this);
        tv_username.setOnClickListener(this);
    }
    @Override
    protected View initView() {
        View view = View.inflate(basecontext, R.layout.fragment_user, null);
        findviews(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_username:
                Intent loginintent = new Intent(basecontext, loginActivity.class);
                startActivityForResult(loginintent, LOGIN);
                break;
            case R.id.ib_user_icon_avator:
                if(myApplication.getAccount().equals("Default")) {

                }
                else {
                    Intent editIntent = new Intent(basecontext, person.class);
                    startActivityForResult(editIntent, EDIT);
//                    tv_user_location.setText(myApplication.getAccount());
//                    ib_user_icon_avator.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
                }
                break;
            case R.id.tv_user_location:
                Intent intent = new Intent(getActivity(), Address.class);
                startActivity(intent);
                break;
            case R.id.tv_user_receive:
                Intent intent_map = new Intent(getActivity(), orderActivity.class);
                startActivity(intent_map);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //data为Activity_B中回传的Intent
        //change01、change02即为回传的值
//        String change01 = data.getStringExtra("change01");
//        String change02 = data.getStringExtra("change02");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {//resultCode为回传的标记，我在B中回传的是resultCode
            case LOGIN:
//                int loginUserId = data.getIntExtra("userId", myApplication.getId());

//                requestUser(loginUserId);
                User user = (User) data.getSerializableExtra("user");
                Log.e(TAG, String.valueOf(user));
//                Glide.with(basecontext).load(constants.BASE_IMAGE_URL+user.getFileName()).into(ib_user_icon_avator);
                if(user.getId()!=-1)
                showUser(user);
                break;
            case EDIT:
                tv_username.setText(myApplication.getAccount());
                ib_user_icon_avator.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
                break;
            default:
                break;
        }
    }

    private void requestUser(int userId) {
        RequestBody requestBody = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .build();
        final Request request = new Request.Builder()
                .url(constants.GET_USER_BY_ID)
                .post(requestBody)
                .build();
        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "请求用户失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                show(response.body().string());
            }
        });

    }
    private void showUser(final User user) {
        myApplication.setId(user.getId());
        myApplication.setPhone(user.getMobile());
        myApplication.setEmail(user.getEmail());
        myApplication.setAccount(user.getUserName());
        myApplication.setAvator_Path(user.getFileName());
        int gender = user.getGender();
        if(gender == 0) {
            myApplication.setGender("男");
        }
        else if(gender == 1) {
            myApplication.setGender("女");
        }
        else {
            myApplication.setGender("保密");
        }
        tv_username.setText(user.getUserName());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_logo);
        myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
        ib_user_icon_avator.setImageBitmap(bitmap );
//        String url = constants.BASE_IMAGE_URL+"//"+String.valueOf(user.getId())+".jpg";


        /*
        final String url = constants.BASE_IMAGE_URL+user.getFileName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap avator = getBitmapGlide(url);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ib_user_icon_avator.setImageBitmap(avator);
                        tv_user_location.setText(user.getUserName());
                    }
                });
//        Glide.with(basecontext).load(url).into(ib_user_icon_avator);
                myApplication.setAvator(photosUtils.bitmap2byte(avator));
            }
        }).start();
        */

    }

    private void show(String string) {
        RESULT result;
        result = JSON.parseObject(string, RESULT.class);
        final User user = (User) result.getDetails();
        myApplication.setId(user.getId());
        myApplication.setPhone(user.getMobile());
        myApplication.setEmail(user.getEmail());
        myApplication.setAccount(user.getUserName());
        int gender = user.getGender();
        if(gender == 0) {
            myApplication.setGender("男");
        }
        else if(gender == 1) {
            myApplication.setGender("女");
        }
        else {
            myApplication.setGender("保密");
        }
        final String url = constants.BASE_IMAGE_URL+"//"+String.valueOf(user.getId())+".jpg";

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap avator = getBitmapGlide(url);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ib_user_icon_avator.setImageBitmap(avator);
                        tv_user_location.setText(user.getUserName());
                    }
                });
//        Glide.with(basecontext).load(url).into(ib_user_icon_avator);
                myApplication.setAvator(photosUtils.bitmap2byte(avator));
            }
        }).start();

    }

    public  Bitmap getBitmapGlide(String url) {
        try {
            return Glide.with(basecontext)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
