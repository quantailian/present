package comnd.example.dllo.mygiftdemo.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.tools.DataCleanManager;
import comnd.example.dllo.mygiftdemo.tools.MyApp;


/**
 * Created by dllo on 16/7/28.
 * 设置界面
 */
public class SetUpActivity extends AbsBaseActivity implements View.OnClickListener {

    private ImageView imagBack;
    private TextView showDataTv;
    // 清除缓存
    private LinearLayout cleanDataLl;
    // 客服电话
    private LinearLayout CallToManager;
    // 评分
    private LinearLayout setup_grade;
    // 我是身份
    private LinearLayout positionLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initViews() {
        imagBack = byView(R.id.setup_back);
        showDataTv = byView(R.id.showDataTv);
        CallToManager = byView(R.id.setup_call);
        // 清除缓存的布局
        cleanDataLl = byView(R.id.cleanDataLl);
        //评分
        setup_grade = byView(R.id.setup_grade);
        // 我的身份
        positionLayout = byView(R.id.my_position_linearlayout);

    }

    @Override
    protected void initDatas() {
        imagBack.setOnClickListener(this);
        cleanDataLl.setOnClickListener(this);
        CallToManager.setOnClickListener(this);
        setup_grade.setOnClickListener(this);
        positionLayout.setOnClickListener(this);
        // 显示缓存
        try {
            showDataTv.setText(DataCleanManager.getTotalCacheSize(MyApp.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回
            case R.id.setup_back:
                finish();
                break;
            // 清除缓存
            case R.id.cleanDataLl:
                // 查看缓存大小
                DataCleanManager.clearAllCache(MyApp.getContext());

                try {
                    showDataTv.setText(DataCleanManager.getTotalCacheSize(MyApp.getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(SetUpActivity.this, "缓存清除成功", Toast.LENGTH_SHORT).show();
                break;
            // 给客服打电话
            case R.id.setup_call:
                showDiaLog();
                break;

            // 给我们评分
            case R.id.setup_grade:
                goTo(this, GradeActivity.class);
                break;

            // 我的身份
            case R.id.my_position_linearlayout:
                goTo(this,SetUpPositionActivity.class);
                break;
        }
    }


    public void showDiaLog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 内容
        builder.setMessage("拨打礼物说客服电话");


        // 确定
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // 隐式intent打电话功能
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:4009992053"));
                startActivity(intent);


            }
        });
        // 取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();

    }
}
