package com.example.myshop.type;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    String TAG = "ChatAdapter";
    private Context context;
    private List<PersonChat> lists;

    public ChatAdapter(Context context, List<PersonChat> lists) {
        this.context = context;
        this.lists = lists;
        for(int i = 0; i < lists.size(); i++) {
            Log.e(TAG, "aaaaaaaa"+lists.get(i).getChatMessage());
        }
    }

    /**
     * 是否是自己发送的消息
     *
     * @author cyf
     *
     */
    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    /**
     * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
     */
    public int getItemViewType(int position) {
        PersonChat entity = lists.get(position);

        if (entity.isMeSend()) {// 收到的消息
            return IMsgViewType.IMVT_COM_MSG;
        } else {// 自己发送的消息
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        HolderView holderView = null;
        PersonChat entity = lists.get(arg0);
        boolean isMeSend = entity.isMeSend();
//        if (holderView == null) {
            holderView = new HolderView();
            if (isMeSend) {
                arg1 = View.inflate(context, R.layout.chat_dialog_right_item,
                        null);
                holderView.tv_chat_me_message = (TextView) arg1.findViewById(R.id.tv_chat_me_message);
                holderView.imageView = (ImageView) arg1.findViewById(R.id.iv_chat_imagr_right);
                Glide.with(context).load(R.drawable.user_other).into(holderView.imageView);
                Log.e(TAG, "aaaaaa"+entity.getChatMessage());
                holderView.tv_chat_me_message.setText(entity.getChatMessage());

            } else {
                arg1 = View.inflate(context, R.layout.chat_dialog_left_item,
                        null);
                holderView.tv_chat_me_message = (TextView) arg1.findViewById(R.id.tvname);
                holderView.imageView = (ImageView) arg1.findViewById(R.id.ivicon);
                Glide.with(context).load(R.drawable.app_logo).into(holderView.imageView);
                Log.e(TAG, "aaaaaa"+entity.getChatMessage());
                holderView.tv_chat_me_message.setText(entity.getChatMessage());

            }
            arg1.setTag(holderView);
//        } else {
//            holderView = (HolderView) arg1.getTag();
//        }

        return arg1;
    }

    class HolderView {
        TextView tv_chat_me_message;
        ImageView imageView;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
