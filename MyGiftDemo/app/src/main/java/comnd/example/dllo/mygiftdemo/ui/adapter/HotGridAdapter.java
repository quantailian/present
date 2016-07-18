package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LocalhotBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;

/**
 * Created by dllo on 16/7/14.
 * 热门页面 用的网格的适配器
 */
public class HotGridAdapter extends BaseAdapter{

    private ArrayList<LocalhotBean> beans;
    private Context context;

    public HotGridAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(ArrayList<LocalhotBean> beans) {
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
        HotGridHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item,parent,false);
            holder = new HotGridHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (HotGridHolder) convertView.getTag();
        }

        // 获得
        LocalhotBean data = beans.get(position);
//        VolleyInstance.loadImageView(data.getImageUrl(),holder.imageContent,context);
        SingleLoadingImageView.loadImageView(data.getImageUrl(),holder.imageContent,context);
        holder.tvContent.setText(data.getName());
        holder.tvPrice.setText(data.getPrice());
        holder.likeCount.setText(data.getLikesCount());
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
