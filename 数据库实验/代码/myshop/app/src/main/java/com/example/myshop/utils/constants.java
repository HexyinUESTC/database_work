package com.example.myshop.utils;

public class constants {
    /**
     * 图片url
     */
    public static String BASE_IMAGE_URL = "http://192.168.137.1:8100/image";
    /**
     * 基础url
     */
    public static String BASE_URL = "http://192.168.137.1:8100/generator";
    /**
     * 首页数据
     */
    public static String HOME_DATA = BASE_URL + "/allproduct/getHomeData";
    /**、
     * 登录
     */
    public static String LOGIN = BASE_URL+"/user/login";
    /**
     * 注册
     */
    public static String REGISTER = BASE_URL+"/user/register";
    /**
     * 编辑用户资料，修改用户信息
     */
    public static String EDIT_USER = BASE_URL+"/user/edit";
    /**
     * 通过id获取用户资料
     */
    public static String GET_USER_BY_ID = BASE_URL+"/user/getUserById";
    /**
     * 上传文件
     */
    public static String USER_UPLOAD_IMAGE = BASE_URL+"/user/upload";
    /**
     * 获取用户所有的地址
     */
    public static String ALL_ADDRESS = BASE_URL+"/userAddress/getAllAddress";
    /**
     * 增加地址信息
     */
    public static String ADD_ADDRESS = BASE_URL+"/userAddress/addAddress";
    /**
     * 更新地址信息
     */
    public static String UPDATE_ADDRESS = BASE_URL+"/userAddress/updateAddress";
    /**
     * 删除地址信息
     */
    public static String DELETE_ADDRESS = BASE_URL+"/userAddress/deleteAddress";
    /**
     * 获取所有的购物车商品
     */
    public static String GET_ALL_CART = BASE_URL+"/cart/getAllCart";
    /**
     * 将某件商品添加到购物车中
     */
    public static String ADD_CART = BASE_URL+"/cart/addCart";
    /**
     * 更新购物车商品的信息，一般是数量
     */
    public static String UPDATE_CART  = BASE_URL+"/cart/updateCart";
    /**
     * 删除购物车中的某件商品
     */
    public static String DELETE_CART  = BASE_URL+"/cart/deleteCart";
    /**
     * 获取社区中的热榜
     */
    public static String GET_ALL_HOT = BASE_URL+"/hot_talk/getAllHot";
    /**
     * 社区中热榜点赞
     */
    public static String LIKE_HOT = BASE_URL+"/hot_talk/like_Talk";
    /**
     * 获取社区中的最新榜
     */
    public static String GET_ALL_NEW = BASE_URL+"/new_talk/getAllNew";
    /**
     * 社区中最新榜点赞
     */
    public static String LIKE_NEW = BASE_URL+"/new_talk/like_Talk";

    /**
     * 添加动态
     */
    public static String INSERT_TALK = BASE_URL + "/new_talk/insert_Talk";
    /**
     * 获取该商品的所有评论
     */
    public static String GET_ALL_COMMENT = BASE_URL+"/comment/getAllComment";
    /**
     * 在某件商品下添加评论
     */
    public static String ADD_COMMENT = BASE_URL+"/comment/addComment";
    /**
     * 在某件商品下删除评论
     */
    public static String DELETE_COMMENT = BASE_URL+"/comment/deleteComment";
    /**
     * 在某个评论下回复
     */
    public static String ADD_REPLY = BASE_URL+"/reply/addReply";
    /**
     * 在某个评论下删除回复
     */
    public static String DELETE_REPLY = BASE_URL+"/reply/deleteReply";
}
