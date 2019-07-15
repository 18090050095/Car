package com.coderjj.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 注意添加网络权限
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);
        //支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //当需要从一个网页跳转到另一个网页的时候，我们希望目标网页
        //仍然在当前WebView中显示，而不是打开系统浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }
}
