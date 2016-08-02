package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SecondJumpLVBean;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.tools.MyCustomListView;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortStregySecondJumpLVAdapter;

/**
 * Created by dllo on 16/7/20.
 * 品类 风格 对象点进去的二级界面
 * 在这里也解析跳进去的三级界面
 */
public class SortStregySecondJumpActivity extends AbsBaseActivity implements VolleyResult, View.OnClickListener, AdapterView.OnItemClickListener {

    private MyCustomListView listView;
    private String url;

    private TextView mytitle;
    private ImageView imageback;
    private SecondJumpLVBean bean;

    @Override
    protected int setLayout() {
        return R.layout.activity_jump_second_sortstregy;
    }

    @Override
    protected void initViews() {
        listView = byView(R.id.second_sortstregy_activity_listview);
        mytitle = byView(R.id.second_sortstregy_tvtitle);
        imageback = byView(R.id.second_sortstregy_img_back);


    }

    @Override
    protected void initDatas() {
        // 收值
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.url = bundle.getString("url");
        // 设置title
        mytitle.setText(bundle.getString("title"));
        imageback.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        VolleyInstance.getInstance(this).startRequest(url,this);

    }

    @Override
    public void success(String str) {
        // 解析
        Gson gson = new Gson();
        bean = gson.fromJson(str,SecondJumpLVBean.class);

        SortStregySecondJumpLVAdapter adapter = new SortStregySecondJumpLVAdapter(this);
        adapter.setSecondJumpLVBean(bean);
        listView.setAdapter(adapter);
    }

    @Override
    public void failure() {

    }

    // 当按返回键图片时 finish
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.second_sortstregy_img_back:
                finish();
                break;
        }
    }

    // listview 的行监听.点击行监听将值传入下一个activity
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.second_sortstregy_activity_listview:
            Bundle bundle = new Bundle();
            bundle.putString("url", bean.getData().getItems().get(position).getUrl());
            Log.d("AAA", "A" + bean.getData().getItems().get(position).getUrl());
            goTo(this, WebJumpActivity.class, bundle);
                break;
        }

    }
}
