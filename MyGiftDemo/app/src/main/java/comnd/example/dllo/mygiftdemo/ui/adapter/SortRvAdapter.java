package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.model.bean.LanMuBean;
import comnd.example.dllo.mygiftdemo.model.net.SingleLoadingImageView;
import comnd.example.dllo.mygiftdemo.model.net.VolleyInstance;

/**
 * Created by dllo on 16/7/15.
 * 栏目的 适配器
 */
public class SortRvAdapter extends RecyclerView.Adapter<SortRvAdapter.MyRvHolder> {
   LanMuBean bean ;
    private Context context;
    private MySortRvOnClickListener mySortRvOnClickListener;

    public SortRvAdapter(Context context) {
        this.context = context;
    }

    public void setBean(LanMuBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setMySortRvOnClickListener(MySortRvOnClickListener mySortRvOnClickListener) {
        this.mySortRvOnClickListener = mySortRvOnClickListener;
    }

    @Override
    public MyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         MyRvHolder holder = null;
         View view = LayoutInflater.from(context).inflate(R.layout.sort_stregy_rv_item,parent,false);
         holder = new MyRvHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyRvHolder holder, int position) {

        if (mySortRvOnClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    mySortRvOnClickListener.sortRvOnClickListener(pos);
                }
            });
        }

       SingleLoadingImageView.loadImageView
               (bean.getData().getColumns().get(position).getCover_image_url(),holder.countentImage,context);

        holder.titleTv.setText(bean.getData().getColumns().get(position).getTitle());
        holder.subtitleTv.setText(bean.getData().getColumns().get(position).getSubtitle());
        holder.authorTv.setText(bean.getData().getColumns().get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return bean!=null?bean.getData().getColumns().size():0;
    }

    class MyRvHolder extends RecyclerView.ViewHolder {
        private ImageView countentImage;
        private TextView titleTv;
        private TextView subtitleTv;
        private TextView authorTv;

        public MyRvHolder(View itemView) {
            super(itemView);
            countentImage = (ImageView) itemView.findViewById(R.id.sort_strtegy_item_contentimage);
            titleTv = (TextView) itemView.findViewById(R.id.sort_strtegy_item_title);
            subtitleTv = (TextView) itemView.findViewById(R.id.sort_strtegy_item_subtitle);
            authorTv = (TextView) itemView.findViewById(R.id.sort_strtegy_item_author);
        }
    }
}
