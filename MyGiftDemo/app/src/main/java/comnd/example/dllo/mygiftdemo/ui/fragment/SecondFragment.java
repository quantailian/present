package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;



import comnd.example.dllo.mygiftdemo.R;


import comnd.example.dllo.mygiftdemo.model.bean.HTBean;


import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;


import comnd.example.dllo.mygiftdemo.ui.activity.WebJumpActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.FuyongAdapter;

/**
 * Created by dllo on 16/7/12.
 */
public class SecondFragment extends AbsBaseFragment implements VolleyResult, AdapterView.OnItemClickListener {

    private ListView listView;
    private String url;
    private HTBean htBean;


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

        listView.setOnItemClickListener(this);

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
        htBean = gson.fromJson(str, HTBean.class);

        FuyongAdapter adapter = new FuyongAdapter(context);
        adapter.setBeans(htBean);
        listView.setAdapter(adapter);

    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析获取失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString("url",htBean.getData().getItems().get(position).getUrl());
        goTo(context, WebJumpActivity.class,bundle);
    }
}


