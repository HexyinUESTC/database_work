package com.example.myshop.bean;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
public class User implements Serializable{
  /**
   {
   "id":1,
   "userName":"tsa",
   "password":"123",
   "gender":0,
   "email":"",
   "mobile":"",
   "fileName":"",
   "createTime":"2016-08-15 17:00:00",
   "updateTime":"2016-08-15 17:00:00"
   }
   */
    private Integer id;
    private String userName;
    private String password;
    private Integer gender;
    private String email;
    private String mobile;
    private String fileName;
   private String createTime;
   private String updateTime;

  public User() {
  }

  public User(Integer id, String userName, String password, Integer gender, String email, String mobile, String fileName, String createTime, String updateTime) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.gender = gender;
    this.email = email;
    this.mobile = mobile;
    this.fileName = fileName;
    this.createTime = createTime;
    this.updateTime = updateTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", gender=" + gender +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", fileName='" + fileName + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
  }
}
