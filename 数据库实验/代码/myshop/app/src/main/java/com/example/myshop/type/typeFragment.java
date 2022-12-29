package com.example.myshop.type;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.utils.RoundImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class typeFragment extends baseFragment implements CircleNotifyView.ICircleNotifyCallback {
    private TextView mTv;
    private final static String TAG = "typeFragment";
    private List<Item> mList = null;
    private MyAdapter mMyAdapter = null;
    private HeaderScrollView mHeaderScrollView = null;

    private Set<Integer> mDismissTag = null;

    @Override
    protected View initView() {
        View view = View.inflate(basecontext, R.layout.fragment_type, null);
        mHeaderScrollView = view.findViewById(R.id.scrollView);
        View header = LayoutInflater.from(basecontext).inflate(R.layout.bar_title, null);
        TextView textView = (TextView) header.findViewById(R.id.tv_title);
        TextView textView1 = (TextView) header.findViewById(R.id.tv_forward);
        textView1.setText("");
        textView.setText("消息");
        mHeaderScrollView.setHeaderView(header);
        mDismissTag = new HashSet<>();
        mList = new ArrayList<>();
//        for(int i = 0; i < 50; i++){
//            mList.add(new Item(i+""));
//        }
        mList.add(new Item("tsa"));
        mList.add(new Item("hcl"));
//        mList.clear();
        mMyAdapter = new MyAdapter();
        mHeaderScrollView.setAdapter(mMyAdapter);
        return view;

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDismissByTag(String tag) {
        mList.remove(new Item(tag));
        mMyAdapter.notifyDataSetChanged();
    }

    private class Item{
        public Item(String name){
            this.name = name;
        }

        String name;

        @Override
        public boolean equals(Object o) {
            if(o instanceof Item && ((Item)o).name != null){
                return ((Item)o).name.equals(this.name);
            }
            return false;
        }
    }

    private class ViewHolder{
        RelativeLayout rlName;
        TextView tvName;
        Button btnDelete;
        CircleNotifyView viewNotify;
        RoundImageView roundImageView;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View convertView, final ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
//            if(null == convertView){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
//                viewHolder.roundImageView = (RoundImageView) convertView.findViewById(R.id.messageAvator);
                viewHolder.rlName = (RelativeLayout) convertView.findViewById(R.id.rl_name);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                viewHolder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
                viewHolder.viewNotify = (CircleNotifyView) convertView.findViewById(R.id.notify_view);
                viewHolder.rlName.setTag(ScrollItemListView.NOR_DELETE_TAG);
                viewHolder.btnDelete.setTag(ScrollItemListView.DELETE_TAG);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "onClick", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(basecontext, chatActivity.class);
                        intent.putExtra("name", mList.get(i).name);
                        startActivity(intent);
                    }
                });
//                convertView.setTag(viewHolder);
//            }
//            else{
//                viewHolder = (ViewHolder)convertView.getTag();
//            }
//            if(i == 0) Glide.with(basecontext).load(R.drawable.user_other).error(R.mipmap.ic_launcher).centerCrop().into(viewHolder.roundImageView);
//            else Glide.with(basecontext).load(R.drawable.ic_launcher_background).error(R.mipmap.ic_launcher).centerCrop().into(viewHolder.roundImageView);
            viewHolder.tvName.setText(mList.get(i).name);
            viewHolder.viewNotify.setCircleNotifyCallback(typeFragment.this);
            viewHolder.viewNotify.setNumber(i+1);
            viewHolder.viewNotify.setTag(mList.get(i).name);
            viewHolder.viewNotify.setVisibility(mDismissTag.contains(i) ? View.GONE : View.VISIBLE);
            final View itemView = convertView;
            viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), mList.get(i).name + " delete", Toast.LENGTH_SHORT).show();
                    mList.remove(i);
                    modifyMargin(i, itemView ,viewGroup);
                    mMyAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    private void modifyMargin(int i, View view, ViewGroup viewGroup){
        View itemView = mMyAdapter.getView(i, view, viewGroup);
        if(null != itemView){
            View leftView = itemView.findViewWithTag(ScrollItemListView.NOR_DELETE_TAG);
            if(null != leftView) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) leftView.getLayoutParams();
                Log.d(TAG, "leftMargin:" + params.leftMargin);
                params.leftMargin = 0;
                leftView.setLayoutParams(params);
            }
        }
    }
}
