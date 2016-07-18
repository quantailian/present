package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

/**
 * Created by dllo on 16/7/16.
 */
public class SortSingleLvAdapter extends BaseAdapter {

    private List<SingleBean.DataBean.CategoriesBean> data;
    private Context context;

    public SortSingleLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SingleBean.DataBean.CategoriesBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingleHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_single_lv_item,parent,false);
            holder = new SingleHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (SingleHolder) convertView.getTag();
        }
        holder.tv_name.setText(data.get(position).getName());
        return convertView;
    }

    class SingleHolder {
        private TextView tv_name;

        public SingleHolder(View view){
            tv_name = (TextView) view.findViewById(R.id.sort_single_tv_name);
        }
    }
}
