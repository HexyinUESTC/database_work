package com.example.myshop.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.home.searchActivity;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class addressRecyclerViewAdapter extends RecyclerView.Adapter<addressRecyclerViewAdapter.ViewHolder> {
    public  final String TAG = "addressRecyclerViewAdapter";
    private Context context;
    private List<UserAddress> userAddressList;
    private CheckBox checkBox;
    public addressRecyclerViewAdapter(Context context, List<UserAddress> userAddressList, CheckBox cb_all_address) {
        this.context = context;
        this.userAddressList = userAddressList;
        this.checkBox = cb_all_address;
        setListener();
    }

    private void setListener() {


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischecked = checkBox.isChecked();
                if (ischecked)
                    check_All_Or_None(1);
                else
                    check_All_Or_None(0);
            }
        });
    }

    public void check_All_Or_None(int ischecked) {
        if (userAddressList != null && userAddressList.size() > 0) {
            for(int i = 0; i < userAddressList.size(); i++) {
                UserAddress userAddress = userAddressList.get(i);
                userAddress.setIsDefault(ischecked);
                notifyItemChanged(i);
            }
        }
    }

    public void deleteData() {
        if (userAddressList != null && userAddressList.size() > 0) {
            for (int i = 0; i < userAddressList.size(); i++) {
                UserAddress address = userAddressList.get(i);
                if (address.getIsDefault()==1) {
                    userAddressList.remove(address);
                    AddressStorage.getInstance().deleteData(address);
                    deleteAddress(address);
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    private void deleteAddress(UserAddress address) {
        Gson gson = new Gson();
        String json = gson.toJson(address);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.DELETE_ADDRESS)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @SuppressLint("LongLogTag")
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

            @SuppressLint("LongLogTag")
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_address, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final UserAddress userAddress = (UserAddress) userAddressList.get(position);
        Boolean is = false;
        if (userAddress.getIsDefault() == 1) is = true;
        holder.cb_gov_address.setChecked(is);
        holder.tv_desc_gov_address.setText(userAddress.getAddress());
        holder.tv_desc_gov_address_detail.setText(userAddress.getRemark());
        holder.tv_name_gov.setText(userAddress.getBuyerName());
        holder.tv_phone_gov.setText(userAddress.getBuyerPhone());
        holder.cb_gov_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddress userAddress = userAddressList.get(position);
                if (userAddress.getIsDefault()==1) userAddress.setIsDefault(0);
                else userAddress.setIsDefault(1);
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return userAddressList.size();
    }

    public void updateItem(int item, String name, String phone, String address, String address2) {
        UserAddress userAddress = userAddressList.get(item);
        userAddress.setBuyerName(name);
        userAddress.setAddress(address);
        userAddress.setBuyerPhone(phone);
        userAddress.setRemark(address2);
        notifyItemChanged(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov_address;
        private TextView tv_desc_gov_address;
        private TextView tv_desc_gov_address_detail;
        private TextView tv_name_gov;
        private TextView tv_phone_gov;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cb_gov_address = (CheckBox) itemView.findViewById(R.id.cb_gov_address);
            tv_desc_gov_address = (TextView) itemView.findViewById(R.id.tv_desc_gov_address);
            tv_name_gov = (TextView) itemView.findViewById(R.id.tv_name_gov);
            tv_phone_gov = (TextView) itemView.findViewById(R.id.tv_phone_gov);
            tv_desc_gov_address_detail  = (TextView) itemView.findViewById(R.id.tv_desc_gov_address_detail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getLayoutPosition(), userAddressList.get(getLayoutPosition()));
                }
            });
        }
    }

    private addressRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(addressRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position, UserAddress userAddress);
    }
}
