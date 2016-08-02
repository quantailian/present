package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import comnd.example.dllo.mygiftdemo.ui.fragment.AbsBaseFragment;
import comnd.example.dllo.mygiftdemo.ui.fragment.HomeFragment;


/**
 * Created by dllo on 16/7/11.
 * 封装的activity的基类
 */
public abstract class AbsBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏

//        this.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(setLayout());
        initViews();
        initDatas();
    }


    protected abstract int setLayout();

    protected abstract void initViews();

    protected abstract void initDatas();

    /**
     * 简化findViewById
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View>T byView(int resId){
        T t = (T) findViewById(resId);
        return t;
    }

    /**
     * 简化intent跳转
     * @param from
     * @param to
     */
    protected void goTo(Context from,Class<? extends AbsBaseActivity> to){
        Intent intent = new Intent(from,to);
        startActivity(intent);
    }


    /**
     * 隐式intent
     * @param action
     * @param uri
     */
    protected void goTo(String action,String uri){
        Intent intent = new Intent(action);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    protected  void goTo(Context from, Class<? extends AbsBaseActivity> to, Bundle values){
        Intent intent = new Intent(from,to);
        intent.putExtras(values);
        startActivity(intent);
    }

}
