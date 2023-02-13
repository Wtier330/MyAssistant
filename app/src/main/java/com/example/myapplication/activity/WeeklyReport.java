package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

/**
 * @author WTL
 * @version 1.0.0
 * @date 2023/02/13
 * @describe 周报生成器的webview套壳开发
 */
public class WeeklyReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置为全屏（隐藏状态栏）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeklyreport);
        this.CreateWebView();
        //TODO 设置悬浮按钮，对页面进行设置
    }

    private void CreateWebView() {
        final WebView wv_keekre = findViewById(R.id.wv_weeklyreport);
        // 设置 WebView 允许执行 JavaScript 脚本
        wv_keekre.getSettings().setJavaScriptEnabled(true);
        // 确保跳转到另一个网页时仍然在当前 WebView 中显示
        // 而不是调用浏览器打开
        wv_keekre.setWebViewClient(new WebViewClient());
        String url = "https://weeklyreport.avemaria.fun/zh";
        wv_keekre.loadUrl(url);
        wv_keekre.setOnKeyListener(new View.OnKeyListener() {
            // 设置 WebView 的按键监听器，覆写监听器的 onKey 函数，对返回键作特殊处理
            // 当 WebView 可以返回到上一个页面时回到上一个页面
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wv_keekre.canGoBack()) {
                    wv_keekre.goBack();
                    return true;
                }
                return false;
            }
        });
    }
}