package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.HTBean;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.tools.CustomListView;
import comnd.example.dllo.mygiftdemo.ui.activity.WebJumpActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.FuyongAdapter;

/**
 * Created by dllo on 16/7/12.
 * 指南页面第二个 fragment 被复用这个页面
 * 我们为这个复用的fragment加了个刷新功能
 */
public class SecondFragment extends AbsBaseFragment implements VolleyResult, AdapterView.OnItemClickListener {

    private CustomListView listView;
    private String url;
    private HTBean htBean;
    private FuyongAdapter adapter;


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


        // 我的自定义lv继承自定义的接口
        listView.setonRefreshListener(new CustomListView.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // 异步任务
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                            adapter = new FuyongAdapter(context);
                            adapter.setBeans(htBean);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }



                        return null;
                    }

                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    };

                }.execute();
            }
        });

    }

    /**
     * 复用fragment的方法
     *
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

        adapter = new FuyongAdapter(context);
        adapter.setBeans(htBean);
        listView.setAdapter(adapter);

    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析获取失败", Toast.LENGTH_SHORT).show();
    }


    // 行监听点进去的二级页面传值
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Bundle bundle = new Bundle();
        bundle.putString("url", htBean.getData().getItems().get(position).getUrl());
        goTo(context, WebJumpActivity.class, bundle);
    }



}


