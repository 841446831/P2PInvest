package com.chinosoft.p2pinvest.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chinosoft.p2pinvest.R;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2016/7/26.
 */
public class FinacingFragment extends BaseFragment{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();

    public void init(View successView)
    {
        viewPager = (ViewPager) successView.findViewById(R.id.viewpager_fragmet);
        tabLayout = (TabLayout) successView.findViewById(R.id.tablayout);
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content, View successView) {
        init(successView);
        FinacingFirstFragment finacingFirstFragment = new FinacingFirstFragment();
        FinacingDebtFragment finacingDebtFragment = new FinacingDebtFragment();
        FinacingTransferFragment finacingTransferFragment = new FinacingTransferFragment();
        fragmentList.add(finacingFirstFragment);
        fragmentList.add(finacingDebtFragment);
        fragmentList.add(finacingTransferFragment);

        viewPager.setAdapter(new MyAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_finacing;
    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getActivity().getResources().getStringArray(R.array.finacing)[position];
        }
    }

}
