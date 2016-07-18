package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LocalGuideFirstLvBean;
import comnd.example.dllo.mygiftdemo.model.bean.MyCustomBean;
import comnd.example.dllo.mygiftdemo.model.bean.MyFristItemBean;
import comnd.example.dllo.mygiftdemo.model.bean.MyLunBoBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.CustomListViewAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.MyFristItemAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.MyRVOnClickListener;

/**
 * Created by dllo on 16/7/12.
 * guide 的第一个fragment
 * 里面放了三个网络解析
 */
public class FristFragment extends AbsBaseFragment implements VolleyResult, Banner.OnBannerClickListener, MyRVOnClickListener {
    // banner 对象
    private Banner rollImgBanner;
    // 初始化banner接口
    private String bannerUrl = MyStrURL.MY_LUNBO;
    // 图片的URL
    private String[] imageUrls;
    // 滑动recycleview的接口
    private String itemUrl = MyStrURL.MY_FRIST_ITEM;
    // 首页listview接口
    private String listviewUrl = MyStrURL.FRIST_LISTVIEW;
    private Gson gson;
    private RecyclerView recyclerView;
    private int type;
    private ArrayList<String> arrayList;
    private ListView listView;


    @Override
    protected int setLayout() {
        return R.layout.fragment_frist;
    }

    @Override
    protected void initView() {
        rollImgBanner = byView(R.id.rollimg_banner);
        recyclerView = byView(R.id.my_frist_recycleview);
        listView = byView(R.id.my_frist_listview);
    }

    @Override
    protected void initDatas() {

        type = 0;
        VolleyInstance.getInstance(context).startRequest(bannerUrl, this);


    }

    /**
     * 轮播图
     */
    public void showBanner() {

        // 设置小圆点
        rollImgBanner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        // 设置位置 居中
        rollImgBanner.setIndicatorGravity(Banner.CENTER);
        // 设置轮播时间
        rollImgBanner.setDelayTime(3000);
        // 设置图片
        rollImgBanner.setImages(imageUrls);
        // 给banner加监听事件
        rollImgBanner.setOnBannerClickListener(this);
    }

    @Override
    public void success(String str) {
        // 解析
        gson = new Gson();
        switch (type) {

            case 0:
                MyLunBoBean boBean = gson.fromJson(str, MyLunBoBean.class);
                // 获取数据
                List<MyLunBoBean.DataBean.BannersBean> bean = boBean.getData().getBanners();
                //初始化数组 加入数据
                imageUrls = new String[bean.size()];
                for (int i = 0; i < bean.size(); i++) {
                    imageUrls[i] = bean.get(i).getImage_url();
                }
                // 显示banner
                showBanner();
                type = 1;
                VolleyInstance.getInstance(context).startRequest(itemUrl, this);
                break;

            case 1:

                MyFristItemBean fristItemBean = gson.fromJson(str, MyFristItemBean.class);
                List<MyFristItemBean.DataBean.SecondaryBannersBean> beans = fristItemBean.getData().getSecondary_banners();
                arrayList = new ArrayList<>();
                for (int i = 0; i < beans.size(); i++) {
                    String url = beans.get(i).getImage_url();
                    arrayList.add(url);

                }
                // 初始化适配器
                MyFristItemAdapter itemAdapter = new MyFristItemAdapter(context);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                itemAdapter.setImageUrls(arrayList);
                recyclerView.setAdapter(itemAdapter);
                itemAdapter.setMyrvOnClickListener(this);
                type = 2;
                VolleyInstance.getInstance(context).startRequest(listviewUrl,this);
                break;

            case 2:
                MyCustomBean customBean = gson.fromJson(str,MyCustomBean.class);
                List<MyCustomBean.DataBean.ItemsBean> mybeans = customBean.getData().getItems();
                ArrayList<LocalGuideFirstLvBean> localGuideFirstLvBeen = new ArrayList<>();
                for (int i = 0; i < mybeans.size(); i++) {
                    String imagurl = mybeans.get(i).getCover_image_url();
                    String title = mybeans.get(i).getTitle();
                    String likeCount = String.valueOf(mybeans.get(i).getLikes_count());
                    String shortTitle = mybeans.get(i).getShort_title();
                    localGuideFirstLvBeen.add(new LocalGuideFirstLvBean(imagurl,title,likeCount,shortTitle));
                }
                //  初始化适配器
                CustomListViewAdapter listViewAdapter = new CustomListViewAdapter(context);
                listViewAdapter.setBeans(localGuideFirstLvBeen);
                listView.setAdapter(listViewAdapter);



                break;
        }
    }

    @Override
    public void failure() {
        Toast.makeText(context, "获取失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnBannerClick(View view, int position) {

        Toast.makeText(context, "我是轮播图的监听事件", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void rvOnClickListener(int pos) {

        Toast.makeText(context, "我是RV的监听", Toast.LENGTH_SHORT).show();
    }
}
