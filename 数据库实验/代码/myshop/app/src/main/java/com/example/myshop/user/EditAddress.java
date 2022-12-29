package com.example.myshop.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.CityBean;
import com.example.myshop.bean.ProvinceBean;
import com.example.myshop.bean.User;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.utils.ItemGroup;
import com.example.myshop.utils.TitleLayout;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditAddress extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "EditAddress";
    private TitleLayout ad_title;
    private EditText ad_name;
    private EditText ad_phone;
    private EditText ad_email;
    private ItemGroup ad_address;
    private EditText ad_address2;
    private ArrayList<ProvinceBean> optionItems1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> optionItems2 = new ArrayList<>();
    private OptionsPickerView pvOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        Log.e(TAG, "进入editAddress");
        ad_title = (TitleLayout)findViewById(R.id.ad_title);
        ad_name = (EditText)findViewById(R.id.ad_name);
        ad_phone = (EditText)findViewById(R.id.ad_phone);
        ad_email = (EditText)findViewById(R.id.ad_email);
        ad_address = (ItemGroup)findViewById(R.id.ad_address);
        ad_address2 = (EditText)findViewById(R.id.ad_address2);
        ad_address.setOnClickListener(this);
        initInfo();
        ad_title.getIv_backward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ad_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ad_name.getText().toString();
                String phone = ad_phone.getText().toString();
                String email = ad_email.getText().toString();
                String address2 = ad_address2.getText().toString();
                String address = ad_address.getContentEdt().toString();
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("address2", address2);
                setResult(RESULT_OK, intent);
                /**
                 * recycleview更新
                 */
//                UserAddress userAddress = new UserAddress();
//                userAddress.setAddressId();

//                edit();
                finish();
            }
        });

    }

//    private void edit(User user) {
////        studentBean  student = new studentBean(1, account, password);
////            User user = new User(1, account, password, 0, "", "", "", , LocalDateTime.now());
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        //MediaType  设置Content-Type 标头中包含的媒体类型值
//        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
//                , json);
//        Request request = new Request.Builder()
//                .url(constants.EDIT_USER)
//                .post(requestBody)
//                .build();
//
//        Call call = myApplication.getOkHttpClient().newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.e("他妈的", e.getMessage());
//                person.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(person.this, "提交失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.i("登录", response.body().string());
//                person.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(person.this, "提交成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }

    private void initInfo() {
        String name = getIntent().getBundleExtra("data").getString("name");
        String phone = getIntent().getBundleExtra("data").getString("phone");
        String address = getIntent().getBundleExtra("data").getString("address");
        String address2 = getIntent().getBundleExtra("data").getString("address2");
        Log.e(TAG, name+phone+address+address2);
        ad_name.setText(name);
        ad_phone.setText(phone);
        ad_address.getContentEdt().setHint(address);
//        ad_email.setText(email);
        ad_address2.setText(address2);
        String province_data = readJsonFile("province.json");
        String city_data = readJsonFile("city.json");
        Log.e(TAG, province_data);
        Gson gson = new Gson();
        optionItems1 = gson.fromJson(province_data, new TypeToken<ArrayList<ProvinceBean>>(){}.getType());
        ArrayList<CityBean> cityBeans = gson.fromJson(city_data, new TypeToken<ArrayList<CityBean>>(){}.getType());
        for(ProvinceBean provinceBean:optionItems1) {
            ArrayList<String> temp = new ArrayList<>();
            for(CityBean cityBean:cityBeans) {
                if(provinceBean.getProvince().equals(cityBean.getProvince())) {
                    temp.add(cityBean.getName());
                }
            }
            optionItems2.add(temp);
        }
    }

    //传入：asset文件夹中json文件名
    //返回：读取的String
    private String readJsonFile(String file) {
        StringBuilder newstringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getResources().getAssets().open(file);

            InputStreamReader isr = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(isr);

            String jsonLine;
            while ((jsonLine = reader.readLine()) != null) {
                newstringBuilder.append(jsonLine);
            }
            reader.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = newstringBuilder.toString();
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ad_address:
                Log.e(TAG, "老子点了");
                pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //选择了则显示并暂存LoginUser，退出时在保存至数据库
                        String tx = optionItems1.get(options1).getPickerViewText()
                                + optionItems2.get(options1).get(options2);
                        ad_address.getContentEdt().setText(tx);
                    }
                }).setCancelColor(Color.GRAY).build();
                pvOptions.setPicker(optionItems1, optionItems2);//二级选择器
                pvOptions.show();
                break;
        }
    }
}
