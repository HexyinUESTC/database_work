package com.example.myshop.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.bean.ResultBeanData;
import com.example.myshop.utils.constants;

import java.util.List;

public class HotGridViewAdapter extends BaseAdapter {
    private  Context context;
    private  List<ResultBeanData.DetailsBean.HotproductBean.HotproductListBean> hotproductList;

    public HotGridViewAdapter(Context context, List<ResultBeanData.DetailsBean.HotproductBean.HotproductListBean> hotproductList) {
        this.context = context;
        this.hotproductList = hotproductList;
    }

    @Override
    public int getCount() {
        return hotproductList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = View.inflate(context, R.layout.item_hot_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.DetailsBean.HotproductBean.HotproductListBean hotproductListBean = hotproductList.get(position);
        Glide.with(context).load(constants.BASE_IMAGE_URL+hotproductListBean.getFileName()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotproductListBean.getName());
        viewHolder.tv_price.setText("￥"+hotproductListBean.getPrice());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
