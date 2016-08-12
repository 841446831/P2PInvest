package com.chinosoft.p2pinvest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinosoft.p2pinvest.Activity.InvestActivity;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.Product;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.ui.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by cai on 2016/7/28.
 */
public class FinacingFirstFragment extends BaseFragment{

    private StickyListHeadersListView listView;
    AsyncHttpClient client = new AsyncHttpClient();


    public class ViewHolder{
        TextView name;
        TextView title;
        TextView rest;
        TextView timeLimitNum;
        TextView annualRateNum;
        RoundProgress roundProgress;
    }

    public class HeaderViewHolder{
        TextView header;
    }

    public void init(View successView)
    {
        listView = (StickyListHeadersListView) successView.findViewById(R.id.stickyListHeadersListView);
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PRODUCT_ALL;
    }

    @Override
    protected void initData(String content, View successView) {
        init(successView);
        List<Product> products = JSON.parseArray(content,Product.class);
        Log.i("TAG","products--->" + products.get(0).getType().getType());
        MyAdapter adapter = new MyAdapter(getActivity(),products);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_finacing_first;
    }

    public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private List<Product> products = new ArrayList<>();
        LayoutInflater inflater = null;
        Map<Integer,Boolean> map = new HashMap<>();

        public MyAdapter(Context context,List<Product> products)
        {
            this.inflater = LayoutInflater.from(context);
            this.products = products;
            for(int i = 0; i < products.size();i++)
            {
                map.put(i,true);
            }
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if(convertView == null)
            {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.header,null);
                holder.header = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            }
            else
            {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            String text = products.get(position).getType().getType();
            holder.header.setText(text);

            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return products.get(position).getType().getType().subSequence(0,1).charAt(0);
        }

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int i) {
            return products.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            final ViewHolder viewHolder ;
            if(view == null)
            {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.product_item,null);
                viewHolder.name = (TextView) view.findViewById(R.id.tv_name);
                viewHolder.annualRateNum = (TextView) view.findViewById(R.id.tv_annualrate_num);
                viewHolder.rest = (TextView) view.findViewById(R.id.tv_rest);
                viewHolder.roundProgress = (RoundProgress) view.findViewById(R.id.roundProgress);
                viewHolder.timeLimitNum = (TextView) view.findViewById(R.id.tv_timelimit_num);
                viewHolder.title = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.name.setText(products.get(i).getName());
            viewHolder.title.setText(products.get(i).getTitle());
            viewHolder.timeLimitNum.setText(products.get(i).getTimeLimit());
            viewHolder.annualRateNum.setText(products.get(i).getAnnualRate().toString()+"%");
            int rest = products.get(i).getTotal() - products.get(i).getInvestMoney();

            if(rest >= 10000)
            {
                float restf =rest/10000f;
                DecimalFormat decimalFormat=new DecimalFormat(".00");
                String r = decimalFormat.format(restf);
                viewHolder.rest.setText("剩余金额"+ r +"万");
            }
            else
            {
                viewHolder.rest.setText("剩余金额"+rest+"元");
            }

            //viewHolder.roundProgress.setProgress(50);

            final int progress = (int)(products.get(i).getInvestMoney() / Float.parseFloat(products.get(i).getTotal().toString()) * 100);

            if(map.get(i))
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i <= progress;i++)
                        {
                            viewHolder.roundProgress.setProgress(i);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                map.put(i,false);
            }
            else
            {
                viewHolder.roundProgress.setProgress(progress);
            }

            return view;
        }
    }
    public class ItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Product product = (Product) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(getActivity(), InvestActivity.class);
            Bundle b= new Bundle();
            b.putSerializable("product",product);
            //intent.putExtra("product",product);
            intent.putExtras(b);
            startActivity(intent);
        }
    }


}
