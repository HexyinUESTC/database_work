package com.example.myshop.find;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.myshop.R;
import com.example.myshop.base.CommentExpandAdapter;
import com.example.myshop.base.CommentExpandableListView;
import com.example.myshop.base.myApplication;
import com.example.myshop.bean.CommentDetailBean;
import com.example.myshop.bean.HotPostBean;
import com.example.myshop.bean.RESULT;
import com.example.myshop.bean.ResultBeanData;
import com.example.myshop.bean.comment;
import com.example.myshop.bean.reply;
import com.example.myshop.user.person;
import com.example.myshop.utils.constants;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class talkDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "talkDetailActivity";
    private Toolbar toolbar;
    private TextView bt_comment;
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private RESULT result;
    private List<CommentDetailBean> commentsList;
    private BottomSheetDialog dialog;
    private HotPostBean postBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_detail);
        postBean = (HotPostBean) getIntent().getSerializableExtra("talk");
        myApplication.setGender("w");
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar11);
        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_lv_comment);
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment);
        bt_comment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情");
        generateTestData();
    }

    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getCom().getId());
                if(isExpanded){
                    expandableListView.collapseGroup(groupPosition);
                }else {
                    expandableListView.expandGroup(groupPosition, true);
                }
                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener( new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                Toast.makeText(Main3Activity.this,"点击了回复",Toast.LENGTH_SHORT).show();
                showReplyDialog(groupPosition);
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第"+groupPosition+"个分组");

            }
        });

    }

    /**
     * by moos on 2018/04/20
     * func:生成测试数据
     * @return 评论数据
     */
    private void generateTestData(){

        getData();
//        processData(testJson);
    }

    public void getData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("talk_id", String.valueOf(1))
                .build();
        Request request = new Request.Builder()
                .url(constants.GET_ALL_COMMENT)
                .post(requestBody)
                .build();
        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "首页请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
//                Log.e(TAG, response.body().string());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            processData(response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                processData(response.body().string());
            }
        });
    }

    private void processData(String string) {
        Log.e(TAG, string);
        Gson gson = new Gson();
        Type type4 = new TypeToken<RESULT<List<CommentDetailBean>>>(){}.getType() ;
        RESULT<List<CommentDetailBean>>  k = gson.fromJson(string,type4);
        commentsList = (List<CommentDetailBean>) k.getDetails();
        Log.e(TAG, String.valueOf(commentsList));
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initExpandableListView(commentsList);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.detail_page_do_comment){

            showCommentDialog();
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){

                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    List<reply> replies = new ArrayList<>();
                    comment com = new comment();
                    com.setId(1);
                    com.setIsLike(0);
                    com.setProductId(postBean.getProductId());
                    com.setUserId(myApplication.getId());
                    com.setIsLike(0);
                    com.setAvator(myApplication.getAvator_Path());
                    com.setContent(commentContent);
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String localDateTime = df.format(date);
                    com.setCreateTime(localDateTime);
                    com.setUpdateTime(localDateTime);
                    com.setLikeNum(0);
                    com.setTalkId(postBean.getId());
                    com.setUserName(myApplication.getAccount());
                    CommentDetailBean detailBean = new CommentDetailBean(com, replies);
//                    CommentDetailBean detailBean = new CommentDetailBean("小明", commentContent,"刚刚");
                    addComment(com);
                    adapter.addTheCommentData(detailBean);
                    Toast.makeText(talkDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(talkDetailActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    private void addComment(comment com) {
        Gson gson = new Gson();
        String json = gson.toJson(com);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.ADD_COMMENT)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("他妈的", e.getMessage());
                talkDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(talkDetailActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                Log.i("提交", string);
                RESULT result = JSON.parseObject(string, RESULT.class);
                if (result.getSuccess() == true) {
                    talkDetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(talkDetailActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    talkDetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(talkDetailActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog_layout,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getCom().getUserName() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){
                    dialog.dismiss();
                    reply rep = new reply();
                    rep.setAvator(myApplication.getAvator_Path());
                    rep.setCommentId(commentsList.get(position).getCom().getId());
                    rep.setContent(replyContent);
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String localDateTime = df.format(date);
                    rep.setCreateTime(localDateTime);
                    rep.setUpdateTime(localDateTime);
                    rep.setId(1);
                    rep.setProductId(postBean.getProductId());
                    rep.setUserId(myApplication.getId());
                    rep.setUserName(myApplication.getAccount());
                    rep.setTalkId(postBean.getId());
                    adapter.addTheReplyData(rep, position);
                    addReply(rep);
                    expandableListView.expandGroup(position);
                    Toast.makeText(talkDetailActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(talkDetailActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    private void addReply(reply rep) {
        Gson gson = new Gson();
        String json = gson.toJson(rep);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = new Request.Builder()
                .url(constants.ADD_REPLY)
                .post(requestBody)
                .build();

        Call call = myApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("评论他妈的", e.getMessage());
                talkDetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(talkDetailActivity.this, "评论提交失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                Log.i("评论", string);
                RESULT result = JSON.parseObject(string, RESULT.class);
                if (result.getSuccess() == true) {
                    talkDetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(talkDetailActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    talkDetailActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(talkDetailActivity.this, "评论失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
    }

}
