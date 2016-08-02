package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleJumpAcBean;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortSingleJumpAdapter;

/**
 * Created by dllo on 16/7/21.
 * 单品中的grid点进去的activity
 */
public class SortSingleJumpActivity extends AbsBaseActivity implements VolleyResult, View.OnClickListener, AdapterView.OnItemClickListener {
    private String url;
    private TextView tvTitle;
    private GridView gridView;
    private ImageView imageBack;
    private SingleJumpAcBean bean;

    @Override
    protected int setLayout() {
        return R.layout.activity_jump_sortsingle;
    }

    @Override
    protected void initViews() {
        tvTitle = byView(R.id.jump_single_tvtitle);
        gridView = byView(R.id.jump_single_gridview);
        imageBack = byView(R.id.jump_single_img_back);

    }

    @Override
    protected void initDatas() {


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        tvTitle.setText(bundle.getString("title"));
        imageBack.setOnClickListener(this);
        gridView.setOnItemClickListener(this);

        VolleyInstance.getInstance(this).startRequest(url,this);


    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        bean = gson.fromJson(str,SingleJumpAcBean.class);

        SortSingleJumpAdapter adapter = new SortSingleJumpAdapter(this);
        adapter.setBean(bean);
        gridView.setAdapter(adapter);


    }

    @Override
    public void failure() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jump_single_img_back:
                 finish();
                break;
        }
    }

    // 二级界面的行监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.jump_single_gridview:
                Bundle bundle = new Bundle();
                bundle.putString("url",bean.getData().getItems().get(position).getUrl());
                goTo(this,WebJumpActivity.class,bundle);

                break;
        }
    }
}
