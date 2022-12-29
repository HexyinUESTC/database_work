package com.example.myshop.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.myshop.R;
import com.example.myshop.cart.cartFragment;
import com.example.myshop.find.findFragment;
import com.example.myshop.home.homeFragment;
import com.example.myshop.type.typeFragment;
import com.example.myshop.user.userFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private ArrayList<baseFragment> fragments;
    private baseFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new homeFragment());
        fragments.add(new typeFragment());
        fragments.add(new findFragment());
        fragments.add(new cartFragment());
        fragments.add(new userFragment());
    }
    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
    }
    private void initEvent() {
        //radioGroup选中事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                int curPosition = 0; //viewGroup当前选中索引
                switch (checkId) {
                    case R.id.rb_home:
                        curPosition = 0;
                        break;
                    case R.id.rb_type:
                        curPosition = 1;
                        break;
                    case R.id.rb_find:
                        curPosition = 2;
                        break;
                    case R.id.rb_cart:
                        curPosition = 3;
                        break;
                    case R.id.rb_user:
                        curPosition = 4;
                        break;
                }
                //switchFragment(mFragments.get(curPosition));
                switchFragment(fragment, fragments.get(curPosition));
            }
        });
        radioGroup.check(R.id.rb_home);//默认选中首页
    }

    //切换fragment 每次都会初始化frgament数据
    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_home, fragment).commit();
    }

    //切换fragment fragment只初始化一次
    private void switchFragment(Fragment fromFragment, baseFragment nextFragment) {
        //判断当前fragment和目标fragment是否是同一个
        if (fragment != nextFragment) {
            //不是同一个 当前要显示的fragment就是nextfragment
            fragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加过
                if (!nextFragment.isAdded()) {
                    //nextFragment没被添加过

                    //隐藏当前Fragment 添加nextFragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_home, nextFragment).commit();
                } else {
                    //nextFragment被添加过

                    //隐藏当前Fragment  显示nextFragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
