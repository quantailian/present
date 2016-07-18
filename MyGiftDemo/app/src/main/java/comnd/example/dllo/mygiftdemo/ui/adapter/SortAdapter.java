package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/15.
 * 分类页面上方的tablayout和viewpager联动
 * viewpager的适配器.
 */
public class SortAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayList;
    private ArrayList<String> title;
    public SortAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setArrayList(ArrayList<Fragment> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
        title = new ArrayList<>();
        title.add("攻略");
        title.add("单品");
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList!=null?arrayList.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
