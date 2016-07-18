package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;

import comnd.example.dllo.mygiftdemo.model.bean.FuyongBean;
import comnd.example.dllo.mygiftdemo.model.bean.HTBean;


import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;


import comnd.example.dllo.mygiftdemo.ui.adapter.FuyongAdapter;

/**
 * Created by dllo on 16/7/12.
 */
public class SecondFragment extends AbsBaseFragment implements VolleyResult {

    private ListView listView;
    private String url;


    @Override
    protected int setLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.my_second_fragment_listview);
    }

    @Override
    protected void initDatas() {
        // 得到参数
        Bundle bundle = getArguments();
        this.url = bundle.getString("url");
        VolleyInstance.getInstance(context).startRequest(url, this);


    }

    /**
     * 复用fragment的方法
     * @param url 通过不同的url实现复用
     * @return
     */
    public static SecondFragment getFragments(String url) {
        SecondFragment secondFragment = new SecondFragment();
        // 利用Activity 向fragment里传值   key - value 形式
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
         // setArgument 来传递参数. getArgument 得到参数
        secondFragment.setArguments(bundle);
        return secondFragment;

    }

    @Override
    public void success(String str) {
        // 解析数据
        Gson gson = new Gson();
        HTBean htBean = gson.fromJson(str, HTBean.class);
        List<HTBean.DataBean.ItemsBean> data = htBean.getData().getItems();
        ArrayList<FuyongBean> beans = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String contentTitle = data.get(i).getTitle();
            String coverImage = data.get(i).getCover_image_url();
            String likeCount = String.valueOf(data.get(i).getLikes_count());
            // items - author这一层
            String touxiang = data.get(i).getAuthor().getAvatar_url();
            String nickname = data.get(i).getAuthor().getNickname();
            // items - column这一层
            String category = data.get(i).getColumn().getCategory();
            String listviewTitle = data.get(i).getColumn().getTitle();
            beans.add(new FuyongBean(contentTitle, coverImage,
                    likeCount, touxiang, nickname, category, listviewTitle));
        }

        FuyongAdapter adapter = new FuyongAdapter(context);
        adapter.setBeans(beans);
        listView.setAdapter(adapter);

    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析获取失败", Toast.LENGTH_SHORT).show();
    }


}


