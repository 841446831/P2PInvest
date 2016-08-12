package com.chinosoft.p2pinvest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinosoft.p2pinvest.Activity.LoginActivity;
import com.chinosoft.p2pinvest.common.MyApplication;
import com.chinosoft.p2pinvest.fragment.BorrowFragment;
import com.chinosoft.p2pinvest.fragment.FinacingFragment;
import com.chinosoft.p2pinvest.fragment.HomeFragment;
import com.chinosoft.p2pinvest.fragment.MyFragment;

public class MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private FinacingFragment finacingFragment;
    private BorrowFragment borrowFragment;
    private MyFragment myFragment;
    private Fragment currentFrament = null;
    private RadioGroup radioGroup;
    private ActionBar actionBar;
    private RadioButton radioButton;
    private int lastCheck = R.id.home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.hide();
        radioGroup = (RadioGroup) this.findViewById(R.id.main_bottom_tabs);
        radioButton = (RadioButton) this.findViewById(R.id.my);

        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        finacingFragment = new FinacingFragment();
        borrowFragment = new BorrowFragment();
        myFragment = new MyFragment();
        switchFragment(homeFragment);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i)
            {
                case R.id.home:
                    switchFragment(homeFragment);
                    lastCheck = i;
                    break;
                case R.id.finacing:
                    switchFragment(finacingFragment);
                    lastCheck = i;
                    break;
                case R.id.borrow:
                    switchFragment(borrowFragment);
                    lastCheck = i;
                    break;
                case R.id.my:
                    if(MyApplication.username == null)
                    {
                        radioGroup.check(lastCheck);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        //startActivity(intent);
                        startActivityForResult(intent,1);
                    }
                    else
                    {
                        switchFragment(myFragment);
                    }
                    break;
                default:
                    break;
            }
        }
    });

//        radioButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.i("TAG",MyApplication.username+"");
//
//                return true;
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(requestCode == RESULT_OK)
                {
                    radioGroup.check(R.id.my);
                }
        }

        super.onActivityResult(requestCode, resultCode, data);


    }

    public void switchFragment(Fragment fragment){

        if(currentFrament != fragment)
        {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(currentFrament == null)
            {
                fragmentTransaction.add(R.id.fragmentContainer,fragment);
            }
            else if(fragment.isAdded())
            {
                fragmentTransaction.hide(currentFrament).show(fragment);
            }
            else
            {
                fragmentTransaction.hide(currentFrament).add(R.id.fragmentContainer,fragment);
            }
            fragmentTransaction.commitAllowingStateLoss();
            currentFrament = fragment;
        }
    }
}
