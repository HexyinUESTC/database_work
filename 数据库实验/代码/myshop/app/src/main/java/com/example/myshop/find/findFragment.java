package com.example.myshop.find;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.myshop.R;
import com.example.myshop.base.baseFragment;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.NewPostBean;
import com.example.myshop.bean.User;
import com.google.android.material.tabs.TabLayout;

public class findFragment extends baseFragment {
    private ImageButton ibCommunityMessage;
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private final int EDIT = 0;
    private final String TAG = "findFragment";
    private NewPostBean add_newPostBean;
    @Override
    protected View initView() {
        View view = View.inflate(basecontext, R.layout.fragment_find, null);
        tableLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        ibCommunityMessage = (ImageButton) view.findViewById(R.id.ib_community_message);
        ibCommunityMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(basecontext, editPostActivity.class);
                startActivityForResult(intent, EDIT);
            }
        });
        CommunityViewPagerAdapter communityViewPagerAdapter = new CommunityViewPagerAdapter(getFragmentManager(),add_newPostBean );
        viewPager.setAdapter(communityViewPagerAdapter);
        tableLayout.setVisibility(View.VISIBLE);
        tableLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //data为Activity_B中回传的Intent
        //change01、change02即为回传的值
//        String change01 = data.getStringExtra("change01");
//        String change02 = data.getStringExtra("change02");
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {//resultCode为回传的标记，我在B中回传的是resultCode
            case EDIT:
//                int loginUserId = data.getIntExtra("userId", myApplication.getId());

//                requestUser(loginUserId);
                NewPostBean  newPostBean = (NewPostBean) data.getSerializableExtra("post");
                Log.e(TAG, String.valueOf(newPostBean));
                add_newPostBean = newPostBean;
                CommunityViewPagerAdapter communityViewPagerAdapter = new CommunityViewPagerAdapter(getFragmentManager(),add_newPostBean );
                viewPager.setAdapter(communityViewPagerAdapter);
                Log.e(TAG, "aaaaaaaaaaaaaaaaaaa");
//                Glide.with(basecontext).load(constants.BASE_IMAGE_URL+user.getFileName()).into(ib_user_icon_avator);
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
    }
}
