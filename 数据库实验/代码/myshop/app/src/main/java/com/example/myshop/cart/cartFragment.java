package com.example.myshop.cart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.CartBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.productBean;
import com.example.myshop.user.orderActivity;
import com.example.myshop.utils.constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Intent.ACTION_EDIT;


public class cartFragment extends baseFragment implements View.OnClickListener {
    private final String TAG =  this.getClass().getSimpleName();
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private shoppingCartAdapter adapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;

    @Override
    protected View initView() {
        View view = View.inflate(basecontext, R.layout.fragment_shopping_cart, null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );
        ivEmpty = (ImageView)view.findViewById( R.id.iv_empty );
        tvEmptyCartTobuy = (TextView)view.findViewById( R.id.tv_empty_cart_tobuy );
        ll_empty_shopcart = (LinearLayout)view.findViewById(R.id.ll_empty_shopcart);
        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
        initListener();
        return view;
    }

    private void initListener() {
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
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
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        if (adapter != null) {
            adapter.check_All_Or_None(true);
            adapter.checkAll();
        }
        llDelete.setVisibility(View.GONE);
        llCheckAll.setVisibility(View.VISIBLE);
    }

    private void showDelete() {
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");
        if (adapter != null) {
            adapter.check_All_Or_None(false);
            adapter.checkAll();
        }
        llDelete.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void showData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("userId", String.valueOf(myApplication.getId()))
                .build();
        final Request request = new Request.Builder()
                .url(constants.GET_ALL_CART)
                .post(requestBody)
                .build();
        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "请求购物车失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG, "请求购物车成功");
                show(response.body().string());
            }
        });

    }

    private void show(String string) {
        RESULT result = JSON.parseObject(string, RESULT.class);
        List<CartBean> cartBeanList = (List<CartBean>) result.getDetails();
        for(int i = 0; i < cartBeanList.size(); i++) {
            productBean product = new productBean();
            product.setId(cartBeanList.get(i).getId());
            product.setSelected(false);
            product.setNumber(cartBeanList.get(i).getQuantity());
//            product.setName(cartBeanList.get(i).get);
        }
        final List<productBean> productBeanList = CartStorage.getInstance().getAllData();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(productBeanList != null && productBeanList.size()>0) {
//有数据设置适配器
                    tvShopcartEdit.setVisibility(View.VISIBLE);
                    llCheckAll.setVisibility(View.VISIBLE);
                    ll_empty_shopcart.setVisibility(View.GONE);
                    adapter = new shoppingCartAdapter(basecontext, productBeanList, tvShopcartTotal, checkboxAll, cbAll);
                    recyclerview.setAdapter(adapter);
                    recyclerview.setLayoutManager(new LinearLayoutManager(basecontext, LinearLayoutManager.VERTICAL, false));
                }
                else {
                    emptyshoppingCart();
                }
            }
        });
//        List<productBean> productBeanList = CartStorage.getInstance().getAllData();

    }

    private void emptyshoppingCart() {
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2021-03-16 19:50:32 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            Intent intent = new Intent(basecontext, orderActivity.class);
            startActivity(intent);
            // Handle clicks for btnCheckOut
        } else if ( v == btnDelete ) {
            // Handle clicks for btnDelete
            adapter.deleteData();
            adapter.checkAll();

            if (adapter.getItemCount() == 0) {
                emptyshoppingCart();
            }
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }

}
