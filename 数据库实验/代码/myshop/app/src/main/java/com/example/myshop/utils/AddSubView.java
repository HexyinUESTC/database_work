package com.example.myshop.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myshop.R;


public class AddSubView extends LinearLayout implements View.OnClickListener {
    private Context context;
    private ImageView iv_sub;
    private TextView tv_value;
    private ImageView iv_add;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;
    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View.inflate(context, R.layout.add_sub_view,this);
        iv_sub = (ImageView) findViewById(R.id.iv_sub);
        tv_value = (TextView) findViewById(R.id.tv_value);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        int v = getValue();
        setValue(v);
        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                addNumber();
                break;
            case R.id.iv_sub:
                subNumber();
                break;
        }
//        Toast.makeText(context, "当前商品数=="+value, Toast.LENGTH_LONG).show();
    }

    private void subNumber() {
        if(value > minValue) {
            value--;
        }
        setValue(value);
        if(onNumberChangeListenr != null) {
            onNumberChangeListenr.OnNumberChange(value);
        }
    }

    private void addNumber() {
        if(value < maxValue) {
            value++;
        }
        setValue(value);
        if(onNumberChangeListenr != null) {
            onNumberChangeListenr.OnNumberChange(value);
        }
    }


    public int getValue() {
        String valueString = tv_value.getText().toString().trim();
        if(!TextUtils.isEmpty(valueString)) {
            value = Integer.parseInt(valueString);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    public interface OnNumberChangeListenr{
        public void OnNumberChange(int value);
    }

    private OnNumberChangeListenr onNumberChangeListenr;

    public void setOnNumberChangeListenr(OnNumberChangeListenr onNumberChangeListenr) {
        this.onNumberChangeListenr = onNumberChangeListenr;
    }
}
