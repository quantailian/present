package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.ui.adapter.SortAdapter;

/**
 * Created by dllo on 16/7/11.
 * 分类界面的 fragment
 * 首先 头部是tablayout和viewpager联用.
 * 我们给设置 攻略 那个标题栏和搜索框固定.
 */
public class SortFragment extends AbsBaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView xuanLiTv;


    @Override
    protected int setLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView() {
        tabLayout = byView(R.id.my_sort_tablayout);
        viewPager = byView(R.id.my_sort_viewpager);
        xuanLiTv = byView(R.id.sort_tv);

    }

    @Override
    protected void initDatas() {
        //View.VISIBLE View可见
        // View.INVISIBLE View不可以见，但仍然占据可见时的大小和位置。
        //View.GONE View不可见，且不占据空间。
        // 开始让我的tv不可见, 而且不占据空间
        xuanLiTv.setVisibility(View.GONE);
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new SortStrtegyFragment());
        arrayList.add(new SortSingleFragment());
        SortAdapter adapter = new SortAdapter(getChildFragmentManager());
        adapter.setArrayList(arrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);


        // 在我的viewpager的监听事件上, 如果在第0个位置.则不可见.否则显示
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (tabLayout.getSelectedTabPosition() == 0) {
                    xuanLiTv.setVisibility(View.GONE);
                } else {
                    xuanLiTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
