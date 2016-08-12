package com.chinosoft.p2pinvest.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.ProductInvest;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.loopj.android.http.RequestParams;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

;


public class ProductBalanceFragment extends BaseFragment {

    ListView listView;
    List<ProductInvest> productInvests = new ArrayList<>();

    class ViewHolder{
        TextView name;
        TextView investMoney;
        TextView income;
    }

    private void init(View view)
    {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    protected RequestParams getParams() {
        RequestParams params = new RequestParams();
        params.put("status","1");
        return params;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INVEST_PRODUCT;
    }

    @Override
    protected void initData(String content, View successView) {
        init(successView);

        productInvests = JSON.parseArray(content,ProductInvest.class);
        listView.setAdapter(new MyAdapter(getActivity()));

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_invest;
    }

    class MyAdapter extends BaseAdapter{

        LayoutInflater inflater = null;
        Context context;

        public MyAdapter(Context context) {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return productInvests.size();
        }

        @Override
        public Object getItem(int i) {
            return productInvests.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder ;
            if(view == null)
            {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.product_balance_item,null);
                viewHolder.name = (TextView) view.findViewById(R.id.invest_product);
                viewHolder.income = (TextView) view.findViewById(R.id.income);
                viewHolder.investMoney = (TextView) view.findViewById(R.id.invest_money);
                view.setTag(viewHolder);
            }
            else
            {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText(productInvests.get(i).getProduct().getName());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String s = decimalFormat.format((productInvests.get(i).getInvestMoney()));
            viewHolder.investMoney.setText(s+"元");

            int day;
            String limitTime = productInvests.get(i).getProduct().getTimeLimit().trim();
            String c = limitTime.substring(limitTime.length() - 1, limitTime.length());
            if (c.equals("天")) {
                day = Integer.parseInt(limitTime.substring(0, limitTime.length() - 1));
            } else {
                day = Integer.parseInt(limitTime.substring(0, limitTime.length() - 2)) * 30;
            }
            Double d = productInvests.get(i).getInvestMoney() / 100.0 * day / 365;
            String sAnnualRate = productInvests.get(i).getProduct().getAnnualRate().toString();
            BigDecimal dAnnualRate = new BigDecimal(sAnnualRate);
            BigDecimal decimal = new BigDecimal(d.toString());
            double profit = decimal.multiply(dAnnualRate).doubleValue();
            String p = decimalFormat.format(profit);
            viewHolder.income.setText(p+"元");

            return view;
        }
    }

}
