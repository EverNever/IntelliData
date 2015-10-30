package com.uestc.znll.ui;

import android.os.Bundle;

import com.uestc.znll.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int setRootView() {
        return R.layout.activity_main;
    }
}
