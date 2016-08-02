package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.OneJumpLVBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

/**
 * Created by dllo on 16/7/20.
 * 这个是我攻略 - 栏目点进去的二级页面
 */
public class SortStregyOneJumpLVAdapter extends BaseAdapter {
    private OneJumpLVBean bean;
    private Context context;

    public SortStregyOneJumpLVAdapter(Context context) {
        this.context = context;
    }

    public void setBean(OneJumpLVBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getData().getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyOneJumpLVHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_jump_one_sortstregy_item,parent,false);
            holder = new MyOneJumpLVHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (MyOneJumpLVHolder) convertView.getTag();
        }

        SingleLoadingImageView.loadImageView(bean.getData().getPosts().get(position).getCover_image_url(),holder.imageView,context);
        holder.title.setText(bean.getData().getPosts().get(position).getTitle());
        holder.author.setText(bean.getData().getPosts().get(position).getAuthor().getNickname());
        holder.likecount.setText(String.valueOf(bean.getData().getPosts().get(position).getLikes_count()));
        return convertView;
    }

    class MyOneJumpLVHolder {
        private ImageView imageView;
        private TextView title;
        private TextView author;
        private TextView likecount;

        public MyOneJumpLVHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.one_item_imagecontent);
            title = (TextView) view.findViewById(R.id.one_item_content);
            author = (TextView) view.findViewById(R.id.one_item_author);
            likecount = (TextView) view.findViewById(R.id.one_item_likecount);
        }
    }

}
