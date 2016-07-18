package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.util.Log;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.HotBean;
import comnd.example.dllo.mygiftdemo.model.bean.LocalhotBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.HotGridAdapter;

/**
 * Created by dllo on 16/7/11.
 * 热门的 fragment
 */
public class HotFragment extends AbsBaseFragment implements VolleyResult {

    private GridView hotgridview;
    String url = MyStrURL.HOT_FM_GV_URL;
    private ArrayList<LocalhotBean> beans;


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

        VolleyInstance.getInstance(context).startRequest(url, this);

    }

    @Override
    public void success(String str) {
        // 网络解析
        Gson gson = new Gson();
        HotBean bean = gson.fromJson(str, HotBean.class);

        List<HotBean.DataBean.ItemsBean> data = bean.getData().getItems();
        beans = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            String imageCount = data.get(i).getData().getCover_image_url();
            String name = data.get(i).getData().getName();
            String price = data.get(i).getData().getPrice();
            String likeCount = String.valueOf(data.get(i).getData().getFavorites_count());

            beans.add(new LocalhotBean(name, likeCount, imageCount, price));

        }
        HotGridAdapter adapter = new HotGridAdapter(context);
        adapter.setBeans(beans);

        hotgridview.setAdapter(adapter);
    }

    @Override
    public void failure() {
        Toast.makeText(context, "网络解析错误", Toast.LENGTH_SHORT).show();
    }
}
