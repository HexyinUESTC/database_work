package com.example.myshop.base;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.bean.CartBean;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.productBean;
import com.example.myshop.cart.CartStorage;
import com.example.myshop.type.chatActivity;
import com.example.myshop.user.orderActivity;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GoodInfoActivity extends Activity implements View.OnClickListener {
    private final String TAG = "GoodInfoActivity";
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
//    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private RecyclerView recycler_comment;
    private goodInfoAdapter adapter;
    private Button btn_more;
    private static final String PRODUCTBEAN = "productBean";
    private productBean product;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2021-03-16 16:36:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        btn_more = (Button) findViewById(R.id.btn_more);
        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
//        wbGoodInfoMore = (WebView)findViewById( R.id.wb_good_info_more );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );
        recycler_comment = (RecyclerView)findViewById(R.id.recycler_comment);

        ibGoodInfoBack.setOnClickListener( this );
        ibGoodInfoMore.setOnClickListener( this );
        btnGoodInfoAddcart.setOnClickListener( this );
        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
//        btn_more.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2021-03-16 16:36:57 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            finish();
            // Handle clicks for ibGoodInfoBack
        } else if(v == tvGoodInfoCart) {
            CartStorage.getInstance().addData(product);
            addIntoCart(product);
            Toast.makeText(this, "添加到购物车", Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            Intent intent = new Intent(GoodInfoActivity.this, orderActivity.class);
            startActivity(intent);
            Toast.makeText(this, "提交订单", Toast.LENGTH_SHORT);
        }
        else if (v == tvGoodInfoCallcenter) {
            Intent intent = new Intent(GoodInfoActivity.this, chatActivity.class);
            startActivity(intent);
            Toast.makeText(this, "客户中心", Toast.LENGTH_SHORT).show();
        }else if(v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }else if(v == tv_more_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if(v == tv_more_search) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if(v == tv_more_home) {
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addIntoCart(productBean product) {
        CartBean cartBean = new CartBean();
        cartBean.setProductId(product.getId());
        cartBean.setId(1);
        cartBean.setUserId(myApplication.getId());
        cartBean.setQuantity(1);
        cartBean.setCost(product.getPrice());
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String localDateTime = df.format(date);
        Gson gson = new Gson();
        String json = gson.toJson(cartBean);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.ADD_CART)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, e.getMessage());
                GoodInfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GoodInfoActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                myApplication.setAccount(account);
                Log.i(TAG, response.body().string());
                GoodInfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GoodInfoActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_info);
        findViews();
//        processData();
        product = (productBean) getIntent().getSerializableExtra(PRODUCTBEAN);
        setDataForView(product);
    }

    private void setDataForView(productBean product) {
        Glide.with(this).load(constants.BASE_IMAGE_URL+product.getFile_name()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(product.getName());
        tvGoodInfoPrice.setText("￥"+product.getPrice());
//        setWebViewData(String.valueOf(product.getId()));
    }

//    private void setWebViewData(String id) {
//        if(id!=null) {
//            wbGoodInfoMore.loadUrl("http://www.baidu.com");
//            WebSettings webSettings = wbGoodInfoMore.getSettings();
//            webSettings.setUseWideViewPort(true);
//            webSettings.setJavaScriptEnabled(true);
//            webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
//            wbGoodInfoMore.setWebViewClient(new WebViewClient(){
////                @Override
////                public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                    view.loadUrl(url);
////                    return true;
////                }
//
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        view.loadUrl(request.getUrl().toString());
//                    }
//                    return true;
//                }
//            });
//        }
//    }
    private void processData() {
        String json = "";
        Log.e(TAG, "商品评价请求"+json);
        Gson gson = new Gson();
        Type type4 = new TypeToken<RESULT<List<HotPostBean>>>(){}.getType() ;
        RESULT<List<HotPostBean>>  k = gson.fromJson(json,type4);
        final List<HotPostBean> hotPostBeans = (List<HotPostBean>) k.getDetails();
        Log.e(TAG, String.valueOf(hotPostBeans));
//        resultBeanData = JSON.parseObject(json, ResultBeanData.class);
//        final List<HotPostBean> hotPostBeans= (List<HotPostBean>) resultBeanData.getDetails();
//        Log.e(TAG, detailsBean.getHotproduct().toString());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (hotPostBeans != null) {
//            有数据则设置适配器
                    adapter = new goodInfoAdapter(GoodInfoActivity.this, hotPostBeans);
                    recycler_comment.setAdapter(adapter);
//            设置布局管理者
                    GridLayoutManager manager = new GridLayoutManager(GoodInfoActivity.this, 1);
//            设置跨度大小监听
//                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                        @Override
//                        public int getSpanSize(int position) {
//                            if(position <= 2) {
//                                ib_top.setVisibility(View.GONE);
//                            }else {
//                                ib_top.setVisibility(View.VISIBLE);
//                            }
//                            return 1;
//                        }
//                    });
                    recycler_comment.setLayoutManager(manager);
                }
                else {
                    Log.e(TAG, "适配器没有数据");
                }
            }
        });

    }

}
