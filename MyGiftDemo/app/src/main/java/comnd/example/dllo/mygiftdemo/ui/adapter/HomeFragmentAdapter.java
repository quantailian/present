package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.db.PresentBean;

/**
 * Created by dllo on 16/7/26.
 * 我的 - 适配器
 */
public class HomeFragmentAdapter extends BaseAdapter{
    private List<PresentBean> bean;
    private Context context;

    public HomeFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setBean(List<PresentBean> bean) {
        this.bean = bean;
     notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean!=null?bean.size():0;
    }

    public void removeData(int pos){
        bean.remove(pos);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeLvHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.home_lv_item,null);
            holder = new HomeLvHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (HomeLvHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.get(position).getImageUrl()).into(holder.imageurl);
        holder.tvtitle.setText(bean.get(position).getTitle());
        holder.tvauthor.setText(bean.get(position).getAuthor());

        return convertView;
    }

    class HomeLvHolder{
        private ImageView imageurl;
        private TextView tvtitle;
        private TextView tvauthor;

        public HomeLvHolder(View view){
            imageurl = (ImageView) view.findViewById(R.id.home_lv_item_imagecontent);
            tvtitle = (TextView) view.findViewById(R.id.home_lv_item_content);
            tvauthor = (TextView) view.findViewById(R.id.home_lv_item_author);
        }
    }
}
