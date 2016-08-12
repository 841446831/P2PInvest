package com.chinosoft.p2pinvest.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.Activity.BalanceAccountActivity;
import com.chinosoft.p2pinvest.Activity.MyInvestActivity;
import com.chinosoft.p2pinvest.Activity.UserInfoActivity;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.User;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.common.MyApplication;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.loopj.android.http.RequestParams;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cai on 2016/7/26.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{

    private CircleImageView profileImage;
    private TextView username;
    private LinearLayout myInvest;
    private LinearLayout balance;
    private LinearLayout sign;
    private LinearLayout integral;
    private TextView totalMoney;
    private Button btnWithdraw;
    private Button btnRecharge;
    private TextView myIncome;
    private LinearLayout investRightWay;
    private LinearLayout debtProduct;
    private LinearLayout myBorrow;
    private LinearLayout userInfo;
    private LinearLayout more;
    private LinearLayout userInfoImage;
    private LineChart chart;

    private User user;

    private Typeface mTf;

    @Override
    protected RequestParams getParams() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", MyApplication.username);
        return requestParams;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.USER_INFO;
    }

    @Override
    protected void initData(String content, View successView) {
        initView(successView);
        if(!TextUtils.isEmpty(content))
        {
            user = new User();
            user = JSON.parseObject(content,User.class);
            Log.i("TAG","content--->"+ content);
            username.setText(user.getUserName());
            BigDecimal bTotalMoney = new BigDecimal(user.getTotalMoney().toString());
            BigDecimal bIncome = new BigDecimal(user.getIncome().toString());
            Double myMoney = bTotalMoney.add(bIncome).doubleValue();
            DecimalFormat decimalFormat=new DecimalFormat("0.00");
            String s = decimalFormat.format(myMoney);
            totalMoney.setText(s);
            String income = decimalFormat.format(user.getIncome());
            myIncome.setText(income);
            drawChartLine();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    private void initView(View view) {
        profileImage = (CircleImageView) view.findViewById(R.id.profile_image);
        username = (TextView) view.findViewById(R.id.username);
        myInvest = (LinearLayout) view.findViewById(R.id.my_invest);
        balance = (LinearLayout) view.findViewById(R.id.balance);
        sign = (LinearLayout) view.findViewById(R.id.sign);
        integral = (LinearLayout) view.findViewById(R.id.integral);
        totalMoney = (TextView) view.findViewById(R.id.total_money);
        btnWithdraw = (Button) view.findViewById(R.id.btn_withdraw);
        btnRecharge = (Button) view.findViewById(R.id.btn_recharge);
        myIncome = (TextView) view.findViewById(R.id.my_income);
        investRightWay = (LinearLayout) view.findViewById(R.id.invest_right_way);
        debtProduct = (LinearLayout) view.findViewById(R.id.debt_product);
        myBorrow = (LinearLayout) view.findViewById(R.id.my_borrow);
        userInfo = (LinearLayout) view.findViewById(R.id.user_info);
        more = (LinearLayout) view.findViewById(R.id.more);
        userInfoImage = (LinearLayout) view.findViewById(R.id.user_info_image);
        chart = (LineChart) view.findViewById(R.id.chart);
        userInfoImage.setOnClickListener(this);
        myInvest.setOnClickListener(this);
        balance.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.user_info_image:
               gotoUserInfoActivity();
                break;
            case R.id.my_invest:
                gotoMyInvestActivity();
                break;
            case R.id.balance:
                gotoBalanceActivity();
                break;
        }
    }

    private void drawChartLine()
    {
        // apply styling
        // holder.chart.setValueTypeface(mTf);
        //绘制图表右下角文字描述信息
        chart.setDescription("");
        chart.setDrawGridBackground(true);

        //绘制图表的X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        //绘制图表的Y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        //false:代表值是平均分配的;
        leftAxis.setLabelCount(7, false);

        chart.getAxisRight().setEnabled(false);
//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setDrawGridLines(false);

        // set data
        chart.setData(generateDataLine());
        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateX(750);

    }

    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();
        e1.add(new Entry(1.25f,0));
        e1.add(new Entry(30.20f,1));
        e1.add(new Entry(40.44f,2));
        e1.add(new Entry(10.00f,3));
        e1.add(new Entry(20.00f,4));

//        for (int i = 0; i < 5; i++) {
//            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
//        }

        LineDataSet d1 = new LineDataSet(e1, "最近收益");
        //指定数据集合绘制时候的属性
        d1.setLineWidth(1.5f);
        d1.setCircleSize(1.5f);
        d1.setHighLightColor(Color.BLACK);
        d1.setDrawValues(true);
        d1.disableDashedLine();
        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(getMonths(), sets);
        return cd;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("8-8");
        m.add("8-9");
        m.add("8-10");
        m.add("8-11");
        m.add("8-12");
        return m;
    }


    private void gotoUserInfoActivity()
    {
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void gotoMyInvestActivity()
    {
        Intent intent = new Intent(getActivity(),MyInvestActivity.class);
        startActivity(intent);
    }

    private void gotoBalanceActivity()
    {
        Intent intent = new Intent(getActivity(), BalanceAccountActivity.class);
        startActivity(intent);
    }
}
