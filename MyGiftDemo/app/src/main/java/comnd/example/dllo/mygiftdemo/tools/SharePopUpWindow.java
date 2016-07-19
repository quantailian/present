package comnd.example.dllo.mygiftdemo.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/19.
 */
public class SharePopUpWindow {


//
//    // 定义一个当前类的静态对象
//    private static SharePopUpWindow sharePopUpWindow;
//    /**
//     * 私有化的构造方法
//     * @param context
//     */
//
//    private SharePopUpWindow (Context context){
//
//    }
//    // 对外提供获得对象的方法
//    public SharePopUpWindow getSharePopUpWindow(Context context){
//        if (sharePopUpWindow == null){
//            synchronized (SharePopUpWindow.class){
//                if (sharePopUpWindow == null){
//                    sharePopUpWindow = new SharePopUpWindow(context);
//                }
//            }
//        }
//        return sharePopUpWindow;
//    }
//    public static void showPopWindow(){
//
//
//        PopupWindow popupWindow = new PopupWindow();
//        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        popupWindow.setHeight(600);
//        View view =
//        popupWindow.setBackgroundDrawable(this.getResources().getDrawable(R.mipmap.ic_launcher));
//
//
//        popupWindow.setContentView(view);
//        // 键盘是否可以获得焦点
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        // 这句话的效果是让我的popWindows变暗
//        // 出现的时候我的背景颜色是半透明
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.5f;
//        getWindow().setAttributes(lp);
//
//        // 消失后我的背景颜色变回
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1f;
//                getWindow().setAttributes(lp);
//            }
//        });
//    }
}
