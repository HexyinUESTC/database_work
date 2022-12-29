package com.example.addsubview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AddSubView add_sub_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_sub_view = (AddSubView) findViewById(R.id.add_sub_view);
        add_sub_view.setOnNumberChangeListenr(new AddSubView.OnNumberChangeListenr() {
            @Override
            public void OnNumberChange(int value) {
                Toast.makeText(MainActivity.this, "当前商品数量"+value, Toast.LENGTH_LONG).show();
            }
        });
    }
}
