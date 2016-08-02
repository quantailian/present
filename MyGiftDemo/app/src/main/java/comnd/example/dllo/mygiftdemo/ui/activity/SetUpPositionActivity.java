package comnd.example.dllo.mygiftdemo.ui.activity;


import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.tools.MyApp;
import comnd.example.dllo.mygiftdemo.ui.adapter.SetUpPositionAdapter;

/**
 * Created by dllo on 16/8/1.
 * 我的身份
 */
public class SetUpPositionActivity extends AbsBaseActivity implements View.OnClickListener, DrawerLayout.DrawerListener {

    private ImageView imagBack;
    // 布局里的linearlayout
    private LinearLayout sexLayout;
    private LinearLayout roleLayout;

    // 两个抽屉的linearlayout
    private LinearLayout setup_sex_layout;
    private LinearLayout setup_role_layout;

    // 第一个抽屉里的你选择性别
    private LinearLayout manlayout;
    private LinearLayout womanlayout;
    // 第二个抽屉里的选择的角色
    //private LinearLayout

    // 我的身份页面的第一个传过来的TV
    private TextView my_position_sextv;
    // 抽屉
    private DrawerLayout drawer;
    // 男生女生
    private TextView man;
    private TextView woman;
    //    private ListView lv;
//    private SetUpPositionAdapter adapter;



    @Override
    protected int setLayout() {
        return R.layout.activity_setup_position;
    }

    @Override
    protected void initViews() {
        imagBack = byView(R.id.setup_position_back);
        sexLayout = byView(R.id.sex_layout);
        roleLayout = byView(R.id.role_layout);
        my_position_sextv = byView(R.id.my_position_sextv);

        //  lv = byView(R.id.position_lv);

        setup_sex_layout = byView(R.id.setup_sex_layout);
        setup_role_layout = byView(R.id.setup_role_layout);
        drawer = byView(R.id.drawer);

        man = byView(R.id.man);
        woman = byView(R.id.woman);

        manlayout = byView(R.id.manLayout);
        womanlayout = byView(R.id.womanLayout);

    }

    @Override
    protected void initDatas() {

        imagBack.setOnClickListener(this);
        sexLayout.setOnClickListener(this);
        roleLayout.setOnClickListener(this);

        manlayout.setOnClickListener(this);
        womanlayout.setOnClickListener(this);



//        data = new ArrayList<>();
//        adapter = new SetUpPositionAdapter(MyApp.getContext());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setup_position_back:
                goTo(this, SetUpActivity.class);
                break;


            case R.id.sex_layout:

                // 当点击性别的时候
                setup_sex_layout.setVisibility(View.VISIBLE);
                setup_role_layout.setVisibility(View.GONE);
                // 让我的抽屉从右面打开
                drawer.openDrawer(Gravity.RIGHT);
                drawer.setDrawerListener(this);
                my_position_sextv.setText(man.getText().toString());


                break;

            case R.id.role_layout:

                setup_role_layout.setVisibility(View.VISIBLE);
                setup_sex_layout.setVisibility(View.GONE);
                drawer.openDrawer(Gravity.RIGHT);

                break;
//
//            case R.id.manLayout:
//
//                my_position_sextv.setText(man.getText().toString());
//                break;
//            case R.id.womanLayout:
//
//                break;
        }

    }



    // 侧滑菜单的监听事件.

    /**
     * 抽屉滑动时
     * @param drawerView
     * @param slideOffset
     */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    /**
     * 抽屉完全打开
     * @param drawerView
     */
    @Override
    public void onDrawerOpened(View drawerView) {

        manlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 抽屉关闭时
     * @param drawerView
     */
    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
