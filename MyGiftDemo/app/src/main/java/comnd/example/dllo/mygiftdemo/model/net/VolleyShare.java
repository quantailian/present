package comnd.example.dllo.mygiftdemo.model.net;

import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/22.
 * 分享的单例.
 */
public class VolleyShare {
    //定义当前静态常量类
    private static VolleyShare share;

    // 私有化的构造方法
    private VolleyShare(Context context) {
        ShareSDK.initSDK(context, "15432c49b4550");
        //share = new VolleyShare(context);
    }

    //初始化


    // static 类方法.不用
    // 对外提供获得对象的方法 直接能获得.
    public static VolleyShare getShare(Context context) {
        if (share == null) {
            synchronized (VolleyInstance.class) {
                if (share == null) {
                    share = new VolleyShare(context);
                }
            }
        }
        return share;
    }

    private void MyShare(Context context,
                       String title, String titleurl, String text,
                       String url, String comment, String appname, String siteurl) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setTitleUrl(titleurl);
        oks.setText(text);
        oks.setUrl(url);
        oks.setComment(comment);
        oks.setSite(appname);
        oks.setSiteUrl(siteurl);
        oks.show(context);
    }


    public void showShare(Context context,
                          String title, String titleurl, String text,
                          String url, String comment, String appname, String siteurl) {

        MyShare(context,title,titleurl,text,url,comment,appname,siteurl);
//
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
//        oks.setTitle("标题");
//        // titleUrl是标题的网络链接，QQ和QQ空间等使用
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(context.getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI

    }
}
