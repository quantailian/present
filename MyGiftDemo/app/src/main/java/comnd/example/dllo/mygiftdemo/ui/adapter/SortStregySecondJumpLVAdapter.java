package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.SecondJumpLVBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;


/**
 * Created by dllo on 16/7/20.
 * * 品类 风格 对象点进去的二级界面的适配器
 */
public class SortStregySecondJumpLVAdapter extends BaseAdapter {
    private SecondJumpLVBean secondJumpLVBean;
    private Context context;

    public SortStregySecondJumpLVAdapter(Context context) {
        this.context = context;
    }

    public void setSecondJumpLVBean(SecondJumpLVBean secondJumpLVBean) {
        this.secondJumpLVBean = secondJumpLVBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return secondJumpLVBean.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return secondJumpLVBean.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyJumpLvViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_jump_second_sortstregy_item, parent, false);
            holder = new MyJumpLvViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyJumpLvViewHolder) convertView.getTag();
        }


        SingleLoadingImageView.loadImageView(secondJumpLVBean.getData().getItems().get(position).getCover_image_url(), holder.contentImageView, context);
        holder.contentTv.setText(secondJumpLVBean.getData().getItems().get(position).getTitle());
        holder.likesCount.setText(String.valueOf(secondJumpLVBean.getData().getItems().get(position).getLikes_count()));
        return convertView;
    }

    class MyJumpLvViewHolder {


        private ImageView contentImageView;
        private TextView contentTv;
        private TextView likesCount;


        public MyJumpLvViewHolder(View view) {


            contentImageView = (ImageView) view.findViewById(R.id.classifyActivity_lvItem_image);
            contentTv = (TextView) view.findViewById(R.id.classifyActivity_lvItem_title);
            likesCount = (TextView) view.findViewById(R.id.classifyActivity_lvItem_likesCount);
        }

    }


}
