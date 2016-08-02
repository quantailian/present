package comnd.example.dllo.mygiftdemo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import comnd.example.dllo.mygiftdemo.R;

/**
 * Created by dllo on 16/7/28.
 * 评分界面
 */
public class GradeActivity extends AbsBaseActivity implements RatingBar.OnRatingBarChangeListener, View.OnClickListener {
    private RatingBar ratingBar;
    private Button grade_Btn;

    @Override
    protected int setLayout() {
        return R.layout.activity_grade;
    }

    @Override
    protected void initViews() {
        ratingBar = byView(R.id.grade_rB);
        grade_Btn = byView(R.id.grade_Btn);

    }

    @Override
    protected void initDatas() {
        ratingBar.setOnRatingBarChangeListener(this);
        grade_Btn.setOnClickListener(this);
    }






    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(this, "您的评分是:"+ String.valueOf(rating)+"分,请点击确定 谢谢~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "感谢您的评分", Toast.LENGTH_SHORT).show();
        finish();
    }
}
