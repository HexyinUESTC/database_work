package com.example.myshop.user;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.myshop.R;
import com.example.myshop.base.BaseActivity;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.User;
import com.example.myshop.utils.ItemGroup;
import com.example.myshop.utils.PhotoUtils;
import com.example.myshop.utils.RoundImageView;
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
import java.util.ArrayList;
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
import static android.provider.MediaStore.getRecentExternalVolumeNames;

public class person extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "person";
    private ItemGroup ig_id,ig_name,ig_gender,ig_email, ig_phone;
    private LinearLayout ll_portrait;

    private ArrayList<String> optionsItems_gender = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private OptionsPickerView pvOptions;

    private RoundImageView ri_portrati;
    private Uri imageUri;  //拍照功能的地址
    private static final int TAKE_PHOTO = 1;
    private static final int FROM_ALBUMS = 2;
    private PopupWindow popupWindow;
    private String imagePath;  //从相册中选的地址
    private com.example.myshop.utils.photosUtils photosUtils = new photosUtils();
    private static final int EDIT_NAME = 3;
    private static final int EDIT_PHONE = 4;
    private static final int EDIT_EMAIL = 5;
    private TitleLayout titleLayout;
//    private Bitmap bitmap = photosUtils.byte2bitmap(myApplication.getAvator());
    private Bitmap bitmap = BitmapFactory.decodeResource(myApplication.getAppContext().getResources(), R.drawable.app_logo);
    private String name, email, phone;
    boolean iswomen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initOptionData();
        ig_id = (ItemGroup)findViewById(R.id.ig_id);
        ig_name = (ItemGroup)findViewById(R.id.ig_name);
        ig_gender = (ItemGroup)findViewById(R.id.ig_gender);
        ig_email = (ItemGroup)findViewById(R.id.ig_email);
        ig_phone = (ItemGroup) findViewById(R.id.ig_phone);
//        ig_brithday = (ItemGroup)findViewById(R.id.ig_brithday);
        ll_portrait = (LinearLayout)findViewById(R.id.ll_portrait);
        ri_portrati = (RoundImageView)findViewById(R.id.ri_portrait);
        titleLayout = (TitleLayout)findViewById(R.id.tl_title);
        ig_gender.setOnClickListener(this);
        ig_phone.setOnClickListener(this);
        ig_email.setOnClickListener(this);
        ig_name.setOnClickListener(this);
        ll_portrait.setOnClickListener(this);
        ri_portrati.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
        ig_id.getContentEdt().setText(String.valueOf(myApplication.getId()));
        ig_name.getContentEdt().setText(myApplication.getAccount());
        ig_phone.getContentEdt().setText(myApplication.getPhone());
        ig_email.getContentEdt().setText(myApplication.getEmail());
        ig_gender.getContentEdt().setText(myApplication.getGender());
//        ri_portrati.setImageBitmap(bitmap);
        titleLayout.getIv_backward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleLayout.setTitle("修改个人信息");
        //设置点击保存的逻辑
        titleLayout.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String name = ig_name.getContentEdt().getText().toString();
                String phone = ig_phone.getContentEdt().getText().toString();
                String email = ig_email.getContentEdt().getText().toString();
                String gender = ig_gender.getContentEdt().getText().toString();
                myApplication.setAccount(name);
                Log.e(TAG, name+"AAAAAAAAAAAAAAA");
                myApplication.setPhone(phone);
                myApplication.setEmail(email);
                myApplication.setGender(gender);
                int g;
                if(gender.equals("男")) g = 0;
                else if(gender.equals("女")) g = 1;
                else g = 2;
                myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
//                Date date = new Date();
//                Date localDateTime = new Date();
                Date date = new Date();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String localDateTime = df.format(date);
                User user = new User(myApplication.getId(), name, myApplication.getPassword(), g, email, phone, "//"+String.valueOf(myApplication.getId())+".jpg", localDateTime, localDateTime);
