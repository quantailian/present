package comnd.example.dllo.mygiftdemo.ui.fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import android.widget.ListView;


import com.google.gson.Gson;


import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;
import comnd.example.dllo.mygiftdemo.model.net.MyStrURL;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
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
public class SortSingleFragment extends AbsBaseFragment implements VolleyResult {

    private ListView leftLv;
    private ListView reightLv;
    private String singleUrl = MyStrURL.SORL_DANPIN_URL;

    private SortSingleRightAdapter rightAdapter;
    private SingleBean bean;
    // 是否滑动
    private boolean isScrool = true;

    private SortSingleLvAdapter adapter;


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

        VolleyInstance.getInstance(context).startRequest(singleUrl, this);
        // 联动
        Linkage();

    }


    @Override
    public void success(String str) {
        Gson gson = new Gson();

        bean = gson.fromJson(str, SingleBean.class);

        adapter = new SortSingleLvAdapter(context);
        adapter.setData(bean);
        leftLv.setAdapter(adapter);

        // 这个是第二个listview初始化适配器

        rightAdapter = new SortSingleRightAdapter(context);
        rightAdapter.setBeans(bean);
        reightLv.setAdapter(rightAdapter);


    }


    @Override
    public void failure() {

    }




    // 两个listview联动
    private void Linkage(){
        // 左面listview 的点击事件
        leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 点击左面的listview的时候. 不滑动
                isScrool = false;
                for (int i = 0; i < leftLv.getChildCount(); i++) {
                    if (i == position){
                        leftLv.getChildAt(i).setBackgroundColor(Color.WHITE);


                    }

                    else {
                        leftLv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                // 当点击左面的listview后,右面的listview得到位置
                reightLv.setSelection(position);
                // 滑动的位置
                reightLv.smoothScrollToPosition(position);

            }
        });

        // 右侧listview的滑动事件
        reightLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (isScrool){
                    for (int i = 0; i < leftLv.getChildCount(); i++) {
                        if (i == firstVisibleItem){
                            leftLv.getChildAt(i).setBackgroundColor(Color.RED);


                        }
                        else {
                            leftLv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);

                        }
                    }


                }
                else {
                    isScrool = true;
                }
            }
        });


    }

}
