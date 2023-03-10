package com.example.myshop.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/6.
 */
public class NewPostBean implements Serializable {


    private Integer id;
    private Integer userId;
    private String userName;
    private String avator;
    private Integer productId;
    private String productImg;
    private Integer commentNum;
    private Integer likeNum;
    private Integer sendNum;
    private Integer isLike;
    private Integer isSend;
    private String description;
    private String createTime;
    private  String updateTime;
    private Integer version;

    public NewPostBean() {

    }

    public NewPostBean(int id, Integer userId, String userName, String avator, Integer productId, String productImg, Integer commentNum, Integer likeNum, Integer sendNum, Integer isLike, Integer isSend, String description, String createTime, String updateTime, Integer version) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.avator = avator;
        this.productId = productId;
        this.productImg = productImg;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.sendNum = sendNum;
        this.isLike = isLike;
        this.isSend = isSend;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.version = version;
    }



    @Override
    public String toString() {
        return "NewPostBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", avator='" + avator + '\'' +
                ", productId=" + productId +
                ", productImg='" + productImg + '\'' +
                ", commentNum=" + commentNum +
                ", likeNum=" + likeNum +
                ", sendNum=" + sendNum +
                ", isLike=" + isLike +
                ", isSend=" + isSend +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
//    /**
//     * code : 200
//     * msg : ????????????
//     * result : [{"post_id":"2409","user_id":"582363","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14757383639657991.png","saying":"","add_time":"1475738364","likes":"0","comments":"0","is_hot":"0","is_top":"0","is_essence":"0","username":"???????????????","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":[]},{"post_id":"2408","user_id":"563046","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/1475725288195472.jpeg","saying":"???????????????????????????????????????","add_time":"1475725303","likes":"0","comments":"1","is_hot":"0","is_top":"0","is_essence":"0","username":"??????","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14742039706486902.jpeg","is_like":"0","comment_list":["???  ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"]},{"post_id":"2406","user_id":"543961","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756614250945495.jpeg","saying":"??????\u2026\u2026????????????\u2026\u2026??????????????????????????????QAQ???????????????\u2026\u2026","add_time":"1475661508","likes":"0","comments":"6","is_hot":"0","is_top":"0","is_essence":"0","username":"-LICHT-","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14756620336796494.jpeg","is_like":"0","comment_list":["?????? ????????????????????????????????????????????????????????????????????????????????????????????????(~???????)~???????????????????????????????????????????????????\u2026\u2026????????????????????????????????????orz???","??????????????????????????????????????????????????????????????????????????????","?????? ??????????????????????????????QAQ???????????????","?????? ???-LICHT-????????????????????????????????????","?????? ????????????????????????????????????~????????????????????????????????????\u2026\u2026","??? ????????????????????????????????????????????????????????????????????????"]},{"post_id":"2405","user_id":"531274","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756593224672148.png","saying":"????????????????????????????????????????????????????????????????????????????????????","add_time":"1475659322","likes":"0","comments":"2","is_hot":"0","is_top":"0","is_essence":"0","username":"Foehn","avatar":"http://img01.cycangcdn.com/ugc/user/avatar/14700074120478917.png","is_like":"0","comment_list":["??????????????????????????????????????? ????????????","??? ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????(?????????)"]},{"post_id":"2404","user_id":"582149","figure":"http://img01.cycangcdn.com/ugc/post/img/201610/14756492642393210.jpeg","saying":"???????????????????????????????????????????????????\n","add_time":"1475649350","likes":"2","comments":"2","is_hot":"0","is_top":"0","is_essence":"0","username":"?????????????????????","avatar":"http://s1.cycangcdn.com/img/user_icon.png","is_like":"0","comment_list":["??????QQ??????????????????????????????????????????(???????????)???","??????QQ??????????????????????????????????????????(???????????)???"]}]
//     */
//
//    private int code;
//    private String msg;
//    /**
//     * post_id : 2409
//     * user_id : 582363
//     * figure : http://img01.cycangcdn.com/ugc/post/img/201610/14757383639657991.png
//     * saying :
//     * add_time : 1475738364
//     * likes : 0
//     * comments : 0
//     * is_hot : 0
//     * is_top : 0
//     * is_essence : 0
//     * username : ???????????????
//     * avatar : http://s1.cycangcdn.com/img/user_icon.png
//     * is_like : 0
//     * comment_list : []
//     */
//
//    private List<ResultBean> result;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<ResultBean> getResult() {
//        return result;
//    }
//
//    public void setResult(List<ResultBean> result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        private String post_id;
//        private String user_id;
//        private String figure;
//        private String saying;
//        private String add_time;
//        private String likes;
//        private String comments;
//        private String is_hot;
//        private String is_top;
//        private String is_essence;
//        private String username;
//        private String avatar;
//        private String is_like;
//        private List<?> comment_list;
//
//        public String getPost_id() {
//            return post_id;
//        }
//
//        public void setPost_id(String post_id) {
//            this.post_id = post_id;
//        }
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getFigure() {
//            return figure;
//        }
//
//        public void setFigure(String figure) {
//            this.figure = figure;
//        }
//
//        public String getSaying() {
//            return saying;
//        }
//
//        public void setSaying(String saying) {
//            this.saying = saying;
//        }
//
//        public String getAdd_time() {
//            return add_time;
//        }
//
//        public void setAdd_time(String add_time) {
//            this.add_time = add_time;
//        }
//
//        public String getLikes() {
//            return likes;
//        }
//
//        public void setLikes(String likes) {
//            this.likes = likes;
//        }
//
//        public String getComments() {
//            return comments;
//        }
//
//        public void setComments(String comments) {
//            this.comments = comments;
//        }
//
//        public String getIs_hot() {
//            return is_hot;
//        }
//
//        public void setIs_hot(String is_hot) {
//            this.is_hot = is_hot;
//        }
//
//        public String getIs_top() {
//            return is_top;
//        }
//
//        public void setIs_top(String is_top) {
//            this.is_top = is_top;
//        }
//
//        public String getIs_essence() {
//            return is_essence;
//        }
//
//        public void setIs_essence(String is_essence) {
//            this.is_essence = is_essence;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public String getIs_like() {
//            return is_like;
//        }
//
//        public void setIs_like(String is_like) {
//            this.is_like = is_like;
//        }
//
//        public List<?> getComment_list() {
//            return comment_list;
//        }
//
//        public void setComment_list(List<?> comment_list) {
//            this.comment_list = comment_list;
//        }
//    }
}
