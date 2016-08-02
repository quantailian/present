package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import comnd.example.dllo.mygiftdemo.LoginActivity;
import comnd.example.dllo.mygiftdemo.R;

import comnd.example.dllo.mygiftdemo.db.DaoInstance;
import comnd.example.dllo.mygiftdemo.db.PresentBean;
import comnd.example.dllo.mygiftdemo.model.bean.MyCustomBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.view.H;


/**
 * Created by dllo on 16/7/13.
 * 指南页面的下面滑动的listview的适配器
 * 这里面有喜欢按钮的判断和加数据库
 */
public class CustomListViewAdapter extends BaseAdapter {

    private MyCustomBean beans;

    private Context context;
    // 想让不喜欢
    private boolean isLike;
    private ViewHolder holder;
    private List<Boolean> isLikes;


    public CustomListViewAdapter(Context context) {
        this.context = context;

    }

    public void setBeans(MyCustomBean beans) {
        this.beans = beans;
        notifyDataSetChanged();
        isLikes = new ArrayList<>();
        for (int i = 0; i < beans.getData().getItems().size(); i++) {
            isLikes.add(false);
        }
    }

    @Override
    public int getCount() {
        return beans != null ? beans.getData().getItems().size() : 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
//        isLike = false;
        holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.custom_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 毕加索
        //Picasso.with(context).load(data.getImageCenter()).into(holder.image_center);
//        VolleyInstance.loadImageView(data.getImageCenter(),holder.image_center,context);
        SingleLoadingImageView.loadImageView(beans.getData().getItems().get(position).getCover_image_url(), holder.image_center, context);
        holder.tv_title.setText(beans.getData().getItems().get(position).getTitle());
        holder.tv_shortTitle.setText(beans.getData().getItems().get(position).getShort_title());
        holder.tv_zan.setText(String.valueOf(beans.getData().getItems().get(position).getLikes_count()));

        final ViewHolder finalLvHolder = holder;

        holder.likecount.setOnClickListener(new View.OnClickListener() {
            private PresentBean data;

            @Override
            public void onClick(View v) {

                SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
                String username = sp.getString("username", "0");
                ImageView iv = (ImageView) v.findViewById(R.id.like_count);
//                iv.setImageResource(R.mipmap.ic_feed_favourite_selected);
                isLike = isLikes.get(position);
                if (!username.equals("0")) {
                    if (!isLike) {
                        isLikes.set(position, true);
                        Log.d("xxx", "if----isLike:" + 1111111111);
                        iv.setImageResource(R.mipmap.ic_feed_favourite_selected);

//                        finalLvHolder.likecount.setImageResource(R.mipmap.ic_feed_favourite_selected);
                       Toast.makeText(context, "喜欢成功", Toast.LENGTH_SHORT).show();
                        finalLvHolder.tv_zan.setText(String.valueOf(
                                beans.getData().getItems().get(position).getLikes_count()+1));


                        data = new PresentBean();
                        data.setTitle(beans.getData().getItems().get(position).getTitle());
                        data.setLikesCount(String.valueOf(beans.getData().getItems().get(position).getLikes_count() + 1));
                        data.setImageUrl(beans.getData().getItems().get(position).getCover_image_url());
                        data.setUserName(username);

                        data.setNextUrl(beans.getData().getItems().get(position).getUrl());
                        DaoInstance.getsInstance().insert(data);
                    } else {
                        DaoInstance.getsInstance().deleteBy(data);
                        isLikes.set(position, false);
                        Log.d("xxx", "else---isLike:" + 22222222);
                        iv.setImageResource(R.mipmap.ic_feed_favourite_normal);


//                        finalLvHolder.likecount.setImageResource(R.mipmap.ic_feed_favourite_normal);
                        finalLvHolder.tv_zan.setText(String.valueOf
                                (beans.getData().getItems().get(position).getLikes_count()));



                        Toast.makeText(context, "取消喜欢", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            }
        });


        return convertView;


    }


    class ViewHolder {
        public ImageView image_center;
        public TextView tv_title;
        private ImageView likecount;
        public TextView tv_zan;
        public TextView tv_shortTitle;

        public ViewHolder(View view) {
            image_center = (ImageView) view.findViewById(R.id.guideItemLv_contentImageView);
            tv_title = (TextView) view.findViewById(R.id.guideItemLv_title);
            // 喜欢
            likecount = (ImageView) view.findViewById(R.id.like_count);
            tv_zan = (TextView) view.findViewById(R.id.guideItemLv_likesCount);
            tv_shortTitle = (TextView) view.findViewById(R.id.guideItemLv_TopTvOne);
        }
    }


}
