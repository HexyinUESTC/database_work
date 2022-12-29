package com.example.myshop.find;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.NewPostBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.utils.constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class new_postFragmentAdapter extends RecyclerView.Adapter<new_postFragmentAdapter.viewHolder> {

    public Context context;
    public List<NewPostBean> newPostBeans;
    public new_postFragmentAdapter(Context context, List<NewPostBean> newPostBeans) {
        this.context = context;
        Collections.reverse(newPostBeans);
        this.newPostBeans = newPostBeans;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_hotpost, null);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, final int position) {
        final NewPostBean newPostBean = newPostBeans.get(position);
        if (newPostBean.getProductId() == 12) {
            Glide.with(context).load(R.drawable.user_other).into(holder.ib_new_post_avatar);
            Glide.with(context).load(R.drawable.q).into(holder.iv_hot_figure);
        }
        else {
            Glide.with(context).load(constants.BASE_IMAGE_URL + newPostBean.getAvator()).into(holder.ib_new_post_avatar);
            Glide.with(context).load(constants.BASE_IMAGE_URL + newPostBean.getProductImg()).into(holder.iv_hot_figure);
        }
        holder.tv_hot_username.setText(newPostBean.getUserName());
        holder.tv_hot_saying.setText(newPostBean.getDescription());
        holder.tv_hot_likes.setText(String.valueOf(newPostBean.getLikeNum()));
        holder.tv_hot_comments.setText(String.valueOf(newPostBean.getCommentNum()));
        final Drawable unLike = context.getResources().getDrawable(R.drawable.good_uncollected);
        final Drawable like = context.getResources().getDrawable(R.drawable.home_arrow_left_hot);
        if(mItemClickListener != null) {
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
                intent.putExtra("talk", newPostBean);
                context.startActivity(intent);
            }
        });

        holder.tv_hot_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newPostBean.getIsLike() == 0) {
                    newPostBean.setIsLike(1);
                    newPostBean.setLikeNum(newPostBean.getLikeNum()+1);
                    holder.tv_hot_likes.setCompoundDrawablesWithIntrinsicBounds(like, null, null, null);
                    holder.tv_hot_likes.setCompoundDrawablePadding(0);
                    holder.tv_hot_likes.setText("￥"+newPostBean.getLikeNum());
                    set_Is_Or_Not_Like(newPostBean);
                }
                else {
                    newPostBean.setIsLike(0);
                    newPostBean.setLikeNum(newPostBean.getLikeNum()-1);
                    holder.tv_hot_likes.setCompoundDrawablesWithIntrinsicBounds(unLike, null, null, null);
                    holder.tv_hot_likes.setCompoundDrawablePadding(0);
                    holder.tv_hot_likes.setText("￥"+newPostBean.getLikeNum());
                    set_Is_Or_Not_Like(newPostBean);
                }
            }
        });
    }


    private void set_Is_Or_Not_Like(NewPostBean newPostBean) {
        Gson gson = new Gson();
        String json = gson.toJson(newPostBean);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.LIKE_NEW)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("点赞他妈的", e.getMessage());
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "点赞提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                Log.i("点赞", string);
                RESULT result = JSON.parseObject(string, RESULT.class);
                if (result.getSuccess() == true) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "点赞失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return newPostBeans.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        private TextView tv_hot_username;
        private TextView tv_hot_addtime;
        private ImageView iv_hot_figure;
        private TextView tv_hot_saying;
        private TextView tv_hot_likes;
        private TextView tv_hot_comments;
        private ImageView ib_new_post_avatar;
        public viewHolder(@NonNull View itemView) {
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

    // 利用接口 -> 给RecyclerView设置点击事件
    private hot_postFragmentAdapter.ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int position) ;
    }
    public void setOnItemClickListener(hot_postFragmentAdapter.ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }

}

