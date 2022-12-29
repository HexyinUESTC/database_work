package com.example.myshop.cart;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.CartBean;
import com.example.myshop.bean.productBean;
import com.example.myshop.user.person;
import com.example.myshop.utils.AddSubView;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class shoppingCartAdapter extends RecyclerView.Adapter<shoppingCartAdapter.Viewholder> {
    private final String TAG = "shoppingCartAdapter";
    private CheckBox cbAll;
    private TextView tvShopcartTotal;
    private CheckBox checkboxAll;
    private Context context;
    private List<productBean> productBeans;
    public shoppingCartAdapter(Context basecontext, List<productBean> productBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.context = basecontext;
        this.productBeans = productBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        showTotalPrice();
        setListener();
        checkAll();
    }
    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                productBean product = productBeans.get(position);
                product.setSelected(!product.getSelected());
                notifyItemChanged(position);
                showTotalPrice();
                checkAll();
            }
        });
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = checkboxAll.isChecked();
                check_All_Or_None(ischecked);
                showTotalPrice();
            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = checkboxAll.isChecked();
                check_All_Or_None(ischecked);
                showTotalPrice();
            }
        });
    }
    protected void check_All_Or_None(boolean isCheck) {
        if (productBeans != null && productBeans.size() > 0) {
            for(int i = 0; i < productBeans.size(); i++) {
                productBean product = productBeans.get(i);
                product.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }
    protected void checkAll() {
        if(productBeans != null && productBeans.size() > 0) {
            int number = 0;
            for(int i = 0; i < productBeans.size(); i++) {
                productBean product = productBeans.get(i);
                if (!product.getSelected()) {
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }
                else {
                    number++;
                }
            }
            if(number == productBeans.size()) {
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        }
        else {
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
        showTotalPrice();
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_shop_cart, null);
        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        final productBean product = productBeans.get(position);
        holder.cb_gov.setChecked(product.getSelected());
        Glide.with(context).load(constants.BASE_IMAGE_URL+product.getFile_name()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(product.getName());
        holder.tv_price_gov.setText("￥"+product.getPrice());
        holder.ddSubView.setValue(product.getNumber());
        holder.ddSubView.setMaxValue(8);
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setOnNumberChangeListenr(new AddSubView.OnNumberChangeListenr() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void OnNumberChange(int value) {
                product.setNumber(value);
                CartStorage.getInstance().updateData(product);
                CartBean cartBean = new CartBean();
//                Date date = new Date();
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String localDateTime = df.format(date);
                cartBean.setId(1);
                cartBean.setCreateTime(localDateTime);
                cartBean.setUpdateTime(localDateTime);
                cartBean.setCost((int)(product.getPrice()*value));
                cartBean.setProductId(product.getId());
                cartBean.setQuantity(value);
                updateCart(cartBean);
                showTotalPrice();
//                刷新适配器
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return productBeans.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void deleteData() {
        if (productBeans != null && productBeans.size() > 0) {
            for (int i = 0; i < productBeans.size(); i++) {
                productBean product = productBeans.get(i);
                if (product.getSelected()) {
                    productBeans.remove(product);
                    CartStorage.getInstance().deleteData(product);
                    CartBean cartBean = new CartBean();
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String localDateTime = df.format(date);
//                    Date date = new Date();
                    cartBean.setId(1);
                    cartBean.setCreateTime(localDateTime);
                    cartBean.setUpdateTime(localDateTime);
                    cartBean.setCost(1);
                    cartBean.setProductId(product.getId());
                    cartBean.setQuantity(1);
                    deleteCart(cartBean);
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            ddSubView = (AddSubView) itemView.findViewById(R.id.ddSubView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void updateCart(CartBean cartBean) {
        Gson gson = new Gson();
        String json = gson.toJson(cartBean);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.UPDATE_CART)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, e.getMessage());
//                ((Activity)context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "提交失败", Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i(TAG, response.body().string());
//                ((Activity)context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "提交成功", Toast.LENGTH_LONG).show();
//                    }
//                });
            }
        });
    }
    private void deleteCart(CartBean cartBean) {
        Gson gson = new Gson();
        String json = gson.toJson(cartBean);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.DELETE_CART)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, e.getMessage());
//                ((Activity)context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "提交失败", Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i(TAG, response.body().string());
//                ((Activity)context).runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, "提交成功", Toast.LENGTH_LONG).show();
//                    }
//                });
            }
        });
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "");
    }

    private double getTotalPrice() {
        double total = 0;
        if (productBeans != null && productBeans.size() > 0) {
            for (int i = 0; i < productBeans.size(); i++) {
                productBean goodsBean = productBeans.get(i);
                if (goodsBean.getSelected())
                    total += Double.parseDouble(String.valueOf(goodsBean.getPrice())) * Double.parseDouble(goodsBean.getNumber() + "");
            }
        }
        return total;
    }
}
