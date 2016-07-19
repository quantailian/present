package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LUNBOJinRu;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/18.
 */
public class SecondJumpAdapter extends BaseAdapter {
    private LUNBOJinRu bean;
    private Context context;

    public SecondJumpAdapter(Context context) {
        this.context = context;
    }

    public void setBean(LUNBOJinRu bean) {
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
        MyJumpViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_second,parent,false);
            holder = new  MyJumpViewHolder(convertView);
            convertView.setTag(holder);
        }

        else {
            holder = ( MyJumpViewHolder) convertView.getTag();
        }
        holder.TopTvOne.setText
                (bean.getData().getPosts().get(position).getColumn().getCategory());
        holder.listviewTitle.setText
                (bean.getData().getPosts().get(position).getColumn().getTitle());
        SingleLoadingImageView.loadImageView
                (bean.getData().getPosts().get(position).
                        getAuthor().getAvatar_url(),holder.touXiangImg,context);
        holder.authorname.setText
                (bean.getData().getPosts().get(position).getAuthor().getNickname());
        SingleLoadingImageView.loadImageView
                (bean.getData().getPosts().get(position).
                        getCover_image_url(),holder.contentImageView,context);
        holder.contentTv.setText(bean.getData().getPosts().get(position).getTitle());
        holder.likesCount.setText(String.valueOf(bean.getData().getPosts().get(position).getLikes_count()));




        return convertView;
    }

    class MyJumpViewHolder{

        private TextView TopTvOne;
        private TextView listviewTitle;
        private final CircleImageView touXiangImg;
        private TextView authorname;
        private ImageView contentImageView;
        private TextView contentTv;
        private TextView likesCount;



        public MyJumpViewHolder(View view){


            TopTvOne = (TextView) view.findViewById(R.id.second_listview_TopTvOne);
            listviewTitle = (TextView) view.findViewById(R.id.second_listview_title);
            touXiangImg = (CircleImageView) view.findViewById(R.id.second_listview_touxiang);
            authorname = (TextView) view.findViewById(R.id.second_listview_tvname);
            contentImageView = (ImageView) view.findViewById(R.id.second_listview_contentImageView);
            contentTv = (TextView) view.findViewById(R.id.second_listview_content);
            likesCount = (TextView) view.findViewById(R.id.second_listview_likesCount);
        }

    }



}
