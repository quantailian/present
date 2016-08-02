package comnd.example.dllo.mygiftdemo.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by dllo on 16/7/13.
 * 指南页面下面那个
 * 自定义listview
 */
public class MyCustomListView extends ListView{
    public MyCustomListView(Context context) {
        super(context);
    }

    /*
    attrs
    我们要获取的属性的资源ID的一个数组，
    如同ContextProvider中请求数据库时的Projection数组，
    就是从一堆属性中我们希望查询什么属性的值
     */
    public MyCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*
　defStyleAttr：这个是当前Theme中的一个attribute，
是指向style的一个引用，
当在layout xml中和style中都没有为View指定属性时，
会从Theme中这个attribute指向的Style中查找相应的属性值，
这就是defStyle的意思，如果没有指定属性值，就用这个值，
所以是默认值，但这个attribute要在Theme中指定，
且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值
 */
    public MyCustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
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
