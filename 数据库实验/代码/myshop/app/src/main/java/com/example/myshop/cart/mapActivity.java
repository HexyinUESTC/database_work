package com.example.myshop.cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.PlanNode;
import com.example.myshop.R;
import com.example.myshop.bean.LogisticsInfoBean;
import com.example.myshop.utils.TitleLayout;

import java.util.ArrayList;
import java.util.List;

public class mapActivity extends AppCompatActivity {

    public LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    private TitleLayout wuliu_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());//创建一个LocationClient的实例，
        // LocationClient的构建函数接收一个Context参数，这里调用getApplicationContext(),方法来获取一个全局的Context参数并传入。
        // 然后调用LocationClient的registerLocationListener（）方法来注册一个定位监听器，当获取到位置信息的时候，就会回调这个定位监听器。

        setContentView(R.layout.activity_map);
        wuliu_title = (TitleLayout) findViewById(R.id.wuliu_title);
        wuliu_title.getTextView_forward().setText("");
        wuliu_title.setTitle("物流情况");
        mapView= (MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        positionText= (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(mapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(mapActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(mapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[]permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(mapActivity.this,permissions,1);
        }else{
            requestLocation();
        }

        RecyclerView rv_logistics = findViewById(R.id.logistics_rlv);
        rv_logistics.setLayoutManager(new LinearLayoutManager(this));
        rv_logistics.setFocusable(false);
        //解决ScrollView嵌套RecyclerView出现的系列问题
        rv_logistics.setNestedScrollingEnabled(false);
        rv_logistics.setHasFixedSize(true);
        rv_logistics.setAdapter(new LogisticsInfoAdapter(this, R.layout.item_logistics, getData()));

    }//ACCESS_FINE_LOCATION、ACCESS_COARSE_LOCATION、READ_PHONE_STATE、WRITE_EXTERNAL_STORAGE这4个权限是危险权限
    // 需要进行运行时权限处理，不过ACCESS_FINE_LOCATION、ACCESS_COARSE_LOCATION属于同一个权限组，两者申请其一就可以了。
    // 这里运用了一比较新的用法在运行时一次性申请3个权限。首先创建了一个List集合，然后依次判断这3个权限有没有被授权，
    // 如果没有被授权，就添加到List集合中，最后将List转换成数组，在调用ActivityCompat.requestPermissions()方法一次性申请。
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());//LatLng类用于存放经纬度
            // 第一个参数是纬度值，第二个参数是精度值。这里输入的是本地位置。
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);//将LatLng对象传入
            baiduMap.animateMapStatus(update);
            update= MapStatusUpdateFactory.zoomTo(16f);//百度地图缩放范围，限定在3-19之间，
            // 可以去小数点位值,值越大，地图显示的信息越精细
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;//防止多次调用animateMapStatus()方法，以为将地图移动到我们当前位置只需
            // 在程序第一次定位的时候调用一次就可以了。
        }
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);//获取我们的当地位置
    }

    private List<LogisticsInfoBean> getData() {
        List<LogisticsInfoBean> data = new ArrayList<>();
        data.add(new LogisticsInfoBean("2021-05-05 13:37:57", "签收人: xyy 已签收 感谢使用申通速递，期待再次为您服务"));
        data.add(new LogisticsInfoBean("2021-05-05 09:03:42", "【黄桷垭】 派件人: xyy 派件中 派件员电话110"));
        data.add(new LogisticsInfoBean("2021-05-05 08:27:10", "【黄桷垭】 已收入"));
        data.add(new LogisticsInfoBean("2021-05-05 04:38:32", "【黄桷垭转运中心】 已收入"));
        data.add(new LogisticsInfoBean("2021-05-04 01:27:49", "【南岸区转运中心】 已发出 下一站 【黄桷垭转运中心】"));
        data.add(new LogisticsInfoBean("2021-05-04 01:17:19", "【南岸区转运中心】 已收入"));
        return data;
    }

    private void initLocation() {
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);//表示每5秒更新一下当前位置
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        // Hight_Accuracy表示高精确度模式，会在GPS信号正常的情况下优先使用GPS定位，在无法接收GPS信号的时候使用网络定位。
        // Battery_Saving表示节电模式，只会使用网络进行定位。
        // Device_Sensors表示传感器模式，只会使用GPS进行定位。
        mLocationClient.setLocOption(option);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//销毁之前，用stop()来停止定位
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }//onRequestPermissionsResult()方法中，对权限申请结果进行逻辑判断。这里使用一个循环对每个权限进行判断，
        // 如果有任意一个权限被拒绝了，那么就会直接调用finish()方法关闭程序，只有当所有的权限被用户同意了，才会
        // 调用requestPermissions()方法开始地理位置定位。
    }
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if(location.getLocType()==BDLocation.TypeGpsLocation||location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(location);
            }
        }
    }
}
