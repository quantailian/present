package comnd.example.dllo.mygiftdemo;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import comnd.example.dllo.mygiftdemo.ui.activity.AbsBaseActivity;
import comnd.example.dllo.mygiftdemo.ui.activity.SignActivity;
import comnd.example.dllo.mygiftdemo.ui.fragment.GuideFragment;

/**
 * Created by dllo on 16/7/13.
 * 登录页面
 */
public class LoginActivity extends AbsBaseActivity implements View.OnClickListener {
    // 用户名 密码登录框
    private EditText userNameEd, passWordEd;
    // 注册新用户 使用密码登录
    private TextView rMessage, rPassword;
    // 登录 注册
    private Button loginBtn, zhuCeBtn;
    // 关闭图片
    private ImageButton imagebtn_X;

    private String userName, passWord;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    // QQ
    private ImageButton qqBtn;

    private PlatformActionListener plListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            String name = platform.getDb().getUserName();
            String imageurl = platform.getDb().getUserIcon();
            Log.d("LoginActivity", name);
            Log.d("LoginActivity", imageurl);


            if (!name.isEmpty() && !imageurl.isEmpty()) {


                    editor.putString("username", name);
                    editor.putString("path1" + name, imageurl);
                    editor.commit();


            }


        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        imagebtn_X = byView(R.id.btn_loginClose);
        userNameEd = byView(R.id.userNameEd);
        passWordEd = byView(R.id.passWordEd);
        rMessage = byView(R.id.registeredMessage);
        rPassword = byView(R.id.registeredPassword);
        loginBtn = byView(R.id.btn_login);
        zhuCeBtn = byView(R.id.btn_ZhuCe);
        qqBtn = byView(R.id.QqBtn);

    }


    @Override
    protected void initDatas() {
        imagebtn_X.setOnClickListener(this);
        userNameEd.setOnClickListener(this);
        passWordEd.setOnClickListener(this);
        rMessage.setOnClickListener(this);
        rPassword.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        zhuCeBtn.setOnClickListener(this);
        qqBtn.setOnClickListener(this);

        //
        ShareSDK.initSDK(this, "15432c49b4550");

        if (sp == null){
        sp = getSharedPreferences("user", MODE_PRIVATE);
        editor = sp.edit();}

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 关闭X
            case R.id.btn_loginClose:
                goTo(this, MainActivity.class);
                break;
            // 用户名
            case R.id.userNameEd:

                break;
            // 密码
            case R.id.passWordEd:

                break;
            // 当点击注册新用户的时候
            // 注册新用户消失 使用密码登录出现.
            // 登录消失 注册出现
            case R.id.registeredMessage:
                rMessage.setVisibility(View.GONE);
                rPassword.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.GONE);
                zhuCeBtn.setVisibility(View.VISIBLE);

                break;
            // 当点击登录的时候
            case R.id.btn_login:
                // 从键盘获得用户名和密码
                userName = userNameEd.getText().toString();
                passWord = passWordEd.getText().toString();
                // 如果用户名或者密码为空的时候 提示不能登录
                if (userName.isEmpty() || passWord.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不对 不能登录", Toast.LENGTH_SHORT).show();
                } else {

                       // 如果输入的用户名和密码和注册的时候一样
                    if (userName.equals(sp.getString(userName, "")) && passWord.equals(sp.getString(userName+ "password", ""))) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        // 在存入一个
                        editor.putString("username", userName);// 记录现在登录的是谁
                        editor.putBoolean("islogin", true); // 记录是否登录
                        editor.commit();
                        goTo(this,SignActivity.class);

                        finish();

                    } else {
                        Toast.makeText(this, "账号或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.registeredPassword:// 密码注册
                rMessage.setVisibility(View.VISIBLE);
                rPassword.setVisibility(View.GONE);
                loginBtn.setVisibility(View.VISIBLE);
                zhuCeBtn.setVisibility(View.GONE);

                break;
            // 当点击注册的时候
            case R.id.btn_ZhuCe:
                // 从键盘获得用户名和密码
                userName = userNameEd.getText().toString();
                passWord = passWordEd.getText().toString();
                // 如果输入的用户名和密码不为空
                if (!userName.isEmpty() && !passWord.isEmpty()) {
                    // 如果输入的用户和我们之前在 sp 中保存的不一样,
                    // 如果我们从sp中得到内容不为空.
                    if (!userName.equals(sp.getString(userName,"")) && !sp.getString(userName,"").equals(null)) {
                    editor.putString(userName,userName);
                    editor.putString(userName+"password",passWord);
                    editor.commit();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "该账号已存在", Toast.LENGTH_SHORT).show();
                }

                }

                else {
                    Toast.makeText(LoginActivity.this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                }
                break;

            // qq登录
            case R.id.QqBtn:

                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(plListener);
                qq.SSOSetting(true);
                qq.authorize();
                finish();
                break;

        }
    }
}


