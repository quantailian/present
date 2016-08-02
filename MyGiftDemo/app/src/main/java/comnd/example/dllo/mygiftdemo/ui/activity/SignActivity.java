package comnd.example.dllo.mygiftdemo.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import comnd.example.dllo.mygiftdemo.MainActivity;
import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/26.
 */
public class SignActivity extends AbsBaseActivity implements View.OnClickListener {

    private ImageView imagBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initViews() {
        imagBack = byView(R.id.sign_back);
    }

    @Override
    protected void initDatas() {

         imagBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        goTo(this, MainActivity.class);
    }
}
