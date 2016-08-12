package com.chinosoft.p2pinvest.fragment;

import android.view.View;

import com.chinosoft.p2pinvest.R;
import com.loopj.android.http.RequestParams;

;


public class BorrowerBalanceFragment extends BaseFragment {

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content, View successView) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_borrower_invest;
    }
}
