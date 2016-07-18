package comnd.example.dllo.mygiftdemo.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by dllo on 16/7/16.
 * 自定义的表格布局
 * 导前面三个方法
 */
public class MyCustomGridView extends GridView {

    public MyCustomGridView(Context context) {
        super(context);
    }

    public MyCustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 重新测量 - 规定他的高度是展开的.
    // 展开listview
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    // 点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE){
            // 禁止listView滑动
            return true;

        }
        return super.dispatchTouchEvent(ev);
    }
}
