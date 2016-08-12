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
import com.chinosoft.p2pinvest.fragment.BorrowerBalanceFragment;
import com.chinosoft.p2pinvest.fragment.ProductBalanceFragment;

import java.util.ArrayList;
import java.util.List;

public class BalanceAccountActivity extends AppCompatActivity{

    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView back;
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initView();
        initFragment();
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(viewpager);
    }

    private void initFragment() {

        ProductBalanceFragment productBalanceFragment = new ProductBalanceFragment();
        BorrowerBalanceFragment borrowerInvestFragment = new BorrowerBalanceFragment();
        fragmentList.add(productBalanceFragment);
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
            return BalanceAccountActivity.this.getResources().getStringArray(R.array.balance)[position];
        }
    }

}
