package comnd.example.dllo.mygiftdemo;

import android.view.View;
import android.widget.ImageView;

import comnd.example.dllo.mygiftdemo.ui.activity.AbsBaseActivity;

/**
 * Created by dllo on 16/7/13.
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected int setLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initViews() {
        imageView = byView(R.id.btn_loginClose);
    }

    @Override
    protected void initDatas() {
         imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        goTo(LoginActivity.this,MainActivity.class);
    }
}
