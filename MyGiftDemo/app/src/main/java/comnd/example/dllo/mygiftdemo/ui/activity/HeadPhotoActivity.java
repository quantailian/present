package comnd.example.dllo.mygiftdemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.provider.MediaStore;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import comnd.example.dllo.mygiftdemo.R;
import comnd.example.dllo.mygiftdemo.db.DaoInstance;
import comnd.example.dllo.mygiftdemo.db.PresentBean;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/7/28.
 * 点击头像的activity
 */
public class HeadPhotoActivity extends AbsBaseActivity implements View.OnClickListener {

    private LinearLayout changHeadPhoto;
    private CircleImageView circleImageView;
    private ImageView imagBack;
    private Bitmap bitmap;
    private String path;
    private EventBus eventBus;
    public boolean isToPhoto = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_headphoto;
    }

    @Override
    protected void initViews() {

        changHeadPhoto = byView(R.id.change_headphoto);
        circleImageView = byView(R.id.headphoto_imageview);
        imagBack = byView(R.id.head_back);
    }

    @Override
    protected void initDatas() {
        changHeadPhoto.setOnClickListener(this);
        imagBack.setOnClickListener(this);
        // 实例化
        eventBus = EventBus.getDefault();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back:

                if (isToPhoto) {

                    SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String userName = sp.getString("username", "未登陆");
                    SharedPreferences.Editor editor = sp.edit();
                    if (!userName.equals("未登录")) {
                        DaoInstance.getsInstance().insert(new PresentBean(path));

                        editor.putString("path" + userName, path);
                        //得到 让它上车
                        eventBus.post(path);
                        editor.commit();

                    }
                    finish();
                }

                else {
                    finish();
                }


                break;
            case R.id.change_headphoto:
                isToPhoto = true;
                // 跳转到图库
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 200);
                break;

        }
    }

    // 去图库得到头像
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            path = cursor.getString
                    (cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            bitmap = BitmapFactory.decodeFile(path);
            circleImageView.setImageBitmap(bitmap);
        }
    }
}
