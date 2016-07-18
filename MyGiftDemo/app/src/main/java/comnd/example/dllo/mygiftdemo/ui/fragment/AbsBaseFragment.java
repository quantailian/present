package comnd.example.dllo.mygiftdemo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comnd.example.dllo.mygiftdemo.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/7/11.
 * 封装的 fragment基类
 */
public abstract class AbsBaseFragment extends Fragment{

    protected Context context;

    /**
     * 当activity和fragment关联时会被调用
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    protected  abstract int setLayout();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    /**
     * 绑定ID
     * @param resId
     * @param <T>
     * @return
     */
   protected <T extends View>T byView(int resId){
       return (T) getView().findViewById(resId);
   }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();
    }

    protected abstract void initDatas();

    /**
     * 跳转传值
     */
    protected void go2(Context from, Class<? extends AbsBaseActivity>to){
        Intent intent = new Intent(from,to);
        context.startActivity(intent);
    }
}
