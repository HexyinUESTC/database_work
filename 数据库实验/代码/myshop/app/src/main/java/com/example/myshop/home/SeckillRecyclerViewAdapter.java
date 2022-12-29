package com.example.myshop.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.bean.ResultBeanData;
import com.example.myshop.utils.constants;

import java.util.List;

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.viewHolder> {
    private final Context context;
    List<ResultBeanData.DetailsBean.SeckillproductListBean> listBeans;
    public SeckillRecyclerViewAdapter(Context context, List<ResultBeanData.DetailsBean.SeckillproductListBean> seckillproductList) {
        this.context = context;
        this.listBeans = seckillproductList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_seckill, null);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ResultBeanData.DetailsBean.SeckillproductListBean seckillproductListBean = listBeans.get(position);
        Glide.with(context).load(constants.BASE_IMAGE_URL+seckillproductListBean.getFileName()).into(holder.iv_figure);
        holder.tv_cover_price.setText("￥"+seckillproductListBean.getPrice());
        holder.tv_origin_price.setText("￥"+seckillproductListBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSeckillRecyclerView != null) {
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

    }

    public interface OnSeckillRecyclerView {
        /**
         * 当某条被点击时回调
         * @param position
         */
        public void onItemClick(int position);
    }
    private OnSeckillRecyclerView onSeckillRecyclerView;

    /**
     * 设置item的监听
     * @param onSeckillRecyclerView
     */
    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
