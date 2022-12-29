package com.example.myshop.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.user.Address;
import com.example.myshop.user.EditAddress;
import com.example.myshop.user.addressRecyclerViewAdapter;
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
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class searchActivity extends AppCompatActivity {
    private String rep = "{\n" +
            "    \"msg\": \"请求成功！\",\n" +
            "    \"success\": true,\n" +
            "    \"details\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"userId\": 1,\n" +
            "            \"userName\": \"admin\",\n" +
            "            \"avator\": \"/baby_1.jpg\",\n" +
            "            \"productId\": 1,\n" +
            "            \"productImg\": \"/baby_1.jpg\",\n" +
            "            \"commentNum\": 2,\n" +
            "            \"likeNum\": 1,\n" +
            "            \"sendNum\": 2,\n" +
            "            \"isLike\": 0,\n" +
            "            \"isSend\": 0,\n" +
            "            \"description\": \"花椒\",\n" +
            "            \"createTime\": \"2020-05-16 00:08:37\",\n" +
            "            \"updateTime\": \"2021-04-17 00:17:52\",\n" +
            "            \"version\": 55\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 2,\n" +
            "            \"userId\": 1,\n" +
            "            \"userName\": \"admin\",\n" +
            "            \"avator\": \"/baby_1.jpg\",\n" +
            "            \"productId\": 1,\n" +
            "            \"productImg\": \"/baby_1.jpg\",\n" +
            "            \"commentNum\": 2,\n" +
            "            \"likeNum\": 2,\n" +
            "            \"sendNum\": 2,\n" +
            "            \"isLike\": 1,\n" +
            "            \"isSend\": 0,\n" +
            "            \"description\": \"花椒\",\n" +
            "            \"createTime\": \"2020-05-16 00:08:37\",\n" +
            "            \"updateTime\": \"2020-05-16 00:08:37\",\n" +
            "            \"version\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": 3,\n" +
            "            \"userId\": 2,\n" +
            "            \"userName\": \"tsa\",\n" +
            "            \"avator\": \"/baby_1.jpg\",\n" +
            "            \"productId\": 1,\n" +
            "            \"productImg\": \"/baby_1.jpg\",\n" +
            "            \"commentNum\": 2,\n" +
            "            \"likeNum\": 2,\n" +
            "            \"sendNum\": 2,\n" +
            "            \"isLike\": 1,\n" +
            "            \"isSend\": 0,\n" +
            "            \"description\": \"花椒\",\n" +
            "            \"createTime\": \"2020-05-16 00:08:37\",\n" +
            "            \"updateTime\": \"2020-05-16 00:08:37\",\n" +
            "            \"version\": 1\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public String repfirst = "{\"msg\": \"请求成功！\",\n" +
            "\"success\": true,\n" +
            " \"details\": []" +
            "}";
    private SearchView searchView;

    private RecyclerView rv_search;
    private searchRecyclerViewAdapter searchRecycleViewAdapter;
    private final String TAG = "searchActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rv_search = (RecyclerView) findViewById(R.id.rv_search);
        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击键盘上的搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                processData(rep);
//                System.out.println("我收到了" + string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }


    @Override
    public  void onResume() {
        super.onResume();
        processData(repfirst);
//        showData();
    }

    private void showData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("message", "")
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
        Type type4 = new TypeToken<RESULT<List<HotPostBean>>>(){}.getType() ;
        RESULT<List<HotPostBean>>  k = gson.fromJson(string,type4);
        final List<HotPostBean> myPostBeans = (List<HotPostBean>) k.getDetails();
        Log.e(TAG, String.valueOf(myPostBeans));

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(myPostBeans != null && myPostBeans.size()>0) {
//有数据设置适配器
                    searchRecycleViewAdapter = new searchRecyclerViewAdapter(searchActivity.this, myPostBeans);
                    rv_search.setAdapter(searchRecycleViewAdapter);
                    rv_search.setLayoutManager(new LinearLayoutManager(searchActivity.this, LinearLayoutManager.VERTICAL, false));
                }
            }
        });
    }
}
