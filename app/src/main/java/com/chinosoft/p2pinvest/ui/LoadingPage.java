package com.chinosoft.p2pinvest.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chinosoft.p2pinvest.R;
import com.chinosoft.p2pinvest.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by cai on 2016/7/26.
 */
public abstract class LoadingPage extends FrameLayout{

    AsyncHttpClient client = new AsyncHttpClient();

    private static final int PAGE_LOADING_STATE = 1;

    private static final int PAGE_ERROR_STATE = 2;

    private static final int PAGE_EMPTY_STATE = 3;

    private static final int PAGE_SUCCESS_STATE = 4;

    private int PAGE_CURRENT_STATE = 1;

    private View loadingView;

    private View errorView;

    private View emptyView;

    private View successView;

    private LayoutParams lp;

    private ResultState resultState = null;

    private Context mConext;

    public LoadingPage(Context context) {
        super(context);
        this.mConext = context;
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mConext = context;
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mConext = context;
        init();
    }

    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingView, lp);
        }
        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            addView(errorView, lp);
        }
        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyView, lp);
        }
        showSafePage();
    }

    private void showSafePage() {
        //Log.i("TAG","--->进入showSafePage");
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? View.VISIBLE : View.GONE);
        if (successView == null) {
            successView = View.inflate(mConext, LayoutId(), null);
            addView(successView, lp);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE ? View.VISIBLE : View.GONE);
    }

    public abstract int LayoutId();

    public void show() {
        //Log.i("TAG","--->进入show");
        //状态归位
        if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }
        //处理不需要发送请求来决定界面的情况
        String url = url();
        //Log.i("TAG","--->" + url);
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();
        } else {
            client.post(url, params(), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String content, Throwable throwable) {
                    resultState = ResultState.ERROR;
                    resultState.setContent("");
                    loadPage();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String content) {
                    if (TextUtils.isEmpty(content)) {
                        resultState = ResultState.EMPTY;
                        resultState.setContent("");
                    } else {
                        resultState = ResultState.SUCCESS;
                        resultState.setContent(content);
                    }
                    loadPage();
                }
            });
        }
    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                //当前状态设置为2，显示错误界面
                PAGE_CURRENT_STATE = 2;
                break;
            case EMPTY:
                //当前状态设置为3，显示空界面
                PAGE_CURRENT_STATE = 3;
                break;
            case SUCCESS:
                //当前状态设置为4，显示成功界面
                PAGE_CURRENT_STATE = 4;
                break;
        }
        showSafePage();
        if (PAGE_CURRENT_STATE == 4) {
            OnSuccess(resultState, successView);
        }
    }

    protected abstract void OnSuccess(ResultState resultState, View successView);

    protected abstract RequestParams params();

    protected abstract String url();

    public enum ResultState {

        ERROR(PAGE_ERROR_STATE), EMPTY(PAGE_EMPTY_STATE), SUCCESS(PAGE_SUCCESS_STATE);

        private int state;

        private String content;

        ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
