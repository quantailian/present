package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.PlFgDxBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

/**
 * Created by dllo on 16/7/16.
 * 这个是攻略页面的复用的gridview的适配器
 * 三个gridview共用一个适配器
 */
public class SortPlFgDxAdapter extends BaseAdapter {

    private PlFgDxBean.DataBean.ChannelGroupsBean beans;
    private Context context;


    public SortPlFgDxAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(PlFgDxBean.DataBean.ChannelGroupsBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    // 设置只显示6个.
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return beans.getChannels().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SortPlHolder holder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sort_stregy_gv_item,parent,false);
            holder = new SortPlHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (SortPlHolder) convertView.getTag();
        }

        SingleLoadingImageView.loadImageView(
                beans.getChannels().get(position).getCover_image_url(),holder.imageView,context);
        return convertView;
    }



    class SortPlHolder {

        private ImageView imageView;
        public SortPlHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.my_sort_strtegv_gv_item_image);
        }
    }


}
