package com.example.myshop.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myshop.R;
import com.example.myshop.base.myApplication;
import com.example.myshop.utils.TitleLayout;


public class EditName extends AppCompatActivity {
    private TitleLayout tl_title;
    private EditText edit_name;
    private static final int EDIT_NAME = 3;
    private static final int EDIT_PHONE = 4;
    private static final int EDIT_EMAIL = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        tl_title = (TitleLayout) findViewById(R.id.tl_title);
        edit_name = (EditText) findViewById(R.id.et_edit_name);
        edit_name.setText(getIntent().getStringExtra("name"));
        final int tag = getIntent().getIntExtra("tag", EDIT_NAME);
        if(tag == EDIT_NAME) {
            tl_title.setTitle("修改用户名");
        }
        else if(tag == EDIT_EMAIL) {
            tl_title.setTitle("修改邮箱");
        }
        else if(tag == EDIT_PHONE) {
            tl_title.setTitle("修改电话号码");
        }
        tl_title.getIv_backward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置监听器
        //如果点击完成，则更新loginUser并销毁
        tl_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                loginUser.setName(edit_name.getText().toString());
                if(tag == EDIT_NAME) {
                    Intent intent = new Intent();
                    intent.putExtra("name", edit_name.getText().toString());
                    setResult(RESULT_OK, intent);
                    myApplication.setAccount(edit_name.getText().toString());
                }
                else if(tag == EDIT_EMAIL) {
                    Intent intent = new Intent();
                    intent.putExtra("name", edit_name.getText().toString());
                    setResult(RESULT_OK, intent);
                    myApplication.setEmail(edit_name.getText().toString());
                }
                else if(tag == EDIT_PHONE) {
                    Intent intent = new Intent();
                    intent.putExtra("name", edit_name.getText().toString());
                    setResult(RESULT_OK, intent);
                    myApplication.setPhone(edit_name.getText().toString());
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
