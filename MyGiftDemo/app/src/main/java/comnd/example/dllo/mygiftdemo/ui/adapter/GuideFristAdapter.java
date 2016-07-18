package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/12.
 * 指南页tablayout和viewpager联动的 适配器
 */
public class GuideFristAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> arrayList;
    private ArrayList<String> title;

    public GuideFristAdapter(FragmentManager fm) {
        super(fm);
    }

    // 设置标题的方法
    public void setTitle(ArrayList<String> title,ArrayList<Fragment> arrayList)  {
        this.title = title;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    // 根据 title的多少来显示多少个fragment
    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
