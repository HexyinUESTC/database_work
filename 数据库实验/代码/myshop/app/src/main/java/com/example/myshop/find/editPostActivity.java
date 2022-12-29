package com.example.myshop.find;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.NewPostBean;
import com.example.myshop.bean.User;
import com.example.myshop.user.person;
import com.example.myshop.utils.TitleLayout;
import com.example.myshop.utils.constants;
import com.example.myshop.utils.photosUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.provider.MediaStore.EXTRA_OUTPUT;

public class editPostActivity extends AppCompatActivity {
    private TitleLayout post_title;
    private EditText post_thing;
    private ImageView post_thing_image;
    private EditText post_thing_comment;
    private PopupWindow popupWindow;
    private Uri imageUri;  //拍照功能的地址
    private static final int TAKE_PHOTO = 1;
    private static final int FROM_ALBUMS = 2;
    private String imagePath;  //从相册中选的地址
    private com.example.myshop.utils.photosUtils photosUtils = new photosUtils();
    private Bitmap bitmap;
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String localDateTime = df.format(date);
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String LocalDateTime2 = df2.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        post_title = (TitleLayout) findViewById(R.id.ad_title);
        post_thing = (EditText) findViewById(R.id.post_thing);
        post_thing_image = (ImageView) findViewById(R.id.post_thing_image);
        post_thing_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_popup_windows();
            }
        });
        post_thing_comment = (EditText) findViewById(R.id.post_thing_comment);
        post_title.setTitle("发布动态");
        post_title.getIv_backward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("post", new NewPostBean());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        post_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPostBean newPostBean = new NewPostBean(
                        1,
                        myApplication.getId(),
                        myApplication.getAccount(),
                        "a",
                        12,
                        "/a"+String.valueOf(myApplication.getId())+"@"+localDateTime+".jpg",
                        0,
                        0,
                        0,
                        0,
                        0,
                        post_thing.getText().toString(),
                        LocalDateTime2,
                        LocalDateTime2,
                        1);
                edit(newPostBean);
                Intent intent = new Intent();
                intent.putExtra("post", newPostBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void edit(NewPostBean newPostBean) {
//        studentBean  student = new studentBean(1, account, password);
//            User user = new User(1, account, password, 0, "", "", "", , LocalDateTime.now());
        Gson gson = new Gson();
        String json = gson.toJson(newPostBean);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.INSERT_TALK)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("他妈的", e.getMessage());
                editPostActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(editPostActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("登录", response.body().string());
                editPostActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(editPostActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        switch (requestCode){
            //拍照得到图片
            case TAKE_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        //将拍摄的图片展示并更新数据库
                        bitmap = BitmapFactory.decodeStream((getContentResolver().openInputStream(imageUri)));
                        post_thing_image.setImageBitmap(bitmap);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String name1 = "a"+String.valueOf(myApplication.getId())+"@"+localDateTime+".jpg";
                                    String path = imageUri.getPath();
                                    uploadImage(path, name1);
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        //                        myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
//                        loginUser.setPortrait(photoUtils.bitmap2byte(bitmap));
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            //从相册中选择图片
            case FROM_ALBUMS:
                if(resultCode == RESULT_OK){
                    //判断手机版本号
                    if(Build.VERSION.SDK_INT >= 19){
                        imagePath =  photosUtils.handleImageOnKitKat(this, data);
                    }else {
                        imagePath = photosUtils.handleImageBeforeKitKat(this, data);
                    }
                }
                if(imagePath != null){
                    //将拍摄的图片展示并更新数据库
                    bitmap = BitmapFactory.decodeFile(imagePath);
                    post_thing_image.setImageBitmap(bitmap);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String name2 = "a"+String.valueOf(myApplication.getId())+"@"+localDateTime+".jpg";
                            try {
                                uploadImage(imagePath, name2);
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

//                    myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
//                    loginUser.setPortrait(photosUtils.bitmap2byte(bitmap));
                }else{
                    Log.d("food","没有找到图片");
                }
                break;
            default:
                break;
        }
    }
    //展示修改头像的选择框，并设置选择框的监听器
    private void show_popup_windows(){
        RelativeLayout layout_photo_selected = (RelativeLayout) getLayoutInflater().inflate(R.layout.photo_select,null);
        if(popupWindow==null){
            popupWindow = new PopupWindow(layout_photo_selected, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        }
        //显示popupwindows
        popupWindow.showAtLocation(layout_photo_selected, Gravity.CENTER, 0, 0);
        //设置监听器
        TextView take_photo =  (TextView) layout_photo_selected.findViewById(R.id.take_photo);
        TextView from_albums = (TextView)  layout_photo_selected.findViewById(R.id.from_albums);
        LinearLayout cancel = (LinearLayout) layout_photo_selected.findViewById(R.id.cancel);
        //拍照按钮监听
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow != null && popupWindow.isShowing()) {
                    imageUri = photosUtils.take_photo_util(editPostActivity.this, "com.example.myshop.fileprovider", "output_image.jpg");
                    //调用相机，拍摄结果会存到imageUri也就是outputImage中
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                    //去除选择框
                    popupWindow.dismiss();
                }
            }
        });
        //相册按钮监听
        from_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //申请权限
                if(ContextCompat.checkSelfPermission(editPostActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(editPostActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    //打开相册
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, FROM_ALBUMS);
                }
                //去除选择框
                popupWindow.dismiss();
            }
        });
        //取消按钮监听
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private boolean uploadImage(String path, String name) throws ExecutionException, InterruptedException, IOException {

        File file = new File(path);
        if(path.isEmpty() || !file.exists()) {
            Log.e("file","文件是空的" + String.valueOf(path.isEmpty()) + String.valueOf(file.exists()) + path);
            return false;
        }
        Log.e("file", path);
        final RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",name, RequestBody.create(new File(path), MediaType.parse("multipart/form-data")))
                .addFormDataPart("name", name)
                .build();
        ResponseBody responseBody = myApplication.getOkHttpClient().newCall(new Request.Builder().post(body).url(constants.USER_UPLOAD_IMAGE).build()).execute().body();
        Log.e("persoaaaaaaaaaaaaaaaaa", responseBody.string());
        return true;
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
