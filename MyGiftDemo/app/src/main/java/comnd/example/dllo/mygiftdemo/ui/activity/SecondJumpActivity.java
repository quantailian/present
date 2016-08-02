package comnd.example.dllo.mygiftdemo.ui.activity;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.widget.ImageView;

import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;


import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LUNBOJinRu;

import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.model.net.VolleyShare;
import comnd.example.dllo.mygiftdemo.ui.adapter.SecondJumpAdapter;
import comnd.example.dllo.mygiftdemo.ui.fragment.FristFragment;

/**
 * Created by dllo on 16/7/18.
 * 轮播图的二级界面
 * 这里的URL是拼接的
 */
public class SecondJumpActivity extends AbsBaseActivity implements VolleyResult, View.OnClickListener {
    private String URL;
    private ListView listView;
    private ImageView imageBack;

    private ImageView image_fenxiang;


    @Override
    protected int setLayout() {
        return R.layout.activity_second_jump;
    }

    @Override
    protected void initViews() {

        listView = byView(R.id.secondaryJump_lv);
        imageBack = byView(R.id.jump_back);
        image_fenxiang = byView(R.id.dump_fenxiang);

    }

    @Override
    protected void initDatas() {
        // 返回键
        imageBack.setOnClickListener(this);
        // 分享键点击事件
        image_fenxiang.setOnClickListener(this);

        // 接收值
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.URL = bundle.getString("url");
        VolleyInstance.getInstance(this).startRequest(URL, this);


    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        LUNBOJinRu lunboBean = gson.fromJson(str, LUNBOJinRu.class);

        SecondJumpAdapter jumpAdapter = new SecondJumpAdapter(this);
        jumpAdapter.setBean(lunboBean);
        listView.setAdapter(jumpAdapter);

    }

    @Override
    public void failure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jump_back:
                finish();
                break;
            case R.id.dump_fenxiang:

                // 分享
                VolleyShare.getShare(this).showShare(this, "礼物说,全球最好的东西", "http://www.liwushuo.com",
                        null, "http://www.liwushuo.com", null, null, null);

                break;
        }

    }


}
