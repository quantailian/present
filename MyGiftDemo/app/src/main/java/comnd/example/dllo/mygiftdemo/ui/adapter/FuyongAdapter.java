package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import comnd.example.dllo.mygiftdemo.R;

import comnd.example.dllo.mygiftdemo.model.bean.HTBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/14.
 * 简单的listview
 * 复用的适配器
 */
public class FuyongAdapter extends BaseAdapter{

    private HTBean beans;
    private Context context;

    public FuyongAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(HTBean beans) {
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
        MyViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_listview_second,parent,false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        }

        else {
            holder = (MyViewHolder) convertView.getTag();
        }


        holder.TopTvOne.setText(beans.getData().getItems().get(position).getColumn().getCategory());
        holder.listviewTitle.setText(beans.getData().getItems().get(position).getColumn().getTitle());

//        Picasso.with(context).load(beans.get
//                (position).getTouxiang()).into(holder.touXiangImg);
        SingleLoadingImageView.loadImageView(beans.getData().getItems().get(position).getAuthor().getAvatar_url(),holder.touXiangImg,context);
//        VolleyInstance.loadImageView(beans.get(position).getTouxiang(),holder.touXiangImg,context);
        holder.authorname.setText(beans.getData().getItems().get(position).getAuthor().getNickname());
       // Picasso.with(context).load(beans.get(position).getCoverImage()).into(holder.contentImageView);
//        VolleyInstance.loadImageView(beans.get(position).getCoverImage(),holder.contentImageView,context);
        SingleLoadingImageView.loadImageView(beans.getData().getItems().get(position).getCover_image_url(),holder.contentImageView,context);
        holder.contentTv.setText(beans.getData().getItems().get(position).getTitle());
        holder.likesCount.setText(String.valueOf(beans.getData().getItems().get(position).getLikes_count()));
        return convertView;
    }

    class MyViewHolder{

        private TextView TopTvOne;
        private TextView listviewTitle;
        private final CircleImageView touXiangImg;
        private TextView authorname;
        private ImageView contentImageView;
        private TextView contentTv;
        private TextView likesCount;



        public MyViewHolder(View view){


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
