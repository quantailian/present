package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SearchBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

/**
 * Created by dllo on 16/7/21.
 * 搜索的适配器
 */
public class SearchAdapter extends BaseAdapter {
    private SearchBean bean;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setBean(SearchBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchGridHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_item,parent,false);
            holder = new SearchGridHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (SearchGridHolder) convertView.getTag();
        }

        SingleLoadingImageView.loadImageView(bean.getData().getItems().get(position).getCover_image_url(),holder.imageContent,context);
        holder.tvContent.setText(bean.getData().getItems().get(position).getName());
        holder.tvPrice.setText(bean.getData().getItems().get(position).getPrice());
        holder.likeCount.setText(String.valueOf(bean.getData().getItems().get(position).getLikes_count()));
        return convertView;
    }

    class SearchGridHolder{
        private ImageView imageContent;
        private TextView tvContent;
        private TextView tvPrice;
        private TextView likeCount;

        public SearchGridHolder(View view){
            imageContent = (ImageView) view.findViewById(R.id.hotFmGv_coverImgIv);
            tvContent = (TextView) view.findViewById(R.id.hotFmGv_nameTv);
            tvPrice = (TextView) view.findViewById(R.id.hotFmGv_priceTv);
            likeCount = (TextView) view.findViewById(R.id.hotFmGv_likesCountTv);
        }
    }
}
