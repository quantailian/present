package comnd.example.dllo.mygiftdemo.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;


import comnd.example.dllo.mygiftdemo.R;


/**
 * Created by dllo on 16/7/19.
 * 指南二级和指南复用页的二级,这个是攻略 - 品类,风格,对象 点进去的三级界面的activity
 * 网址的 Activity  里面有一个webview的打开网址的控件
 */
public class WebJumpActivity extends AbsBaseActivity implements View.OnClickListener {

    private WebView webView;

    private String urls;
    private ImageView imagback;

    @Override
    protected int setLayout() {
        return R.layout.activity_jump_web;
    }

    @Override
    protected void initViews() {

        webView = byView(R.id.my_webjump_webview);
        imagback = byView(R.id.web_back);
    }

    @Override
    protected void initDatas() {


        imagback.setOnClickListener(this);
        //接收从fragment传来的值
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.urls = bundle.getString("url");

        showWeb();
    }


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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 只能通过 返回键的ID让它finish 不能让它直接跳转.
            case R.id.web_back:
                finish();
                break;
        }

    }
}
