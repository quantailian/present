package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SingleBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

/**
 * Created by dllo on 16/7/16.
 * 单品页面 自定义的表格布局的适配器
 */
public class SortSingleGvAdapter extends BaseAdapter {
    private List<SingleBean.DataBean.CategoriesBean.SubcategoriesBean> beans;
    private Context context;

    public SortSingleGvAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(List<SingleBean.DataBean.CategoriesBean.SubcategoriesBean> beans) {
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
        GvHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_single_lv_gd_item,parent,false);
            holder = new GvHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder= (GvHolder) convertView.getTag();
        }
        SingleLoadingImageView.loadImageView(beans.get(position).getIcon_url(),holder.imageView,context);
        holder.textView.setText(beans.get(position).getName());
        return convertView;

    }

    class GvHolder {
        private ImageView imageView;
        private TextView textView;

        public GvHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.singleGv_imageIv);
            textView = (TextView) view.findViewById(R.id.singleGv_titleIv);
        }
    }
}
