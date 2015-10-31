package com.uestc.znll.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.uestc.znll.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    //xml文件中定义的控件
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidgets();

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

    private void findWidgets(){
        listView = (ListView)findViewById(R.id.Main_List);
    }

}
