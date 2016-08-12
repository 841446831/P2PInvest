package com.chinosoft.p2pinvest.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinosoft.p2pinvest.MainActivity;
import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.bean.User;
import com.chinosoft.p2pinvest.common.MyApplication;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView username;
    private LinearLayout llUsername;
    private TextView realname;
    private LinearLayout llRealname;
    private LinearLayout llTel;
    private LinearLayout llEmail;
    private LinearLayout llChangePassword;
    private Button btnLogout;
    private User user;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (User) bundle.getSerializable("user");
        Log.i("TAG", "user--->" + user);
        username.setText(user.getUserName());

    }

    private void initView() {
        username = (TextView) findViewById(R.id.username);
        llUsername = (LinearLayout) findViewById(R.id.ll_username);
        realname = (TextView) findViewById(R.id.realname);
        llRealname = (LinearLayout) findViewById(R.id.ll_realname);
        llTel = (LinearLayout) findViewById(R.id.ll_tel);
        llEmail = (LinearLayout) findViewById(R.id.ll_email);
        llChangePassword = (LinearLayout) findViewById(R.id.ll_change_password);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                logout();
                break;
            case R.id.back:
                finish();
        }
    }

    private void logout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("你确定要退出登录吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyApplication.deleteUser(user.getUserName());
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
