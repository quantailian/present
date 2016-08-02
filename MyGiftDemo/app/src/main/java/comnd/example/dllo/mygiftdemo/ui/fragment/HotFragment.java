package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.db.DaoInstance;
import comnd.example.dllo.mygiftdemo.db.DaoSession;
import comnd.example.dllo.mygiftdemo.db.PresentBean;
import comnd.example.dllo.mygiftdemo.model.bean.HotBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.activity.JumpBabyDetailsActivity;
import comnd.example.dllo.mygiftdemo.ui.activity.SearchActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.HotGridAdapter;

/**
 * Created by dllo on 16/7/11.
 * 热门的 fragment
 */
public class HotFragment extends AbsBaseFragment implements VolleyResult, AdapterView.OnItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private GridView hotgridview;
    String url = MyStrURL.HOT_FM_GV_URL;
    private HotBean bean;
    private ImageView imageSearch;
    // 初始化我的刷新
    private SwipeRefreshLayout swipeRefreshLayout;
    private HotGridAdapter adapter;




    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        hotgridview = byView(R.id.my_hot_gridview);
        imageSearch = byView(R.id.my_hot_search);
        swipeRefreshLayout = byView(R.id.hot_refresh);

    }

    @Override
    protected void initDatas() {
        VolleyInstance.getInstance(context).startRequest(url, this);
        hotgridview.setOnItemClickListener(this);
        imageSearch.setOnClickListener(this);
        // 设置刷新动画的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
       //swipeRefreshLayout.setProgressBackgroundColorSchemeColor();
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    public void success(String str) {
        // 网络解析
        Gson gson = new Gson();
        bean = gson.fromJson(str, HotBean.class);
        adapter = new HotGridAdapter(context);


        adapter.setBeans(bean);
        hotgridview.setAdapter(adapter);

    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



        Bundle bundle = new Bundle();
        bundle.putString("url",bean.getData().getItems().get(position).getData().getUrl());
        goTo(context, JumpBabyDetailsActivity.class,bundle);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_hot_search:
                go2(context, SearchActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (bean.getData().getItems().size()>0){

            adapter.setBeans(bean);
            hotgridview.setAdapter(adapter);
        }

       swipeRefreshLayout.setRefreshing(false);
    }
}
