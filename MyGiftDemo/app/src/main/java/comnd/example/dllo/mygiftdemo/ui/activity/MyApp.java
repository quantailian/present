package comnd.example.dllo.mygiftdemo.ui.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/11.
 * 放唯一的context
 */
public class MyApp extends Application {
// Application 是当前应用,只存在一个

    private static ArrayList<AbsBaseActivity> activities;
    private static SharedPreferences sp;
    private static Context context;

    public static void addActivity(AbsBaseActivity a){
        activities.add(a);
    }

    public static void removeActivity(AbsBaseActivity a){
        activities.remove(a);
        a.finish();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<>();
        context = getApplicationContext();
        sp = context.getSharedPreferences("MyDemo",MODE_PRIVATE);

        // imageLoader 初始化
        ImageLoaderConfiguration configuration =
                ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(configuration);
    }
    // 对外提供的方法
    public static Context getContext(){

        return context;
    }

    public static SharedPreferences getSp() {
        return sp;
    }
}
