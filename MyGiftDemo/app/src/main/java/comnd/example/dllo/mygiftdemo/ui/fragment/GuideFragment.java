package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.LoginActivity;
import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.GuideFragmentTitleBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.GuideFristAdapter;

/**
 * Created by dllo on 16/7/11.
 * 指南页面的 主fragment
 */
public class GuideFragment extends AbsBaseFragment implements VolleyResult {
    //定义Volley请求队列
//    private RequestQueue queue;
    private String url = MyStrURL.MY_TITLE;
    private ViewPager viewpager;
    private ArrayList<Fragment> arrayList;
    private GuideFristAdapter adapter;
    private TabLayout tabLayout;
    private ArrayList<String> titleBean;
    private ImageView imageView;


    @Override
    protected int setLayout() {

        getActivity().getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.guide_title);

        return R.layout.fragment_guide;


    }

    @Override
    protected void initView() {

        tabLayout = byView(R.id.my_guide_tablayout);
        viewpager = byView(R.id.my_guide_viewpager);
        imageView = byView(R.id.guide_title_one);

    }

    @Override
    protected void initDatas() {

        arrayList = new ArrayList<>();
        titleBean = new ArrayList<>();
        // 标题栏上的一个图片点击按钮跳转到注册页面
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go2(GuideFragment.this.context, LoginActivity.class);
            }
        });

        // viewpager预加载
        viewpager.setOffscreenPageLimit(16);

        VolleyInstance.getInstance(getContext()).startRequest(url, this);
    }

    @Override
    public void success(String str) {

        Gson gson = new Gson();
        // 解析
        GuideFragmentTitleBean bean = gson.fromJson(str, GuideFragmentTitleBean.class);
        List<GuideFragmentTitleBean.DataBean.ChannelsBean> datas = bean.getData().getChannels();
        for (int i = 0; i < datas.size(); i++) {

            String title = datas.get(i).getName();
            titleBean.add(title);
        }
        // 判断fragment的位置来加布局
        arrayList = new ArrayList<>();

            // 通过复用的方法添加URL实现不同布局
            arrayList.add(new FristFragment());

            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_CYSH_URL));// 穿搭 没有
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_HT_URL)); // 海淘
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SNP_URL));// 送男票
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SJY_URL)); // 礼物 没有
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SGM_URL));// 送闺蜜
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SBM_URL)); // 送爸妈
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_STS_URL));// 送同事
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SJY_URL));// 送机油
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SBB_URL)); // 送宝贝
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SGM_URL)); // 手工  没有
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_SJG_URL));// 设计感
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_CYSH_URL));// 创意生活
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_WYF_URL));// 文艺风
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_KJF_URL));// 科技范
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_MMD_URL));// 萌萌哒
            arrayList.add(SecondFragment.getFragments(MyStrURL.GD_REUSE_QPGG_URL));// 奇葩搞怪

        adapter = new GuideFristAdapter(getChildFragmentManager());
        adapter.setTitle(titleBean, arrayList);
        viewpager.setAdapter(adapter);
        // 设置滑动的tablayout
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 设置引导条颜色
        tabLayout.setSelectedTabIndicatorColor(Color.RED);
        tabLayout.setupWithViewPager(viewpager);


    }

    @Override
    public void failure() {

    }


}
