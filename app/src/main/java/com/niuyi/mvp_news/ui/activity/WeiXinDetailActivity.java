package com.niuyi.mvp_news.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.niuyi.mvp_news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeiXinDetailActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String weixin_url = getIntent().getStringExtra("weixin_url");

        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.setWebViewClient(new MyWebViewClient());

        mWebview.loadUrl(weixin_url);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}
