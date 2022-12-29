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

import org.w3c.dom.Text;

import java.util.List;

public class RecommendGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ResultBeanData.DetailsBean.RecommendproductListBean> recommendproductList;
    public RecommendGridViewAdapter(Context context, List<ResultBeanData.DetailsBean.RecommendproductListBean> recommendproductList) {
        this.context = context;
        this.recommendproductList = recommendproductList;
    }

    @Override
    public int getCount() {
        return recommendproductList.size();
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
            convertView = View.inflate(context, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ResultBeanData.DetailsBean.RecommendproductListBean recommendproductListBean = recommendproductList.get(position);
        Glide.with(context).load(constants.BASE_IMAGE_URL+recommendproductListBean.getFileName()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(recommendproductListBean.getName());
        viewHolder.tv_price.setText("￥"+recommendproductListBean.getPrice());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;

    }
}
