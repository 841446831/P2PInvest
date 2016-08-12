package com.chinosoft.p2pinvest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinosoft.p2pinvest.ui.LoadingPage;
import com.chinosoft.p2pinvest.utils.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**
 * Created by cai on 2016/7/26.
 */
public abstract class BaseFragment extends Fragment{

    private LoadingPage loadingPage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.i("TAG","--->进入onCreateView");
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            public int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                //initTitle();
                initData(resultState.getContent(),successView);
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };

        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.i("TAG","--->进入onActivityCreated");
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
            }
        }, 1000);

    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    protected abstract void initData(String content,View successView);

   //protected abstract void initTitle();

    public abstract int getLayoutId();

    public void show() {
        loadingPage.show();
    }

}
