package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LocalGuideFirstLvBean;
import comnd.example.dllo.mygiftdemo.model.bean.MyCustomBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;

/**
 * Created by dllo on 16/7/13.
 * 指南页面的下面滑动的listview的适配器
 */
public class CustomListViewAdapter extends BaseAdapter {

    private ArrayList<LocalGuideFirstLvBean> beans;

    private Context context;

    public CustomListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(ArrayList<LocalGuideFirstLvBean> beans) {
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
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.custom_listview_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        LocalGuideFirstLvBean data = beans.get(position);
        // 毕加索
        //Picasso.with(context).load(data.getImageCenter()).into(holder.image_center);
//        VolleyInstance.loadImageView(data.getImageCenter(),holder.image_center,context);
        SingleLoadingImageView.loadImageView(data.getImageCenter(),holder.image_center,context);
        holder.tv_title.setText(data.getTitle());
        holder.tv_zan.setText(data.getLikeCount());
        holder.tv_shortTitle.setText(data.getShort_msg());
        return convertView;


    }


    class ViewHolder{
        public ImageView image_center;
        public TextView tv_title;
        public TextView tv_zan;
        public TextView tv_shortTitle;

        public ViewHolder(View view){
            image_center = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
            tv_title = (TextView) view.findViewById(R.id.guideItemLv_title);
            tv_zan = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            tv_shortTitle = (TextView) view.findViewById(R.id.guideItemLv_TopTvOne);
        }
    }
}
