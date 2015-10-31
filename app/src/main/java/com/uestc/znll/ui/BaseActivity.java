package com.uestc.znll.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uestc.znll.R;
import com.uestc.znll.util.ImmerseStatusBar;

/**
 * Created by chao on 2015/8/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    //用于logcat的tag
    protected String TAG = getClass().getSimpleName();
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(setRootView());
        initLayout();
        initValue();
        initListener();
    }

    /**
     * 初始化布局，主要是findView
     */
    abstract protected void initLayout();

    /**
     * 初始化值，比如setAdapter，setText
     */
    abstract protected void initValue();

    /**
     * 初始化监听器，比如setOnClickListener
     */
    abstract protected void initListener();

    /**
     * 设置根视图layout
     *
     * @return layoutRes
     */
    abstract protected int setRootView();
}
