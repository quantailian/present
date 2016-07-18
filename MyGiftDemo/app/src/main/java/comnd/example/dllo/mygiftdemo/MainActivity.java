package comnd.example.dllo.mygiftdemo;





import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import comnd.example.dllo.mygiftdemo.ui.activity.AbsBaseActivity;
import comnd.example.dllo.mygiftdemo.ui.fragment.GuideFragment;
import comnd.example.dllo.mygiftdemo.ui.fragment.HomeFragment;
import comnd.example.dllo.mygiftdemo.ui.fragment.HotFragment;
import comnd.example.dllo.mygiftdemo.ui.fragment.SortFragment;

public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {

private RadioGroup radioGroup;
    private GuideFragment guideFragment;
    private HotFragment hotFragment;
    private SortFragment sortFragment;
    private HomeFragment homeFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        // 获取radiogroup
        radioGroup = byView(R.id.my_main_radioGroup);

    }

    @Override
    protected void initDatas() {
        guideFragment = new GuideFragment();
        hotFragment = new HotFragment();
        sortFragment = new SortFragment();
        homeFragment = new HomeFragment();
        radioGroup.setOnCheckedChangeListener(this);
        // 将第一个作为默认的启动项
        radioGroup.check(R.id.my_main_guideRadio_Btn);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId){
            case R.id.my_main_guideRadio_Btn:
                fragmentTransaction.replace(R.id.my_main_lineralayout,guideFragment);
                break;
            case R.id.my_main_hotRadio_Btn:
                fragmentTransaction.replace(R.id.my_main_lineralayout,hotFragment);
                break;
            case R.id.my_main_sortRadio_Btn:
                fragmentTransaction.replace(R.id.my_main_lineralayout,sortFragment);
                break;
            case R.id.my_main_homeRadio_Btn:
                fragmentTransaction.replace(R.id.my_main_lineralayout,homeFragment);
                break;

            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