//                myApplication.setAvator();
                edit(user);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        initInfo();
    }

    private void edit(User user) {
//        studentBean  student = new studentBean(1, account, password);
//            User user = new User(1, account, password, 0, "", "", "", , LocalDateTime.now());
        Gson gson = new Gson();
        String json = gson.toJson(user);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.EDIT_USER)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("他妈的", e.getMessage());
                person.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(person.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("登录", response.body().string());
                person.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(person.this, "提交成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
//    private void initData() {
//        okHttpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .callTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .build();
//
//    }


    //从数据库中初始化数据并展示
    private void initInfo(){
//        LoginUser loginUser = LoginUser.getInstance();
        ig_id.getContentEdt().setText(String.valueOf(myApplication.getId()));  //ID是int，转string
        ig_name.getContentEdt().setText(myApplication.getAccount());
//        ri_portrati.setImageBitmap(photosUtils.byte2bitmap(myApplication.getAvator()));
        ig_email.getContentEdt().setText(myApplication.getEmail());
        ig_phone.getContentEdt().setText(myApplication.getPhone());
        ig_gender.getContentEdt().setText(myApplication.getGender());
    }

    //初始化性别、地址和生日的数据
    private void initOptionData(){
        //性别选择器数据
        optionsItems_gender.add(new String("保密"));
        optionsItems_gender.add(new String("男"));
        optionsItems_gender.add(new String("女"));

        //地址选择器数据
//        String province_data = readJsonFile("province.json");
//        String city_data = readJsonFile("city.json");

//        Gson gson = new Gson();

//        options1Items = gson.fromJson(province_data, new TypeToken<ArrayList<ProvinceBean>>(){}.getType());
//        ArrayList<CityBean> cityBean_data = gson.fromJson(city_data, new TypeToken<ArrayList<CityBean>>(){}.getType());
//        for(ProvinceBean provinceBean:options1Items){
//            ArrayList<String> temp = new ArrayList<>();
//            for (CityBean cityBean : cityBean_data){
//                if(provinceBean.getProvince().equals(cityBean.getProvince())){
//                    temp.add(cityBean.getName());
//                }
//            }
//            options2Items.add(temp);
//        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ig_gender:
                pvOptions = new OptionsPickerBuilder(person.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //选择了则显示并暂存LoginUser，退出时在保存至数据库
                        String tx = optionsItems_gender.get(options1);
                        ig_gender.getContentEdt().setText(tx);
//                        loginUser.setGender(tx);
                    }
                }).setCancelColor(Color.GRAY).build();
                pvOptions.setPicker(optionsItems_gender);
                pvOptions.show();
                break;
            case R.id.ll_portrait:
                show_popup_windows();
                break;
            case R.id.ig_email:
                edit(myApplication.getEmail(), EDIT_EMAIL);
                break;
            case R.id.ig_phone:
                edit(myApplication.getPhone(), EDIT_PHONE);
                break;
            case R.id.ig_name:
                edit(myApplication.getAccount(), EDIT_NAME);
                break;
        }
    }

    private void edit(String name, int tag) {
        Intent intent = new Intent(person.this, EditName.class);
        intent.putExtra("name", name);
        intent.putExtra("tag", tag);
        startActivityForResult(intent, tag);
    }

    //处理拍摄照片回调
    @RequiresApi(api = Build.VERSION_CODES.O)
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
                        ri_portrati.setImageBitmap(bitmap);
                        final LocalDateTime localDateTime = LocalDateTime.now();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String name1 = "//"+String.valueOf(myApplication.getId())+".jpg";
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
                        myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
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
                    ri_portrati.setImageBitmap(bitmap);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String name2 = "//"+String.valueOf(myApplication.getId())+".jpg";
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

                    myApplication.setAvator(photosUtils.bitmap2byte(bitmap));
//                    loginUser.setPortrait(photosUtils.bitmap2byte(bitmap));
                }else{
                    Log.d("food","没有找到图片");
                }
                break;
            //如果是编辑名字，则修改展示
            case EDIT_NAME:
                if(resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    ig_name.getContentEdt().setText(name);
//                    myApplication.setAccount(name);
                }
                break;
            case EDIT_EMAIL:
                if(resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    ig_email.getContentEdt().setText(name);
//                    myApplication.setEmail(name);
                }
                break;
            case EDIT_PHONE:
                if(resultCode == RESULT_OK){
                    String name = data.getStringExtra("name");
                    ig_phone.getContentEdt().setText(name);
//                    myApplication.setPhone(name);
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
                    if(ContextCompat.checkSelfPermission(person.this,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(person.this, new String[]{Manifest.permission.CAMERA},1);
                    }else {
                        imageUri = photosUtils.take_photo_util(person.this, "com.example.myshop.fileprovider", "output_image.jpg");
                        //调用相机，拍摄结果会存到imageUri也就是outputImage中
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, TAKE_PHOTO);
                        //去除选择框
                        popupWindow.dismiss();
                    }
                }
            }
        });
        //相册按钮监听
        from_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //申请权限
                if(ContextCompat.checkSelfPermission(person.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(person.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
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