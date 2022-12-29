package com.uprainsun.demo.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author uprainsun
 * @since 2021-03-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

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
    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户名
     */
      private String userName;

      /**
     * 密码
     */
      private String password;

      /**
     * 性别(1:男 0：女)
     */
      private Integer gender;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 手机
     */
      private String mobile;

      /**
     * 头像
     */
      private String fileName;

  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
  @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;


}
