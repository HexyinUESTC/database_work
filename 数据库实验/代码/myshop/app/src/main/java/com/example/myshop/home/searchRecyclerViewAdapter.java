package com.example.myshop.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.find.hot_postFragmentAdapter;
import com.example.myshop.find.talkDetailActivity;
import com.example.myshop.user.addressRecyclerViewAdapter;
import com.example.myshop.utils.constants;

import java.util.List;

public class searchRecyclerViewAdapter extends RecyclerView.Adapter<searchRecyclerViewAdapter.ViewHolder> {
    private final String TAG = "searchRecyclerViewAdapter";
    private Context context;
    private List<HotPostBean> myPostBaens;

    public searchRecyclerViewAdapter(Context context, List<HotPostBean> myPostBeans) {
        this.context = context;
        this.myPostBaens = myPostBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_hotpost, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final HotPostBean hotPostBean = myPostBaens.get(position);
        Glide.with(context).load(constants.BASE_IMAGE_URL + hotPostBean.getAvator()).into(holder.ib_new_post_avatar);
        Glide.with(context).load(constants.BASE_IMAGE_URL + hotPostBean.getProductImg()).into(holder.iv_hot_figure);
        holder.tv_hot_username.setText(hotPostBean.getUserName());
        holder.tv_hot_saying.setText(hotPostBean.getDescription());
        holder.tv_hot_likes.setText(String.valueOf(hotPostBean.getLikeNum()));
        final Drawable unLike = context.getResources().getDrawable(R.drawable.good_uncollected);
        final Drawable like = context.getResources().getDrawable(R.drawable.home_arrow_left_hot);
        holder.tv_hot_comments.setText(String.valueOf(hotPostBean.getCommentNum()));
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }
        holder.tv_hot_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, talkDetailActivity.class);
//                intent.putExtra("talk", hotPostBean);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return myPostBaens.size();
    }


    private hot_postFragmentAdapter.ItemClickListener mItemClickListener;

    public interface ItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(hot_postFragmentAdapter.ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_hot_username;
        private TextView tv_hot_addtime;
        private ImageView iv_hot_figure;
        private TextView tv_hot_saying;
        private TextView tv_hot_likes;
        private TextView tv_hot_comments;
        private ImageView ib_new_post_avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_hot_username = (TextView) itemView.findViewById(R.id.tv_hot_username);
            tv_hot_addtime = (TextView) itemView.findViewById(R.id.tv_hot_addtime);
            iv_hot_figure = (ImageView) itemView.findViewById(R.id.iv_hot_figure);
            tv_hot_saying = (TextView) itemView.findViewById(R.id.tv_hot_saying);
            tv_hot_likes = (TextView) itemView.findViewById(R.id.tv_hot_likes);
            tv_hot_comments = (TextView) itemView.findViewById(R.id.tv_hot_comments);
            ib_new_post_avatar = (ImageView) itemView.findViewById(R.id.ib_new_post_avatar);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(onSeckillRecyclerView != null) {
//                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
//                    }
//                }
//            });
        }
    }
}
