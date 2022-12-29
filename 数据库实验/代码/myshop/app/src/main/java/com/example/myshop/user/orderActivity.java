package com.example.myshop.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myshop.R;
import com.example.myshop.cart.mapActivity;
import com.example.myshop.utils.TitleLayout;

public class orderActivity extends AppCompatActivity {

    private Button wuliu;
    private TitleLayout order_tl;
    private Button pingjia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_order_center);
        wuliu = (Button) findViewById(R.id.wuliu);
        order_tl = (TitleLayout) findViewById(R.id.order_tl);
        pingjia = (Button) findViewById(R.id.pingjia);
        order_tl.setTitle("我的订单");
        order_tl.getTextView_forward().setText("编辑");
        wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderActivity.this, mapActivity.class);
                startActivity(intent);
            }
        });
        pingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderActivity.this, comment.class);
                startActivity(intent);
            }
        });

    }
}
