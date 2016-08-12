package com.chinosoft.p2pinvest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.User;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.common.MyApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class InvestInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button btn_invest;
    private TextView investMoney;
    private TextView myMoney;
    private TextView tv_productName;
    private User user;
    private AlertDialog dialog;
    private AlertDialog dialog1;
    String productName;
    String invest;
    Integer pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_info);
        initView();
        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        productName = intent.getStringExtra("productName");
        invest = intent.getStringExtra("invest");
        pid = intent.getIntExtra("pid",0);
        tv_productName.setText(productName);
        investMoney.setText(invest + "元");

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", MyApplication.username);
        client.post(AppNetConfig.USER_INFO, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                user = new User();
                user = JSON.parseObject(data, User.class);
                //BigDecimal bTotalMoney = new BigDecimal(user.getTotalMoney().toString());
                //BigDecimal bIncome = new BigDecimal(user.getIncome().toString());
                //Double myTotalMoney = bTotalMoney.add(bIncome).doubleValue();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String s = decimalFormat.format(user.getTotalMoney());
                myMoney.setText(s + "元");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

    }


    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        btn_invest = (Button) findViewById(R.id.btn_invest);
        btn_invest.setOnClickListener(this);
        investMoney = (TextView) findViewById(R.id.invest_money);
        investMoney.setOnClickListener(this);
        myMoney = (TextView) findViewById(R.id.my_money);
        myMoney.setOnClickListener(this);
        tv_productName = (TextView) findViewById(R.id.productName);
        tv_productName.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_invest:
                showDialog();
                break;
            case R.id.back:
                finish();
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.invest_dialog, null);
        Button confirm = (Button) view.findViewById(R.id.confirm);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    private void pay(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",MyApplication.username);
        params.put("pid",pid.toString());
        params.put("invest",invest);

        client.post(AppNetConfig.INVEST, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dialog.dismiss();
                String data = new String(responseBody);
                Map<String,Integer> map = (Map<String, Integer>) JSON.parse(data);
                if(map.get("msg") == 1)
                {
                    paySuccess();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void paySuccess()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.pay_success_dialog, null);
        TextView close = (TextView) view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
                finish();
            }
        });
        dialog1 = builder.create();
        dialog1.setView(view);
        dialog1.show();
    }
}
