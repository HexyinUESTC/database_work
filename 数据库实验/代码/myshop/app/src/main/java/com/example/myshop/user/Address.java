package com.example.myshop.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Address extends AppCompatActivity implements View.OnClickListener {
    private final int EDIT_ADDRESS = 6;
    private int item = 0;
    String rep = "{\n" +
            "    \"msg\": \"请求成功\",\n" +
            "    \"success\": true,\n" +
            "    \"details\": [\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"userId\": 2,\n" +
            "            \"address\": \"重庆市南岸区南山街道\",\n" +
            "            \"remark\": \"重庆邮电大学\",\n" +
            "            \"isdefault\": 0,\n" +
            "            \"createTime\": \"2019-06-03 10:32:38\",\n" +
            "            \"updateTime\": \"2019-06-03 10:32:39\",\n" +
            "            \"buyerPhone\": \"17358459187\",\n" +
            "            \"buyerName\": \"夏宇阳\",\n" +
            "            \"areaCode\": \"413000\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"userId\": 2,\n" +
            "            \"address\": \"湖南省益阳市赫山区\",\n" +
            "            \"remark\": \"箴言中学\",\n" +
            "            \"isdefault\": 1,\n" +
            "            \"createTime\": \"2019-06-03 10:32:37\",\n" +
            "            \"updateTime\": \"2019-06-03 10:32:39\",\n" +
            "            \"buyerPhone\": \"17358459187\",\n" +
            "            \"buyerName\": \"夏宇阳\",\n" +
            "            \"areaCode\": \"413000\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private TextView tv_address_edit;
    private RecyclerView addressrecyclerview;
    private LinearLayout ll_delete_address;
    private CheckBox cb_all_address;
    private Button btn_delete_address;
    private Button btn_collection_address;
    private LinearLayout ll_empty_shopcart;
    private addressRecyclerViewAdapter adapter;
    private final String TAG =  this.getClass().getSimpleName();
    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        tv_address_edit = (TextView) findViewById(R.id.tv_address_edit);
        addressrecyclerview = (RecyclerView) findViewById(R.id.addressrecyclerview);
        ll_delete_address = (LinearLayout) findViewById(R.id.ll_delete_address);
        cb_all_address = (CheckBox) findViewById(R.id.cb_all_address);
        btn_delete_address = (Button) findViewById(R.id.btn_delete_address);
        btn_collection_address = (Button) findViewById(R.id.btn_collection_address);
        ll_empty_shopcart = (LinearLayout)findViewById(R.id.ll_empty_shopcart);
        btn_delete_address.setOnClickListener(this);
        btn_collection_address.setOnClickListener(this);
        initListener();
    }

    private void initListener() {
        tv_address_edit.setTag(ACTION_EDIT);
        tv_address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (ACTION_EDIT == action) {
                    showDelete();
                }
                else {
                    hideDelete();
                }
            }
        });
    }

    private void hideDelete() {
        tv_address_edit.setTag(ACTION_EDIT);
        tv_address_edit.setText("编辑");
        if (adapter != null) {
            adapter.check_All_Or_None(1);
//            adapter.checkAll();
//            adapter.showTotalPrice();
        }
        ll_delete_address.setVisibility(View.GONE);
        cb_all_address.setVisibility(View.VISIBLE);
    }

    private void showDelete() {
        tv_address_edit.setTag(ACTION_COMPLETE);
        tv_address_edit.setText("完成");
        if (adapter != null) {
            adapter.check_All_Or_None(0);
//            adapter.checkAll();
        }
        ll_delete_address.setVisibility(View.VISIBLE);
        cb_all_address.setVisibility(View.GONE);
    }
    @Override
    public void onResume() {
        super.onResume();
        processData(rep);
//        showData();
    }

    private void showData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("userId", String.valueOf(1))
                .build();
        Request request = new Request.Builder()
                .url(constants.ALL_ADDRESS)
                .post(requestBody)
                .build();
        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "首页请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
//                Log.e(TAG, response.body().string());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            processData(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                processData(response.body().string());
            }
        });

    }


    private void processData(String string) {
        Log.e(TAG, string);

//        List<productBean> productBeanList = CartStorage.getInstance().getAllData();
//        RESULT result = new Gson().fromJson(string, RESULT.class);

//        final List<UserAddress> userAddressList = (List<UserAddress>) result.getDetails();
        Gson gson = new Gson();
        Type type4 = new TypeToken<RESULT<List<UserAddress>>>(){}.getType() ;
        RESULT<List<UserAddress>>  k = gson.fromJson(string,type4);
        final List<UserAddress> userAddressList = (List<UserAddress>) k.getDetails();
        Log.e(TAG, String.valueOf(userAddressList));

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(userAddressList != null && userAddressList.size()>0) {
//有数据设置适配器
                    tv_address_edit.setVisibility(View.VISIBLE);
                    cb_all_address.setVisibility(View.VISIBLE);
                    ll_empty_shopcart.setVisibility(View.GONE);
                    adapter = new addressRecyclerViewAdapter(Address.this, userAddressList,cb_all_address);
                    addressrecyclerview.setAdapter(adapter);
                    addressrecyclerview.setLayoutManager(new LinearLayoutManager(Address.this, LinearLayoutManager.VERTICAL, false));
                    adapter.setOnItemClickListener(new addressRecyclerViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, UserAddress userAddress) {
                            item = position;
                            Log.e(TAG, "item点击"+String.valueOf(userAddress));
                            Intent intent = new Intent(Address.this, EditAddress.class);
                            Bundle bundle  = new Bundle();
                            bundle.putString("name", userAddress.getBuyerName());
                            bundle.putString("phone", userAddress.getBuyerPhone());
                            bundle.putString("address", userAddress.getAddress());
                            bundle.putString("address2", userAddress.getRemark());
                            intent.putExtra("data", bundle);
                            startActivityForResult(intent, EDIT_ADDRESS);
                        }
                    });
                }
                else {
                    emptyshoppingCart();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //data为Activity_B中回传的Intent
        //change01、change02即为回传的值
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {//resultCode为回传的标记，我在B中回传的是resultCode
            case EDIT_ADDRESS:
//                int loginUserId = data.getIntExtra("userId", myApplication.getId());

//                requestUser(loginUserId);
//                User user = (User) data.getSerializableExtra("user");
//                Log.e(TAG, String.valueOf(user));
//                Glide.with(basecontext).load(constants.BASE_IMAGE_URL+user.getFileName()).into(ib_user_icon_avator);
//                showUser(user);
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String phone = data.getStringExtra("phone");
                    String address = data.getStringExtra("address");
                    String address2 = data.getStringExtra("address2");
                    Log.e(TAG, "aaaaaaaaaaaaaaa"+name+phone+address2+address);
                    adapter.updateItem(item, name, phone, address, address2);
                }

                break;
            default:
                break;
        }
    }

    private void emptyshoppingCart() {
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tv_address_edit.setVisibility(View.GONE);
        ll_delete_address.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if ( v == btn_delete_address ) {
            // Handle clicks for btnDelete
            adapter.deleteData();
//            adapter.checkAll();
            if (adapter.getItemCount() == 0) {
                emptyshoppingCart();
            }
        } else if ( v == btn_collection_address ) {
            // Handle clicks for btnCollection
        }
    }
}
