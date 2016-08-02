package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.OneJumpLVBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.tools.MyCustomListView;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortStregyOneJumpLVAdapter;

/**
 * Created by dllo on 16/7/20.
 * 攻略页面第一个栏目的二级界面
 */
public class SortStregyOneJumpActivity extends AbsBaseActivity implements VolleyResult, View.OnClickListener {
    //定义一个URL,从上一级传过来
    private String url;
    private MyCustomListView listView;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView imageback;

    @Override
    protected int setLayout() {
        return R.layout.activity_jump_one_sortstregy;
    }

    @Override
    protected void initViews() {

        listView = byView(R.id.one_sortstregy_activity_listview);
        imageView = byView(R.id.one_sortstregy_img);
        tvTitle = byView(R.id.one_sortstregy_tvtitle);
        tvContent = byView(R.id.one_sortstregy_content);
        imageback = byView(R.id.one_sortstregy_img_back);
    }

    @Override
    protected void initDatas() {

        // 接收数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // 接收URL
        this.url = bundle.getString("url");
        // 接收上面的图片 文字 标题
        SingleLoadingImageView.loadImageView(bundle.getString("image"),imageView,this);
        tvContent.setText(bundle.getString("content"));
        tvTitle.setText(bundle.getString("title"));


        imageback.setOnClickListener(this);
        VolleyInstance.getInstance(this).startRequest(url,this);

    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        OneJumpLVBean bean = gson.fromJson(str,OneJumpLVBean.class);

        SortStregyOneJumpLVAdapter adapter = new SortStregyOneJumpLVAdapter(this);
        adapter.setBean(bean);
        listView.setAdapter(adapter);
    }

    @Override
    public void failure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_sortstregy_img_back:
                finish();
                break;
        }
    }



}
