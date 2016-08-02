package comnd.example.dllo.mygiftdemo.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/15.
 * 自己写的单例 解析图片
 */
public class SingleLoadingImageView {

    // 定义一个当前类的静态对象
    private static SingleLoadingImageView singleLoadingImageView;
    /**
     * 私有化的构造方法
     * @param context
     */

    private SingleLoadingImageView (Context context){

    }
    // 对外提供获得对象的方法
    public SingleLoadingImageView getSingleLoadingImageView(Context context){
        if (singleLoadingImageView == null){
            synchronized (SingleLoadingImageView.class){
                if (singleLoadingImageView == null){
                    singleLoadingImageView = new SingleLoadingImageView(context);
                }
            }
        }
        return singleLoadingImageView;
    }

    // UniversalImageLoader 解析图片
    public static DisplayImageOptions options = new
            DisplayImageOptions.Builder().
            cacheOnDisk(true).cacheInMemory(true).
            showImageForEmptyUri(R.mipmap.icon_progress_bar_refresh).
            showImageOnFail(R.mipmap.icon_progress_bar_refresh).build();

    public static void loadImageView(String url, ImageView iv, Context context){
        // imageLoader 初始化
        ImageLoaderConfiguration configuration =
                ImageLoaderConfiguration.createDefault(context);

        ImageLoader.getInstance().init(configuration);

        ImageLoader.getInstance().displayImage(url,iv,options);
    }
}
