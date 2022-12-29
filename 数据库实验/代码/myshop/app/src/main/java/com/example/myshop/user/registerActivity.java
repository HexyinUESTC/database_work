package com.example.myshop.user;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.User;
import com.example.myshop.bean.studentBean;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registerActivity extends AppCompatActivity {
//    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
//        initData();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
//    private void initData() {
//        okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .callTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .build();
//
//    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void register() {
        EditText editTextName = (EditText)findViewById(R.id.editTextName);
        EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        EditText editTextPassword2 = (EditText)findViewById(R.id.editTextPassword2);
        String name = editTextName.getText().toString();
        String pwd = editTextPassword.getText().toString();
        String pwd2 = editTextPassword2.getText().toString();
        if(name.length()<1) {
            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (pwd.length() < 1){
            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!pwd.equals(pwd2)){
            Toast.makeText(this,"两次密码不相同.", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
//            Date date = new Date();
//            Date localDateTime = new Date();
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String localDateTime = df.format(date);
//            studentBean student = new studentBean(1, name, password);
            User user = new User(1, name, pwd, 0, "", "", "",localDateTime, localDateTime);
            Gson gson = new Gson();
            String json = gson.toJson(user);
            //MediaType  设置Content-Type 标头中包含的媒体类型值
            RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                    , json);
            Request request = new Request.Builder()
                    .url(constants.REGISTER)
                    .post(requestBody)
                    .build();

            Call call = myApplication.getOkHttpClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e("他妈的", e.getMessage());
                    registerActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(registerActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.i("登录", response.body().string());
                    registerActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }

        finish();
    }
}
