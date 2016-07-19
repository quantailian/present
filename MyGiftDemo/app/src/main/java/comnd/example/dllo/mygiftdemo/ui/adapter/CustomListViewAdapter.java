package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import comnd.example.dllo.mygiftdemo.R;

import comnd.example.dllo.mygiftdemo.model.bean.MyCustomBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;


/**
 * Created by dllo on 16/7/13.
 * 指南页面的下面滑动的listview的适配器
 */
public class CustomListViewAdapter extends BaseAdapter {

    private MyCustomBean beans;

    private Context context;

    public CustomListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(MyCustomBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans!=null?beans.getData().getItems().size():0;
    }

    @Override
    public Object getItem(int position) {
        return beans.getData().getItems().get(position);
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


        // 毕加索
        //Picasso.with(context).load(data.getImageCenter()).into(holder.image_center);
//        VolleyInstance.loadImageView(data.getImageCenter(),holder.image_center,context);
        SingleLoadingImageView.loadImageView(beans.getData().getItems().get(position).getCover_image_url(),holder.image_center,context);
        holder.tv_title.setText(beans.getData().getItems().get(position).getTitle());
        holder.tv_shortTitle.setText(beans.getData().getItems().get(position).getShort_title());
        holder.tv_zan.setText(String.valueOf(beans.getData().getItems().get(position).getLikes_count()));
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
