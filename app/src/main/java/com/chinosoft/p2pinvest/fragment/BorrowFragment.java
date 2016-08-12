package com.chinosoft.p2pinvest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.BorrowPlatform;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2016/7/26.
 */
public class BorrowFragment extends BaseFragment{

    private ListView listView;
    AsyncHttpClient client = new AsyncHttpClient();

    public void init(View  successView)
    {
        listView = (ListView) successView.findViewById(R.id.lv_borrow);
    }

    class ViewHolder{
        ImageView ivImage;
        TextView tvname;
        TextView tvtitle;
        TextView tvPeopleNum;
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PLATFORM_ALL;
    }

    @Override
    protected void initData(String content, View successView) {
        init(successView);
        List<BorrowPlatform> borrowPlatforms = JSON.parseArray(content,BorrowPlatform.class);
        MyAdapter myAdapter = new MyAdapter(getActivity(),borrowPlatforms);
        listView.setAdapter(myAdapter);

    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_borrow;
    }

    public class MyAdapter extends BaseAdapter{

        LayoutInflater layoutInflater;
        List<BorrowPlatform> borrowPlatforms = new ArrayList<>();

        public MyAdapter(Context context,List<BorrowPlatform> borrowPlatforms) {
            this.layoutInflater = LayoutInflater.from(context);
            this.borrowPlatforms = borrowPlatforms;
        }

        @Override
        public int getCount() {
            return borrowPlatforms.size();
        }

        @Override
        public Object getItem(int i) {
            return borrowPlatforms.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;

            if(view == null)
            {
                viewHolder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.platform_item,null);
                viewHolder.ivImage = (ImageView) view.findViewById(R.id.iv_image);
                viewHolder.tvtitle = (TextView) view.findViewById(R.id.tv_title);
                viewHolder.tvname = (TextView) view.findViewById(R.id.tv_name);
                viewHolder.tvPeopleNum = (TextView) view.findViewById(R.id.tv_peoplenum);
                view.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) view.getTag();
            }
            String imageUrl = AppNetConfig.BASEURL + "image/" + borrowPlatforms.get(i).getImageUrl();
            Picasso.with(getActivity()).load(imageUrl).into(viewHolder.ivImage);
            viewHolder.tvname.setText(borrowPlatforms.get(i).getName());
            viewHolder.tvtitle.setText(borrowPlatforms.get(i).getTitle());
            viewHolder.tvPeopleNum.setText(borrowPlatforms.get(i).getPeopleNum() + "人已成功借款");
            return view;
        }
    }

}
