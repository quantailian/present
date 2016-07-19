package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/19.
 */
public class JumpBabyDetailsActivity extends AbsBaseActivity {

    private WebView webView;
    private String urls;

    @Override
    protected int setLayout() {
        return R.layout.hot_details;
    }

    @Override
    protected void initViews() {
        webView = byView(R.id.my_boby_details);

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.urls = bundle.getString("url");

        showWeb();
    }


    // 加载网页
    public void showWeb(){

        // 启用支持javascript脚本文件
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置支持当前屏幕
        webSettings.setSupportZoom(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(20);

        webSettings.setUseWideViewPort(true);
        // 让手机不能
        webSettings.setLoadWithOverviewMode(true);
        // 缓存 加载缓存或者从网络加载
        webSettings.setCacheMode(webSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl("www.baidu.com");
        // 默认启动手机浏览器关闭,在webview里启动页面
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //设置视图web
        webView.loadUrl(urls);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 当按下返回键和webview的返回时
        if (keyCode == event.KEYCODE_BACK && webView.canGoBack()){
            // goback表示返回webview的上一界面
            webView.goBack();
            return true;

        }
        finish();
        return false;
    }

}
