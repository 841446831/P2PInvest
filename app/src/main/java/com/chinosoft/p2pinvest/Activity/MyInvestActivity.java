package com.chinosoft.p2pinvest.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.fragment.BorrowerInvestFragment;
import com.chinosoft.p2pinvest.fragment.ProductInvestFragment;

import java.util.ArrayList;
import java.util.List;

public class MyInvestActivity extends AppCompatActivity{

    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView back;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_invest);
        initView();
        initFragment();
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);
    }

    private void initFragment() {

        ProductInvestFragment productInvestFragment = new ProductInvestFragment();
        BorrowerInvestFragment borrowerInvestFragment = new BorrowerInvestFragment();
        fragmentList.add(productInvestFragment);
        fragmentList.add(borrowerInvestFragment);
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager_fragmet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Override
        public int getCount() {
            return  fragmentList == null ? 0 : fragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return MyInvestActivity.this.getResources().getStringArray(R.array.my_invest)[position];
        }
    }

}
