package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import comnd.example.dllo.mygiftdemo.ui.adapter.SortPlFgDxAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortRvAdapter;

/**
 * Created by dllo on 16/7/15.
 * 攻略
 * 攻略界面上面是一个RV.下面是三个Gridview
 * 三个Gridview 共用一个布局和 适配器.
 */
public class SortStrtegyFragment extends AbsBaseFragment implements VolleyResult {

    private RecyclerView recyclerView;
    private String LanMuURL = MyStrURL.SORT_LANMU_URL;
    private SortRvAdapter rvAdapter;
    private int type;
    private GridView gvPL;
    private GridView gvStyle;
    private GridView gvDX;
    private String myUrl = MyStrURL.SORT_PLFGDX_URL;
    private SortPlFgDxAdapter myadapter;


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


    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        switch (type) {
            case 0:
                // 解析
                // 初始化存放我是数据类的数组
                List<LanMuBean.DataBean.ColumnsBean> datas = new ArrayList<>();
                // 初始化我的数据类
                LanMuBean lanMuBean = gson.fromJson(str, LanMuBean.class);
                // 根据我数据类中的内容多少来循环
                for (int i = 0; i < lanMuBean.getData().getColumns().size(); i++) {
                    // 从我数据类中得到内容组
                    LanMuBean.DataBean.ColumnsBean columnsBean = lanMuBean.getData().getColumns().get(i);
                    // 将内容add进
                    datas.add(columnsBean);
                }
                rvAdapter = new SortRvAdapter(context);

                rvAdapter.setBeans(datas);
                recyclerView.setAdapter(rvAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(gridLayoutManager);

                type = 1;
                VolleyInstance.getInstance(context).startRequest(myUrl, this);

                break;

            case 1:
                // 初始化存储存储数据类的数组,用的是一个数据类,所以只有名字不一样
                List<PlFgDxBean.DataBean.ChannelGroupsBean.ChannelsBean> pbean = new ArrayList<>();
                List<PlFgDxBean.DataBean.ChannelGroupsBean.ChannelsBean> fbean = new ArrayList<>();
                List<PlFgDxBean.DataBean.ChannelGroupsBean.ChannelsBean> dbean = new ArrayList<>();
                // 通过gson初始化我的数据类
                PlFgDxBean bean = gson.fromJson(str, PlFgDxBean.class);
                // 循环判断第一层object的大小,得到第一层object里面的内容
                for (int i = 0; i < bean.getData().getChannel_groups().size(); i++) {

                    PlFgDxBean.DataBean.ChannelGroupsBean data = bean.getData().getChannel_groups().get(i);


                    // 通过switch-case 分别加入不同的gridview里
                    switch (i) {
                        case 0:
                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                // 加到第一个 集合中
                                pbean.add(data.getChannels().get(i1));
                            }
                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(pbean);
                            gvPL.setAdapter(myadapter);

                            break;
                        case 1:


                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {

                                fbean.add(data.getChannels().get(i1));


                            }

                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(fbean);
                            gvStyle.setAdapter(myadapter);


                            break;
                        case 2:

                            for (int i1 = 0; i1 < data.getChannels().size(); i1++) {
                                dbean.add(data.getChannels().get(i1));

                            }
                            myadapter = new SortPlFgDxAdapter(context);
                            myadapter.setBeans(dbean);
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
}
