package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LanMuBean;
import comnd.example.dllo.mygiftdemo.model.bean.PlFgDxBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.activity.SortStregyOneJumpActivity;
import comnd.example.dllo.mygiftdemo.ui.activity.SortStregySecondJumpActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.MySortRvOnClickListener;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortPlFgDxAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortRvAdapter;

/**
 * Created by dllo on 16/7/15.
 * 攻略
 * 攻略界面上面是一个RV.下面是三个Gridview
 * 三个Gridview 共用一个布局和 适配器.
 */
public class SortStrtegyFragment extends AbsBaseFragment implements VolleyResult, MySortRvOnClickListener, AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;
    // 栏目的URL
    private String LanMuURL = MyStrURL.SORT_LANMU_URL;
    private SortRvAdapter rvAdapter;
    // 根据type来定义解析顺序
    private int type;
    private GridView gvPL;
    private GridView gvStyle;
    private GridView gvDX;
    // 下面三个复用的gridview的URL
    private String myUrl = MyStrURL.SORT_PLFGDX_URL;
    private SortPlFgDxAdapter myadapter;


    private PlFgDxBean bean;
    // 总的数据类
    private PlFgDxBean.DataBean.ChannelGroupsBean data;
    // 三个不同数据类
    private PlFgDxBean.DataBean.ChannelGroupsBean classplbean;
    private PlFgDxBean.DataBean.ChannelGroupsBean classfgbean;
    private PlFgDxBean.DataBean.ChannelGroupsBean classdxbean;
    private LanMuBean lanMuBean;


    @Override
    protected int setLayout() {
        return R.layout.fragment_sort_strtegy;
    }

    @Override
    protected void initView() {

        recyclerView = byView(R.id.my_sort_strtegy_rv);
        // 三个gridview
        gvPL = byView(R.id.my_sort_strtegy_gvpinlei);
        gvStyle = byView(R.id.my_sort_strtegy_gvstyle);
        gvDX = byView(R.id.my_sort_strtegy_gvduixiang);


    }

    @Override
    protected void initDatas() {

        VolleyInstance.getInstance(context).startRequest(LanMuURL, this);
        gvPL.setOnItemClickListener(this);
        gvStyle.setOnItemClickListener(this);
        gvDX.setOnItemClickListener(this);


    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        switch (type) {
            case 0:
                // 解析
                // 初始化我的数据类
                lanMuBean = gson.fromJson(str, LanMuBean.class);
                // 适配器
                rvAdapter = new SortRvAdapter(context);
                rvAdapter.setBean(lanMuBean);
                recyclerView.setAdapter(rvAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(gridLayoutManager);
                rvAdapter.setMySortRvOnClickListener(this);
                type = 1;
                VolleyInstance.getInstance(context).startRequest(myUrl, this);

                break;

            case 1:

                // 通过gson初始化我的数据类
                bean = gson.fromJson(str, PlFgDxBean.class);

                // 循环判断第一层object的大小,得到第一层object里面的内容
                for (int i = 0; i < bean.getData().getChannel_groups().size(); i++) {

                    data = bean.getData().getChannel_groups().get(i);

                    // 通过switch-case 分别加入不同的gridview里
                    // 里面每一个case都建了个数据类,让它的值等于我们的data.然后将不同的数据类里的值进行网址拼接.
                    switch (i) {
                        case 0:

                            classplbean = data;
                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(data);
                            gvPL.setAdapter(myadapter);

                            break;
                        case 1:

                            classfgbean = data;
                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(data);
                            gvStyle.setAdapter(myadapter);


                            break;
                        case 2:
                            classdxbean = data;
                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(data);
                            gvDX.setAdapter(myadapter);


                            break;

                    }

                }
                break;


        }
    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析失败", Toast.LENGTH_SHORT).show();

    }


    //rv的点击事件
    @Override
    public void sortRvOnClickListener(int pos) {
        // 传值
        Bundle bundle = new Bundle();
        bundle.putString("url", "http://api.liwushuo.com/v2/columns/"
                + lanMuBean.getData().getColumns().get(pos).getId() + "?limit=20&offset=0");
        bundle.putString("image",lanMuBean.getData().getColumns().get(pos).getBanner_image_url());
        bundle.putString("content",lanMuBean.getData().getColumns().get(pos).getDescription());
        bundle.putString("title",lanMuBean.getData().getColumns().get(pos).getTitle());
        goTo(context, SortStregyOneJumpActivity.class,bundle);

    }


    // parent是listview或者gridview中装的一切控件.只有选择他得到ID,才能为我的listview设置行监听.
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        switch (parent.getId()) {

            case R.id.my_sort_strtegy_gvpinlei:
                // 拼接网址 传过去.

                bundle.putString("url",
                        "http://api.liwushuo.com/v2/channels/"
                                + classplbean.getChannels().get(position).getId() + "/items?limit=20&offset=0");
                bundle.putString("title", classplbean.getChannels().get(position).getName());
                goTo(context, SortStregySecondJumpActivity.class, bundle);

                Toast.makeText(context, "我是GV的行监听", Toast.LENGTH_SHORT).show();
                break;

            case R.id.my_sort_strtegy_gvstyle:
                // 拼接网址 传过去.

                bundle.putString("url",
                        "http://api.liwushuo.com/v2/channels/"
                                + classfgbean.getChannels().get(position).getId() + "/items?limit=20&offset=0");
                bundle.putString("title", classfgbean.getChannels().get(position).getName());
                goTo(context, SortStregySecondJumpActivity.class, bundle);

                break;

            case R.id.my_sort_strtegy_gvduixiang:
                // 拼接网址 传过去.

                bundle.putString("url",
                        "http://api.liwushuo.com/v2/channels/"
                                + classdxbean.getChannels().get(position).getId() + "/items?limit=20&offset=0");
                bundle.putString("title", classdxbean.getChannels().get(position).getName());
                goTo(context, SortStregySecondJumpActivity.class, bundle);

                break;


        }
    }
}
