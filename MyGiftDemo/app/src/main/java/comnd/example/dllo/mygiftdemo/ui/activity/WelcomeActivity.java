package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import comnd.example.dllo.mygiftdemo.MainActivity;
import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/12.
 * 欢迎页面  - 实现5s后跳转到主页面
 */
public class WelcomeActivity extends AbsBaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private CountDownTimer timer;

    @Override
    protected int setLayout() {

        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        imageView = byView(R.id.my_welcome_image);
    }

    @Override
    protected void initDatas() {
        imageView.setOnClickListener(this);

        timer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                goTo(WelcomeActivity.this,MainActivity.class);

            }
        }.start();
    }

 @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onClick(View v) {
       goTo(WelcomeActivity.this,MainActivity.class);
        finish();

    }
}
