package comnd.example.dllo.mygiftdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/8/1.
 */
public class SetUpPositionAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private Context context;

    public SetUpPositionAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        myPositionHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.position_lv_item,null);
            holder = new myPositionHolder(convertView);
            convertView.setTag(holder);
        }

        else {
            holder = (myPositionHolder) convertView.getTag();
        }
        holder.tv.setText(arrayList.get(position));
        return convertView;
    }


    class myPositionHolder {
        private TextView tv;

        public myPositionHolder(View view){
            tv = (TextView) view.findViewById(R.id.position_tv);
        }
    }
}
