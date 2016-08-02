package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;
import comnd.example.dllo.mygiftdemo.ui.activity.SortSingleJumpActivity;

/**
 * Created by dllo on 16/7/16.
 * 单品页面 右面listview 的适配器
 */
public class SortSingleRightAdapter extends BaseAdapter {
    private SingleBean beans;
    private Context context;

    public SortSingleRightAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(SingleBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans != null ? beans.getData().getCategories().size() : 0;
    }


    @Override
    public Object getItem(int position) {
        return beans.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RightHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_single_gd_item, parent, false);
            holder = new RightHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RightHolder) convertView.getTag();
        }

        // 这个是listview中嵌套的gridview ,在这里给它初始化和绑定布局 和设置了行监听传值.
        SortSingleGvAdapter sortSingleGvAdapter = new SortSingleGvAdapter(context);
        sortSingleGvAdapter.setBeans(beans.getData().getCategories().get(position).getSubcategories());
        holder.gridView.setAdapter(sortSingleGvAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Bundle bundle = new Bundle();
                bundle.putString("url",
                        "http://api.liwushuo.com/v2/item_subcategories/" +
                                String.valueOf(beans.getData().getCategories().get(position).getId())
                                + "/items?limit=20&offset=0");
                bundle.putString("title", beans.getData().getCategories().get(position).getName());
                Intent intent = new Intent(context, SortSingleJumpActivity.class);
                //********将bundle里的值放入intent
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });


        holder.textView.setText("————— " + beans.getData().getCategories().get(position).getName() + " —————");
        return convertView;
    }

    class RightHolder {
        private TextView textView;
        GridView gridView;

        public RightHolder(View view) {

            textView = (TextView) view.findViewById(R.id.right_lvTitleTv);
            gridView = (GridView) view.findViewById(R.id.right_listView_gv);
        }
    }
}
