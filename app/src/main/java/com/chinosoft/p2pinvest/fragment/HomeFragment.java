package com.chinosoft.p2pinvest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.FloatCodec;
import com.chinosoft.p2pinvest.Activity.InvestActivity;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.Home;
import com.chinosoft.p2pinvest.bean.Notice;
import com.chinosoft.p2pinvest.bean.Product;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.ui.RoundProgress;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cai on 2016/7/26.
 */
public class HomeFragment extends BaseFragment{

    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    Timer timer;
    private int progress = 0;
    private RoundProgress roundProgress;
    private Button btnInvest;

    private Home home;

    public void init(View successView)
    {
        viewPager = (ViewPager) successView.findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) successView.findViewById(R.id.circle);
        roundProgress = (RoundProgress) successView.findViewById(R.id.roundProgress);
        btnInvest = (Button) successView.findViewById(R.id.btn_invest);
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.HOME;
    }

    @Override
    protected void initData(String content,View successView) {

        init(successView);

        if(!TextUtils.isEmpty(content))
        {
            home = new Home();
            JSONObject jsonObject = JSON.parseObject(content);
            String productString = jsonObject.getString("product");
            String noticeString = jsonObject.getString("notice");

            Product product = JSON.parseObject(productString,Product.class);
            List<Notice> notices = JSON.parseArray(noticeString,Notice.class);
            //Log.i("TAG","--->" + notices.size());
            home.setNotices(notices);
            home.setProduct(product);

            viewPager.setAdapter(new MyAdapter());
            indicator.setViewPager(viewPager);
            startSwitchTask();
            progress = (int) (product.getInvestMoney() / Float.parseFloat(product.getTotal().toString()) * 100);
            roundProgress.setName(product.getName());
            roundProgress.setTextTitle("产品已获投资");
            new Thread(runnable).start();
        }

        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InvestActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("product",home.getProduct());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            for(int i = 0; i <= progress;i++)
            {
                roundProgress.setProgress(i);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    private class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return home.getNotices() == null ? 0 : home.getNotices().size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = AppNetConfig.BASEURL + "image/" + home.getNotices().get(position).getImageUrl();
            //Log.i("TAG","--->" + imageUrl);
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getActivity()).load(imageUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
       }

       @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    public void startSwitchTask()
    {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                final  int index = (viewPager.getCurrentItem() + 1) % home.getNotices().size();
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(index);
                    }
                });
            }
        };
        timer.schedule(timerTask,3000,3000);
    }



}
