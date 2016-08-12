package com.chinosoft.p2pinvest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.Product;
import com.chinosoft.p2pinvest.common.MyApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class InvestActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView name;
    private TextView annualrateNum;
    private TextView timelimitNum;
    private TextView startTime;
    private TextView receivedWay;
    private TextView peopleNum;
    private LinearLayout introduce;
    private TextView rest;
    private TextView income;
    private Button btnInvest;
    private Button btnMinus;
    private EditText etMoney;
    private Button btnAdd;
    private LinearLayout invest;
    private ImageView back;
    Product product;
    private ProgressBar progressBar;
    private TextView percentInvest;
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        initView();
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 2:
                if(resultCode==RESULT_OK)
                {
                    Intent intent = new Intent(InvestActivity.this,InvestInfoActivity.class);
                    intent.putExtra("productName",product.getName());
                    String invest = etMoney.getText().toString().trim();
                    intent.putExtra("invest",invest);
                    intent.putExtra("pid",product.getId());
                    startActivity(intent);
                }
        }
    }

    public void initData() {
        product = new Product();
        Bundle bundle = getIntent().getExtras();
        product = (Product) bundle.getSerializable("product");
        Log.i("TAG", product.toString());
        name.setText(product.getName());
        annualrateNum.setText(product.getAnnualRate() + "%");
        timelimitNum.setText(product.getTimeLimit());
        startTime.setText(product.getStartTime());
        peopleNum.setText(product.getPeopleNum() + "人");
        receivedWay.setText(product.getReceivedWay());
        int restMoney = product.getTotal() - product.getInvestMoney();
        rest.setText(restMoney + "");
        income.setText(calculate()+"元");
        progress = (int) (product.getInvestMoney() / Float.parseFloat(product.getTotal().toString()) * 100);
        percentInvest.setText("已售" +progress+ "%");
        MyThread thread = new MyThread();
        thread.start();
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            for(int i = 0;i <= progress;i++)
            {
                progressBar.setProgress(i);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }

    public void initView() {
        name = (TextView) findViewById(R.id.tv_name);
        annualrateNum = (TextView) findViewById(R.id.tv_annualrate_num);
        timelimitNum = (TextView) findViewById(R.id.tv_timelimit_num);
        startTime = (TextView) findViewById(R.id.startTime);
        receivedWay = (TextView) findViewById(R.id.tv_receivedway);
        peopleNum = (TextView) findViewById(R.id.tv_peoplenum);
        introduce = (LinearLayout) findViewById(R.id.ll_introduce);
        rest = (TextView) findViewById(R.id.tv_rest);
        income = (TextView) findViewById(R.id.tv_income);
        invest = (LinearLayout) findViewById(R.id.ll_invest);
        btnInvest = (Button) findViewById(R.id.btn_invest);
        btnInvest.setOnClickListener(this);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnMinus.setOnClickListener(this);
        etMoney = (EditText) findViewById(R.id.et_money);
        etMoney.setOnClickListener(this);
        btnAdd = (Button) findViewById(R.id.btn_add);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        etMoney.setOnClickListener(this);
        editMoney();
        invest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                invest.setFocusable(true);
                invest.setFocusableInTouchMode(true);
                invest.requestFocus();
                return false;
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setOnClickListener(this);
        percentInvest = (TextView) findViewById(R.id.percent_invest);
        percentInvest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int money = Integer.parseInt(etMoney.getText().toString().trim());
        switch (v.getId()) {
            case R.id.btn_invest:
                if(MyApplication.username != null)
                {
                    Intent intent = new Intent(InvestActivity.this,InvestInfoActivity.class);
                    intent.putExtra("productName",product.getName());
                    String invest = etMoney.getText().toString().trim();
                    intent.putExtra("invest",invest);
                    intent.putExtra("pid",product.getId());
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(InvestActivity.this,LoginActivity.class);
                    startActivityForResult(intent,2);
                }
                break;
            case R.id.btn_minus:
                if (money - 100 >= 100) {
                    etMoney.setText(money - 100 + "");
                } else {
                    Toast.makeText(InvestActivity.this, "金额不能少于100", Toast.LENGTH_SHORT).show();
                }
                income.setText(calculate()+"元");
                break;
            case R.id.btn_add:
                if (money >= (product.getTotal() - product.getInvestMoney())) {
                    etMoney.setText((product.getTotal() - product.getInvestMoney()) + "");
                    Toast.makeText(InvestActivity.this, "超出最大金额", Toast.LENGTH_SHORT).show();
                } else {
                    etMoney.setText(money + 100 + "");
                }
                income.setText(calculate()+"元");
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private String calculate() {
        int day;
        String money = etMoney.getText().toString().trim();
        String limitTime = product.getTimeLimit().trim();
        String c = limitTime.substring(limitTime.length() - 1, limitTime.length());
        if (c.equals("天")) {
            day = Integer.parseInt(limitTime.substring(0, limitTime.length() - 1));
        } else {
            day = Integer.parseInt(limitTime.substring(0, limitTime.length() - 2)) * 30;
        }

        Double d = Integer.parseInt(money) / 100.0 * day / 365;
        String sAnnualRate = product.getAnnualRate().toString();
        BigDecimal dAnnualRate = new BigDecimal(sAnnualRate);
        BigDecimal decimal = new BigDecimal(d.toString());
        double profit = decimal.multiply(dAnnualRate).doubleValue();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String p = decimalFormat.format(profit);
        return p;
    }

    private void editMoney() {
        // validate

        etMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String money = etMoney.getText().toString().trim();
                    if (money.equals("") || Integer.parseInt(money) < 100) {
                        etMoney.setText(100 + "");
                        Toast.makeText(InvestActivity.this, "金额不能少于100", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(money) % 100 != 0) {
                        etMoney.setText(100 + "");
                        Toast.makeText(InvestActivity.this, "金额必须是100的整数倍", Toast.LENGTH_LONG).show();
                    } else if (Integer.parseInt(money) > (product.getTotal() - product.getInvestMoney())) {
                        etMoney.setText((product.getTotal() - product.getInvestMoney()) + "");
                        Toast.makeText(InvestActivity.this, "超出最大金额", Toast.LENGTH_SHORT).show();
                    }
                    income.setText(calculate()+"元");
                }

            }
        });
    }


}
