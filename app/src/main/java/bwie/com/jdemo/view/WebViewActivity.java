package bwie.com.jdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import bwie.com.jdemo.R;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/4/17 16:26
 */

public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebView webview = (WebView) findViewById(R.id.webview);
        //设置支持JavaScript脚本l
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);


    }
}
