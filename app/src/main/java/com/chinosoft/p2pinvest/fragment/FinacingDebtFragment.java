package com.chinosoft.p2pinvest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.Borrower;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.ui.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cai on 2016/7/28.
 */
public class FinacingDebtFragment extends BaseFragment{

    private ListView listView;
    AsyncHttpClient client = new AsyncHttpClient();

    class ViewHolder{

        TextView tv_title;
        TextView tv_total;
        TextView tv_annualrate_num;
        TextView tv_timelimit_num;
        RoundProgress roundProgress;

    }

    public void init(View successView){
        listView = (ListView) successView.findViewById(R.id.listView);
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.BORROWER_ALL;
    }

    @Override
    protected void initData(String content, View successView) {
        init(successView);
        List<Borrower> borrowers = JSON.parseArray(content,Borrower.class);
        MyAdaper myAdaper = new MyAdaper(getActivity(),borrowers);
        listView.setAdapter(myAdaper);
    }

    @Override
    public int getLayoutId() {
            return R.layout.fragment_finacing_debt;
        }
    public class  MyAdaper extends BaseAdapter{

        LayoutInflater layoutInflater;
        private List<Borrower> borrowers;
        Map<Integer,Boolean> map = new HashMap<>();
        public MyAdaper(Context context,List<Borrower> borrowers) {
            this.layoutInflater = LayoutInflater.from(context);
            this.borrowers = borrowers;

            for(int i = 0; i < borrowers.size();i++)
            {
                map.put(i,true);
            }
        }

        @Override
        public int getCount() {
            return borrowers.size();
        }

        @Override
        public Object getItem(int i) {
            return borrowers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;

            if(view == null)
            {
                viewHolder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.borrower_item,null);
                viewHolder.roundProgress = (RoundProgress) view.findViewById(R.id.roundProgress);
                viewHolder.tv_annualrate_num = (TextView) view.findViewById(R.id.tv_annualrate_num);
                viewHolder.tv_timelimit_num = (TextView) view.findViewById(R.id.tv_timelimit_num);
                viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
                viewHolder.tv_total = (TextView) view.findViewById(R.id.tv_total);
                view.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) view.getTag();
            }

            int total = borrowers.get(i).getTotal();

            if(total > 10000)
            {
                float totalf =total/10000f;
                DecimalFormat decimalFormat=new DecimalFormat(".00");
                String t = decimalFormat.format(totalf);
                viewHolder.tv_total.setText("总额："+ t +"万");
            }
            else
            {
                viewHolder.tv_total.setText("总额：" + total + "元");
            }

            viewHolder.tv_annualrate_num.setText(borrowers.get(i).getAnnualRate().toString()+"%");
            viewHolder.tv_timelimit_num.setText(borrowers.get(i).getTimeLimit());
            viewHolder.tv_title.setText(borrowers.get(i).getBorrowPlatform().getName() + "借款");

            final int progress = (int)(borrowers.get(i).getInvestMoney() / Float.parseFloat(borrowers.get(i).getTotal().toString()) * 100);

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

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

}
