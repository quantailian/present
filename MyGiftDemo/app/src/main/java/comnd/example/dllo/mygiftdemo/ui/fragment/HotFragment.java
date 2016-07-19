package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.google.gson.Gson;
import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.HotBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.activity.JumpBabyDetailsActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.HotGridAdapter;

/**
 * Created by dllo on 16/7/11.
 * 热门的 fragment
 */
public class HotFragment extends AbsBaseFragment implements VolleyResult, AdapterView.OnItemClickListener{

    private GridView hotgridview;
    String url = MyStrURL.HOT_FM_GV_URL;
    private HotBean bean;


    @Override
    protected int setLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {

        hotgridview = byView(R.id.my_hot_gridview);


    }

    @Override
    protected void initDatas() {
        hotgridview.setOnItemClickListener(this);
        VolleyInstance.getInstance(context).startRequest(url, this);

        hotgridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "网络解析错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    public void success(String str) {
        // 网络解析
        Gson gson = new Gson();
        bean = gson.fromJson(str, HotBean.class);
        HotGridAdapter adapter = new HotGridAdapter(context);
        adapter.setBeans(bean);
        hotgridview.setAdapter(adapter);
    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, "是打发斯蒂芬是打发放撒艾弗森", Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("url",bean.getData().getItems().get(position).getData().getUrl());
        goTo(context, JumpBabyDetailsActivity.class,bundle);

    }

}
