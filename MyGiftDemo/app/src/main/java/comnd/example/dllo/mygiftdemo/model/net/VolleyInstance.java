package comnd.example.dllo.mygiftdemo.model.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/12.
 * 封装Volley的单例类 --->提供volley框架的一些使用方法
 * 解析URL
 */

public class VolleyInstance {
    // 定义一个请求队列
    private RequestQueue queue;
    // 定义一个当前类的静态对象
    private static VolleyInstance instance;
    // 私有化构造方法
    private VolleyInstance(Context context){
        queue = Volley.newRequestQueue(context);
    }

    // 对外提供获得对象的方法
    public static VolleyInstance getInstance(Context context){
        if (instance == null){
            synchronized (VolleyInstance.class){
                if (instance == null){
                    instance = new VolleyInstance(context);
                }
            }
        }
        return instance;
    }

    // 将请求接口利用接口回调响应到调用端
    public void startRequest(String url, final VolleyResult result){
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 触发成功接口方法,将值传过去
                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.failure();
            }
        });

        queue.add(sr);
    }




}
