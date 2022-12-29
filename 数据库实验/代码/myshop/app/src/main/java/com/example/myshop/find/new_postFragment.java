package com.example.myshop.find;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.NewPostBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.ResultBeanData;
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
import okhttp3.Request;
import okhttp3.Response;

public class new_postFragment extends baseFragment {
    public final String TAG = "new_postFragment";
    private RecyclerView recyclerView;
    private ImageButton ib_top;
    private ResultBeanData resultBeanData;
    private new_postFragmentAdapter adapter;
    private NewPostBean add_newPostBean;
    public new_postFragment(NewPostBean newPostBean) {
        add_newPostBean = newPostBean;
    }
    @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_new_post, null);
        recyclerView = view.findViewById(R.id.rv_home_post2);
        ib_top = view.findViewById(R.id.ib_top_post2);
        initListener();
        return view;
    }

    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                recyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getData();
    }

    private void getData() {
        Request request = new Request.Builder()
                .url(constants.GET_ALL_NEW)
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
        Gson gson = new Gson();
        Type type4 = new TypeToken<RESULT<List<NewPostBean>>>(){}.getType() ;
        RESULT<List<NewPostBean>>  k = gson.fromJson(json,type4);
        final List<NewPostBean> newPostBeans = (List<NewPostBean>) k.getDetails();
//        int ind = newPostBeans.size();
        if(add_newPostBean != null) newPostBeans.add(add_newPostBean);
        Log.e(TAG, String.valueOf(newPostBeans));
//        Log.e(TAG, detailsBean.getHotproduct().toString());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (newPostBeans != null) {
//            有数据则设置适配器
                    adapter = new new_postFragmentAdapter(basecontext, newPostBeans);
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
