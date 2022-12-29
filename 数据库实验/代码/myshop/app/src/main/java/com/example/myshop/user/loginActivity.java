package com.example.myshop.user;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.User;
import com.example.myshop.bean.studentBean;
import com.example.myshop.home.SeckillRecyclerViewAdapter;
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

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "loginActivity: ";
    private Button btnLogin;
    private EditText editTextName,editTextPwd;
    private TextView textViewRegister;
//    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button) this.findViewById(R.id.buttonLogin);
        editTextName=(EditText)this.findViewById(R.id.editTextName);
        editTextPwd=(EditText)this.findViewById(R.id.editTextPassword);
        textViewRegister=(TextView)this.findViewById(R.id.textViewRegister);
        btnLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
//        initData();
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                  login();
                break;
            case R.id.textViewRegister:
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login() {
        final String account = editTextName.getText().toString();
        String password = editTextPwd.getText().toString();
//        Date localDateTime = new Date();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localDateTime = df.format(date);
//        studentBean  student = new studentBean(1, account, password);
        User user = new User(1, account, password, 0, "", "", "", localDateTime, localDateTime);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.LOGIN)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("他妈的", e.getMessage());
                loginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(loginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                myApplication.setAccount(account);
//                Log.i("登录", response.body().string());
                processData(response.body().string());
                loginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
//        processData(rep);
    }

    String rep = "{\n" +
            "    \"msg\": \"注册成功!\",\n" +
            "    \"success\": true,\n" +
            "    \"details\": {\n" +
            "        \"id\": 7,\n" +
            "        \"userName\": \"cly\",\n" +
            "        \"password\": \"123\",\n" +
            "        \"gender\": 0,\n" +
            "        \"email\": \"\",\n" +
            "        \"mobile\": \"\",\n" +
            "        \"fileName\": \"\",\n" +
            "        \"createTime\": \"2021-04-14 00:39:16\",\n" +
            "        \"updateTime\": \"2021-04-14 00:39:16\"\n" +
            "    }\n" +
            "}";
    RESULT result;
    public void processData(String string) {
        result = JSON.parseObject(string, RESULT.class);
        Log.e(TAG, String.valueOf(result.getDetails()));
        JSONObject jsonObject = (JSONObject) result.getDetails();
        User user = new User((Integer) jsonObject.get("id"),
                (String) jsonObject.get("userName"),
                (String)jsonObject.get("password"),
                (Integer) jsonObject.get("gender"),
                (String) jsonObject.get("email"),
                (String) jsonObject.get("mobile"),
                (String) jsonObject.get("fileName"),
                (String) jsonObject.get("createTime"),
                (String) jsonObject.get("updateTime"));
//        User user = (User) result.getDetails();
        Intent intent = new Intent();
        intent.putExtra("user", user);
        this.setResult(RESULT_OK, intent);
        finish();
//        String msg = result.getMsg();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("userId", userId)
//                .build();
//        final Request request = new Request.Builder()
//                .url(constants.GET_USER_BY_ID)
//                .post(requestBody)
//                .build();
//        Call call = myApplication.getOkHttpClient().newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.e(TAG, "请求用户失败");
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                show(response.body().string());
//            }
//        });

    }

//    private void show(String string) {
//        RESULT result = new RESULT();
//        result = JSON.parseObject(string, RESULT.class);
//        User user = (User) result.getDetails();
//        myApplication.setId(user.getId());
//        myApplication.setPhone(user.getMobile());
//        myApplication.setEmail(user.getEmail());
//        myApplication.setAccount(user.getUserName());
//        int gender = user.getGender();
//        if(gender == 0) {
//            myApplication.setGender("男");
//        }
//        else if(gender == 1) {
//            myApplication.setGender("女");
//        }
//        else {
//            myApplication.setGender("保密");
//        }
//        myApplication.setAvator(user.);
//    }
    @Override
    public void onBackPressed() {
        User user = new User();
        user.setId(-1);
        Intent intent = new Intent();
        intent.putExtra("user", user);
        this.setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
