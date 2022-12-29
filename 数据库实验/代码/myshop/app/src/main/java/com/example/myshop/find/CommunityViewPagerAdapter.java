package com.example.myshop.find;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myshop.bean.NewPostBean;

import java.util.ArrayList;
import java.util.List;

public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentLists = new ArrayList<>();
    private String[] titles = new String[]{"热帖", "新帖"};
    public CommunityViewPagerAdapter(FragmentManager fm, NewPostBean newPostBean) {
        super(fm);
        initFragmens(newPostBean);
    }

    private void initFragmens(NewPostBean newPostBean) {
        hot_postFragment hotPostFragment = new hot_postFragment();
        new_postFragment newPostFragment = new new_postFragment(newPostBean);
        fragmentLists.add(hotPostFragment);
        fragmentLists.add(newPostFragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists == null ? 0 : fragmentLists.size();
    }
}
