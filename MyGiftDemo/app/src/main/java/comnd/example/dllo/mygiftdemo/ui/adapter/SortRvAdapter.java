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
    private List<LanMuBean.DataBean.ColumnsBean> beans ;
    private Context context;

    public SortRvAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(List<LanMuBean.DataBean.ColumnsBean> beans) {
        this.beans = beans;
    }

    @Override
    public MyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         MyRvHolder holder = null;
         View view = LayoutInflater.from(context).inflate(R.layout.sort_stregy_rv_item,parent,false);
         holder = new MyRvHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRvHolder holder, int position) {

       SingleLoadingImageView.loadImageView
               (beans.get(position).getCover_image_url(),holder.countentImage,context);

        holder.titleTv.setText(beans.get(position).getTitle());
        holder.subtitleTv.setText(beans.get(position).getSubtitle());
        holder.authorTv.setText(beans.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return beans!=null?beans.size():0;
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
