package com.example.myshop.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.ResultBeanData;
import com.example.myshop.utils.constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class homeFragment extends baseFragment {
    private static final String TAG = homeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomeFragmentAdapter adapter;
    private ResultBeanData resultBeanData;
   @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        recyclerView = view.findViewById(R.id.rv_home);
        ib_top = view.findViewById(R.id.ib_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), searchActivity.class);
                startActivity(intent);
            }
        });
        tv_message_home = view.findViewById(R.id.tv_message_home);
        initListener();
        return view;
    }

    private void initListener() {
//置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                recyclerView.scrollToPosition(0);
            }
        });

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(basecontext, searchActivity.class);
                startActivity(intent);
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        getData();

    }

    public void getData() {
        Request request = new Request.Builder()
                .url(constants.HOME_DATA)
                .get()
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

    private void processData(String json) {
        Log.e(TAG, "首页请求"+json);
        resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        final ResultBeanData.DetailsBean detailsBean = (ResultBeanData.DetailsBean)resultBeanData.getDetails();
//        Log.e(TAG, detailsBean.getHotproduct().toString());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (detailsBean != null) {
//            有数据则设置适配器
                    adapter = new HomeFragmentAdapter(basecontext, detailsBean);
                    recyclerView.setAdapter(adapter);
//            设置布局管理者
                    GridLayoutManager manager = new GridLayoutManager(basecontext, 1);
//            设置跨度大小监听
                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if(position <= 2) {
                                ib_top.setVisibility(View.GONE);
                            }else {
                                ib_top.setVisibility(View.VISIBLE);
                            }
                            return 1;
                        }
                    });
                    recyclerView.setLayoutManager(manager);
                }
                else {
                    Log.e(TAG, "适配器没有数据");
                }
            }
        });

    }
}
