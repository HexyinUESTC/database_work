package com.example.myshop.base;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.bean.CommentDetailBean;
import com.example.myshop.bean.reply;
import com.example.myshop.utils.CircleImageViewcomment;
import com.example.myshop.utils.RoundImageView;
import com.example.myshop.utils.constants;
import com.example.myshop.utils.photosUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Moos
 * E-mail: moosphon@gmail.com
 * Date:  18/4/20.
 * Desc: 评论与回复列表的适配器
 */

public class CommentExpandAdapter extends BaseExpandableListAdapter {
    private com.example.myshop.utils.photosUtils photosUtils = new photosUtils();
    private static final String TAG = "CommentExpandAdapter";
    private List<CommentDetailBean> commentBeanList;
    private List<reply> replyBeanList;
    private Context context;
    private int pageIndex = 1;

    public CommentExpandAdapter(Context context, List<CommentDetailBean> commentBeanList) {
        this.context = context;
        this.commentBeanList = commentBeanList;
    }

    @Override
    public int getGroupCount() {
        return commentBeanList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if(commentBeanList.get(i).getReplyList() == null){
            return 0;
        }else {
            return commentBeanList.get(i).getReplyList().size()>0 ? commentBeanList.get(i).getReplyList().size():0;
        }

    }

    @Override
    public Object getGroup(int i) {
        return commentBeanList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return commentBeanList.get(i).getReplyList().get(i1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
    boolean isLike = false;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpand, View convertView, ViewGroup viewGroup) {
        final GroupHolder groupHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item_layout, viewGroup, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
//        if (groupPosition == 0)
//            Glide.with(context).load(R.drawable.user_other)
//    //                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .error(R.mipmap.ic_launcher)
//                    .centerCrop()
//                    .into(groupHolder.logo);
//        else
//            Glide.with(context).load(R.drawable.ic_launcher_background)
//                    //                .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .error(R.mipmap.ic_launcher)
//                    .centerCrop()
//                    .into(groupHolder.logo);
        if (commentBeanList.get(groupPosition).getCom().getUserName() == myApplication.getAccount())
            groupHolder.logo.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
        else
            Glide.with(context).load(constants.BASE_IMAGE_URL+commentBeanList.get(groupPosition).getCom().getAvator()).into(groupHolder.logo);
//        groupHolder.tv_name.setText(commentBeanList.get(groupPosition).getNickName());
//        groupHolder.tv_time.setText(commentBeanList.get(groupPosition).getCreateDate());
//        groupHolder.tv_content.setText(commentBeanList.get(groupPosition).getContent());
        groupHolder.tv_name.setText(commentBeanList.get(groupPosition).getCom().getUserName());
        groupHolder.tv_time.setText(String.valueOf(commentBeanList.get(groupPosition).getCom().getCreateTime()));
        groupHolder.tv_content.setText(commentBeanList.get(groupPosition).getCom().getContent());
        groupHolder.iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLike){
                    isLike = false;
                    groupHolder.iv_like.setColorFilter(Color.parseColor("#aaaaaa"));
                }else {
                    isLike = true;
                    groupHolder.iv_like.setColorFilter(Color.parseColor("#FF5C5C"));
                }
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        final ChildHolder childHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_reply_item_layout,viewGroup, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        String replyUser = commentBeanList.get(groupPosition).getReplyList().get(childPosition).getUserName();
        SpannableString spannableString1;
        if(!TextUtils.isEmpty(replyUser)) {
            spannableString1 = new SpannableString(replyUser);
        }
        else {
            spannableString1 = new SpannableString(replyUser);
        }
        ForegroundColorSpan span1 = new ForegroundColorSpan(Color.BLUE);
        int size1 = replyUser.length();
        spannableString1.setSpan(span1, 0, size1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spannableStringreply = new SpannableString("回复");
        ForegroundColorSpan spanreply = new ForegroundColorSpan(Color.BLACK);
        spannableStringreply.setSpan(spanreply, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        String reply2 = commentBeanList.get(groupPosition).getReplyList().get(childPosition).getUserName();
        String reply2 = commentBeanList.get(groupPosition).getCom().getUserName();
        SpannableString spannableString2 = new SpannableString(reply2);
        ForegroundColorSpan span2 = new ForegroundColorSpan(Color.BLUE);
        int size2 = reply2.length();
        spannableString2.setSpan(span2, 0, size2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        SpannableString spannableStringchar = new SpannableString(":");
        ForegroundColorSpan spanchar = new ForegroundColorSpan(Color.BLACK);
        spannableStringreply.setSpan(spanchar, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableStringBuilder.append(spannableString1);
        spannableStringBuilder.append(spannableStringreply);
        spannableStringBuilder.append(spannableString2);
        spannableStringBuilder.append(spannableStringchar);

        childHolder.tv_name.setText(spannableStringBuilder);
//        if(!TextUtils.isEmpty(replyUser)){
//            childHolder.tv_name.setText(replyUser + ":");
//        }else {
//            childHolder.tv_name.setText("无名"+":");
//        }

        childHolder.tv_content.setText(commentBeanList.get(groupPosition).getReplyList().get(childPosition).getContent());
        if (commentBeanList.get(groupPosition).getReplyList().get(childPosition).getUserName() == myApplication.getAccount())
            childHolder.roundImageView.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
        else
            Glide.with(context).load(constants.BASE_IMAGE_URL+commentBeanList.get(groupPosition).getReplyList().get(childPosition).getAvator()).into(childHolder.roundImageView);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class GroupHolder{
        private CircleImageViewcomment logo;
        private TextView tv_name, tv_content, tv_time;
        private ImageView iv_like;
        public GroupHolder(View view) {
            logo = (CircleImageViewcomment) view.findViewById(R.id.comment_item_logo);
            tv_content = (TextView) view.findViewById(R.id.comment_item_content);
            tv_name = (TextView) view.findViewById(R.id.comment_item_userName);
            tv_time = (TextView) view.findViewById(R.id.comment_item_time);
            iv_like = (ImageView) view.findViewById(R.id.comment_item_like);
        }
    }

    private class ChildHolder{
        private TextView tv_name, tv_content;
        private CircleImageViewcomment roundImageView;
        public ChildHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.reply_item_user);
            tv_content = (TextView) view.findViewById(R.id.reply_item_content);
            roundImageView = (CircleImageViewcomment)view.findViewById(R.id.reply_avator);
        }
    }


    /**
     * by moos on 2018/04/20
     * func:评论成功后插入一条数据
     * @param commentDetailBean 新的评论数据
     */
    public void addTheCommentData(CommentDetailBean commentDetailBean){
        if(commentDetailBean!=null){

            commentBeanList.add(commentDetailBean);
            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("评论数据为空!");
        }

    }

    /**
     * by moos on 2018/04/20
     * func:回复成功后插入一条数据
     * @param replyDetailBean 新的回复数据
     */
    public void addTheReplyData(reply replyDetailBean, int groupPosition){
        if(replyDetailBean!=null){
            Log.e(TAG, "addTheReplyData: >>>>该刷新回复列表了:"+replyDetailBean.toString() );
            if(commentBeanList.get(groupPosition).getReplyList() != null ){
                commentBeanList.get(groupPosition).getReplyList().add(replyDetailBean);
//                commentBeanList.get(groupPosition).getReplyList().add(groupPosition+1, replyDetailBean);
            }else {
                List<reply> replyList = new ArrayList<>();
                replyList.add(replyDetailBean);
                commentBeanList.get(groupPosition).setReplyList(replyList);
            }

            notifyDataSetChanged();
        }else {
            throw new IllegalArgumentException("回复数据为空!");
        }

    }

    /**
     * by moos on 2018/04/20
     * func:添加和展示所有回复
     * @param replyBeanList 所有回复数据
     * @param groupPosition 当前的评论
     */
    private void addReplyList(List<reply> replyBeanList, int groupPosition){
        if(commentBeanList.get(groupPosition).getReplyList() != null ){
            commentBeanList.get(groupPosition).getReplyList().clear();
            commentBeanList.get(groupPosition).getReplyList().addAll(replyBeanList);
        }else {

            commentBeanList.get(groupPosition).setReplyList(replyBeanList);
        }

        notifyDataSetChanged();
    }

}
