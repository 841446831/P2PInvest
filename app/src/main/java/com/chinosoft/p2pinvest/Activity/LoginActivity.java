package com.chinosoft.p2pinvest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.LoginResult;
import com.chinosoft.p2pinvest.common.AppNetConfig;
import com.chinosoft.p2pinvest.common.MyApplication;
import com.chinosoft.p2pinvest.ui.TopBar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TopBar loginTopbar;
    private EditText etUser;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        loginTopbar = (TopBar) findViewById(R.id.loginTopbar);
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String username = etUser.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                requestLogin(username,password);
                break;
        }
    }

    public void requestLogin(final String username,String password)
    {
        RequestParams params = new RequestParams();
        params.put("username",username);
        params.put("password",password);
        client.post(AppNetConfig.LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                Map<String,Integer> map = (Map<String, Integer>) JSON.parse(data);
                if(map.get("msg") == LoginResult.SUCCESS)
                {
                    MyApplication.saveUser(username);
                    setResult(RESULT_OK);
                    finish();
                }
                else if(map.get("msg") == LoginResult.WRONG_PASSWORD)
                {
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
                }
                //Log.i("TAG","---->"+map.get("msg"));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(LoginActivity.this,"服务器异常",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submit() {
        // validate
        String etUserString = etUser.getText().toString().trim();
        if (TextUtils.isEmpty(etUserString)) {
            Toast.makeText(this, "输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String etPasswordString = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(etPasswordString)) {
            Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
    }
}
