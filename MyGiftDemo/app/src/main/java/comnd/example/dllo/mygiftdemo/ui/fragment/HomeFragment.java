package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.db.DaoInstance;
import comnd.example.dllo.mygiftdemo.db.PresentBean;
import comnd.example.dllo.mygiftdemo.ui.activity.HeadPhotoActivity;
import comnd.example.dllo.mygiftdemo.ui.activity.SetUpActivity;
import comnd.example.dllo.mygiftdemo.ui.adapter.HomeFragmentAdapter;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by dllo on 16/7/11.
 * 我的 - 个人中心的 fragment
 */
public class HomeFragment extends AbsBaseFragment implements View.OnClickListener {

    private ListView homelv;
    private List<PresentBean> data;
    private HomeFragmentAdapter adapter;
    private TextView usernameTV;
    private ImageButton imageButton_setting;
    private CircleImageView home_imageview;
    private SharedPreferences sp;
    private String userName;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        homelv = byView(R.id.home_lv);
        usernameTV = byView(R.id.home_username);
        imageButton_setting = byView(R.id.imageButton_setting);
        //头像
        home_imageview = byView(R.id.home_imageview);



    }

    @Override
    protected void initDatas() {
         // 注册订阅者 eventbus 注册操作
       EventBus.getDefault().register(this);
        imageButton_setting.setOnClickListener(this);
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        userName = sp.getString("username","未登陆");

        usernameTV.setText(userName);
        if (!userName.equals("未登陆")){
           data = DaoInstance.getsInstance().queryBy(userName);
            Log.d("HomeFragment", userName);
            Log.d("HomeFragment", "data.size():" + data.size());
            home_imageview.setOnClickListener(this);
        }

        adapter = new HomeFragmentAdapter(context);
        adapter.setBean(data);
        homelv.setAdapter(adapter);
        homelv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        homelv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // 得到行布局的位置
                PresentBean be= (PresentBean) parent.getItemAtPosition(position);
                DaoInstance.getsInstance().deleteBy(be);

                adapter.removeData(position);

                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 设置
            case R.id.imageButton_setting:
                go2(context,SetUpActivity.class);
                break;

            case R.id.home_imageview:
                go2(context, HeadPhotoActivity.class);
                break;
        }
   }

    @Override
    public void onResume() {
        super.onResume();
        String paths = sp.getString("path"+userName,"未登录");
        if (!paths.equals("未登录")){
            home_imageview.setImageBitmap(BitmapFactory.decodeFile(paths));
        }
        // 得到QQ登录的头像
        String imageUrl = sp.getString("path1" + userName, "666");
        if (!imageUrl.equals("666")) {
            Picasso.with(context).load(imageUrl).into(home_imageview);
        }

    }

    @Subscribe
    public void onReceive(String path){
        DaoInstance.getsInstance().queryBy(path);
        String paths = sp.getString("path"+userName,"未登录");

        if (!paths.equals("未登录")){
            home_imageview.setImageBitmap(BitmapFactory.decodeFile(paths));
        }
        else {
            home_imageview.setImageBitmap(BitmapFactory.decodeFile(path));

        }

    }

    //销毁事件公交车
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }






}
