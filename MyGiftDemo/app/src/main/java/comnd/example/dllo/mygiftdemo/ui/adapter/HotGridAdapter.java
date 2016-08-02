package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.HotBean;

import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;


/**
 * Created by dllo on 16/7/14.
 * 热门页面 用的网格的适配器
 */
public class HotGridAdapter extends BaseAdapter{

    private HotBean beans;
    private Context context;

    public HotGridAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(HotBean beans) {
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
        HotGridHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item,parent,false);
            holder = new HotGridHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (HotGridHolder) convertView.getTag();
        }


        SingleLoadingImageView.loadImageView(beans.getData().getItems().get(position).getData().getCover_image_url(),holder.imageContent,context);
        holder.tvContent.setText(beans.getData().getItems().get(position).getData().getName());
        holder.tvPrice.setText(beans.getData().getItems().get(position).getData().getPrice());
        holder.likeCount.setText(String.valueOf(beans.getData().getItems().get(position).getData().getFavorites_count()));
        return convertView;
    }


    class HotGridHolder{
        private ImageView imageContent;
        private TextView tvContent;
        private TextView tvPrice;
        private TextView likeCount;

        public HotGridHolder(View view){
            imageContent = (ImageView) view.findViewById(R.id.hotFmGv_coverImgIv);
            tvContent = (TextView) view.findViewById(R.id.hotFmGv_nameTv);
            tvPrice = (TextView) view.findViewById(R.id.hotFmGv_priceTv);
            likeCount = (TextView) view.findViewById(R.id.hotFmGv_likesCountTv);
        }
    }
}
