package com.example.myshop.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myshop.R;
import com.example.myshop.utils.TitleLayout;

public class comment extends AppCompatActivity {
    private TitleLayout co_title;
    private EditText co_post_thing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        co_title = (TitleLayout) findViewById(R.id.co_title);
        co_post_thing = (EditText) findViewById(R.id.co_post_thing);
        co_title.setTitle("订单评价");
        co_title.getIv_backward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        co_title.getTextView_forward().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
