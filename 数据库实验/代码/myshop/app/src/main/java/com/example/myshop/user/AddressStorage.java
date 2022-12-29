package com.example.myshop.user;


import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.myshop.base.myApplication;
import com.example.myshop.bean.UserAddress;
import com.example.myshop.bean.productBean;
import com.example.myshop.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class AddressStorage {

    public static final String JSON_CART = "json_cart";
    private  static com.example.myshop.user.AddressStorage instance;
    private final Context mContext;
    //SparseArray的性能优于HashMap
    private SparseArray<UserAddress> sparseArray;

    private AddressStorage(Context context){
        this.mContext = context;
        //把之前存储的数据读取
        sparseArray = new SparseArray<>(100);

        listToSparseArray();
    }

    /**
     * 从本地读取的数据加入到SparseArray中
     */
    private void listToSparseArray() {
        List<UserAddress> userAddressList = getAllData();
        //把List数据转换成SparseArray
        for (int i= 0; i<userAddressList.size();i++){
            UserAddress goodsBean = userAddressList.get(i);
            sparseArray.put(goodsBean.getId(),goodsBean);
        }

    }

    /**
     * 获取本地所有的数据
     * @return
     */
    public List<UserAddress> getAllData() {
        List<UserAddress> userAddressList  = new ArrayList<>();
        //1.从本地获取
        String json = CacheUtils.getString(mContext, JSON_CART);
        //2.使用Gson转换成列表
        //判断不为空的时候执行
        if(!TextUtils.isEmpty(json)){
            //把String转换成List
            userAddressList = new Gson().fromJson(json,new TypeToken<List<UserAddress>>(){}.getType());
        }
        return userAddressList;
    }


    /**
     * 得到购车实例
     * @return
     */
    public static com.example.myshop.user.AddressStorage getInstance(){
        if(instance == null){
            instance = new com.example.myshop.user.AddressStorage(myApplication.getAppContext());
        }
        return instance;
    }

    /**
     * 添加数据
     * @param goodsBean
     */
    public void addData(UserAddress goodsBean){

        //1.添加到内存中SparseArray
        //如果当前数据已经存在，就修改成number递增
        UserAddress tempData = sparseArray.get(goodsBean.getId());
        if(tempData != null){
            //内存中有了这条数据
//            tempData.setNumber(tempData.getNumber()+1);
        }else{
            tempData = goodsBean;
//            tempData.setNumber(1);
        }

        //同步到内存中
        sparseArray.put(tempData.getId(),tempData);


        //2.同步到本地
        saveLocal();

    }

    /**
     * 删除数据
     * @param goodsBean
     */
    public void deleteData(UserAddress goodsBean){
        //1.内存中删除
        sparseArray.delete(Integer.parseInt(String.valueOf(goodsBean.getId())));

        //2.把内存的保持到本地
        saveLocal();
    }


    /**
     * 更新数据
     * @param goodsBean
     */
    public void updateData(UserAddress goodsBean){
        //1.内存中更新
        sparseArray.put(goodsBean.getId(),goodsBean);

        //2.同步到本地
        saveLocal();
    }

    /**
     * 保持数据到本地
     */
    private void saveLocal() {
        //1.SparseArray转换成List
        List<UserAddress> goodsBeanList = sparseToList();
        //2.使用Gson把列表转换成String类型
        String json = new Gson().toJson(goodsBeanList);
        //3.把String数据保存
        CacheUtils.saveString(mContext,JSON_CART,json);

    }

    private List<UserAddress> sparseToList() {
        List<UserAddress> goodsBeanList = new ArrayList<>();
        for (int i=0;i<sparseArray.size();i++){
            UserAddress goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }


}

