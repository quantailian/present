package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;

/**
 * Created by dllo on 16/7/16.
 * 单品页面 右面listview 的适配器
 */
public class SortSingleRightAdapter extends BaseAdapter {
    private List<SingleBean.DataBean.CategoriesBean> beans;
    private Context context;

    public SortSingleRightAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(List<SingleBean.DataBean.CategoriesBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans!=null?beans.size():0;
    }



    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RightHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_single_gd_item,parent,false);
            holder = new RightHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (RightHolder) convertView.getTag();
        }

        // 这个是listview中嵌套的gridview ,在这里给它初始化和绑定布局
        SortSingleGvAdapter sortSingleGvAdapter = new SortSingleGvAdapter(context);
        sortSingleGvAdapter.setBeans(beans.get(position).getSubcategories());
        holder.gridView.setAdapter(sortSingleGvAdapter);

        holder.textView.setText("-------------"+beans.get(position).getName()+"----------------");
        return convertView;
    }

    class RightHolder {
        private TextView textView;
        GridView gridView;
        public RightHolder(View view){

            textView = (TextView) view.findViewById(R.id.right_lvTitleTv);
            gridView = (GridView) view.findViewById(R.id.right_listView_gv);
        }
    }
}
