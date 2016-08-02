package comnd.example.dllo.mygiftdemo.ui.activity;


import android.util.Log;
import android.view.View;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SearchBean;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.model.net.VolleyResult;
import comnd.example.dllo.mygiftdemo.ui.adapter.SearchAdapter;

/**
 * Created by dllo on 16/7/21.
 * 搜索界面
 */
public class SearchActivity extends AbsBaseActivity implements View.OnClickListener, SearchView.OnQueryTextListener, VolleyResult {

    private ImageView imageBack;
    private SearchView searchEd;
    private TextView searchTv;

    private GridView gridView;
    private LinearLayout searchGone;



    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        //返回键
        imageBack = byView(R.id.search_back);
        // 搜索框
        searchEd = byView(R.id.search_ed);
        // 搜索键
        searchTv = byView(R.id.searchTV);

        // gridview
        gridView = byView(R.id.search_gridview);

        // 隐藏的一个linearlayout
        searchGone = byView(R.id.searchGone);
//        // 排序键,当 搜索框输入内容后出现
//        imagePaixu = byView(R.id.search_paixu);

    }

    @Override
    protected void initDatas() {
        imageBack.setOnClickListener(this);
        searchTv.setOnClickListener(this);
        searchEd.setOnQueryTextListener(this);
        searchEd.setSubmitButtonEnabled(false);
        searchEd.setIconifiedByDefault(false);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;

            case R.id.searchTV:

                 finish();
                break;


        }
    }

    // 单击搜索按钮的时候激发的方法
    @Override
    public boolean onQueryTextSubmit(String query) {


        return true;
    }
// 用户输入字符串激发的方法
    @Override
    public boolean onQueryTextChange(String newText) {

        searchGone.setVisibility(View.GONE);

        String s = null;
        try {
            s = new String(newText.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://api.liwushuo.com/v2/search/item?keyword="+s+"&limit=20&offset=0&sort=";

        VolleyInstance.getInstance(this).startRequest(url,this);
        Log.d("aaa",url+"a");


        return true;
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        SearchBean bean = gson.fromJson(str,SearchBean.class);

        SearchAdapter adapter = new SearchAdapter(this);
        adapter.setBean(bean);
        gridView.setAdapter(adapter);

    }

    @Override
    public void failure() {

    }
}
