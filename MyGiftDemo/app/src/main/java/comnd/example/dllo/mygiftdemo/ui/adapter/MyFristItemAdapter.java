package comnd.example.dllo.mygiftdemo.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.MyFristItemBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;
import comnd.example.dllo.mygiftdemo.ui.fragment.FristFragment;


/**
 * Created by dllo on 16/7/13.
 * 指南页面 可以滑动的那几个图片的适配器
 * recycleview
 */
public class MyFristItemAdapter extends RecyclerView.Adapter<MyFristItemAdapter.MyHolder>{

    private MyFristItemBean data;
    private Context context;
    private MyRVOnClickListener myrvOnClickListener;

    public MyFristItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(MyFristItemBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setMyrvOnClickListener(MyRVOnClickListener myrvOnClickListener) {
        this.myrvOnClickListener = myrvOnClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.frist_item,null);
        holder = new MyHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        // 毕加索实现网络获取图片
//        Picasso.with(context).load(imageUrls.get(position)).into(holder.imageView);
//        VolleyInstance.loadImageView(imageUrls.get(position),holder.imageView,context);
        SingleLoadingImageView.loadImageView(data.getData().getSecondary_banners().get(position).getImage_url(),holder.imageView,context);
      // rv设置监听
       if (myrvOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    myrvOnClickListener.rvOnClickListener(pos);
                }
            });
       }

    }


    @Override
    public int getItemCount() {
        return data.getData().getSecondary_banners().size();
    }



    class MyHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.frist_item_image);
        }
    }
}
