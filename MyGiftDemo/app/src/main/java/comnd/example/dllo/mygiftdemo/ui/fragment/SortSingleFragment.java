package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortSingleGvAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortSingleLvAdapter;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortSingleRightAdapter;


/**
 * Created by dllo on 16/7/15.
 * 单品
 * 由两个listview占据.
 * 共用一个URL
 * 右面的listview里面套了一个gridview/
 * gridview 的解析是在右面listview的适配器中进行的.
 */
public class SortSingleFragment extends AbsBaseFragment implements VolleyResult, View.OnTouchListener, AdapterView.OnItemClickListener {

    private ListView leftLv;
    private ListView reightLv;
    private String singleUrl = MyStrURL.SORL_DANPIN_URL;

    private View clickSource,touchSource;
    private int offset =0;
    private SortSingleRightAdapter rightAdapter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_sort_single;
    }

    @Override
    protected void initView() {
        leftLv = byView(R.id.left_listView);
        reightLv = byView(R.id.right_listView);


    }

    @Override
    protected void initDatas() {

        VolleyInstance.getInstance(context).startRequest(singleUrl,this);
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();

        SingleBean bean = gson.fromJson(str, SingleBean.class);
        List<SingleBean.DataBean.CategoriesBean> data = new ArrayList<>();
        for (int i = 0; i < bean.getData().getCategories().size(); i++) {

            data.add(bean.getData().getCategories().get(i));
        }
        SortSingleLvAdapter adapter = new SortSingleLvAdapter(context);
        adapter.setData(data);
        leftLv.setAdapter(adapter);

        // 这个是第二个listview初始化适配器
        rightAdapter = new SortSingleRightAdapter(context);
        rightAdapter.setBeans(data);
        reightLv.setAdapter(rightAdapter);
        // 给右面的listview 设置滑动点击事件
        reightLv.setOnTouchListener(this);
        leftLv.setOnItemClickListener(this);
    }






    @Override
    public void failure() {

    }


    /**
     * listview 滑动事件 右侧listview 滑动 左侧listview 根据位置跳到相应位置.
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (touchSource == null){
            touchSource = v;
        }
        if (v == touchSource){
            leftLv.dispatchTouchEvent(event);
            if (event.getAction() == MotionEvent.ACTION_UP){
                clickSource = v;
                touchSource = null;
            }
        }
        return false;
    }

    /**
     * listview 的点击事件
     * 双listview联动 点击左侧listview 右侧跳到相应位置
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 右侧listview 跳到当前左侧listview的位置
        reightLv.setSelection(position);
        // 通知适配器刷新位置
        rightAdapter.notifyDataSetChanged();
    }
}
