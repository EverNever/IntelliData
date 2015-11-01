package com.uestc.znll.ui;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.uestc.znll.R;

/**
 * Created by Administrator on 2015/11/1.
 */
public class DatadetailActivity extends BaseActivity {

    private FragmentTabHost tabHost;

    private Class tabFragmentClass[] = {DatadetailFragmentGraphic.class,DatadetailFragmentList.class};
    private int tabItemImage[]={R.drawable.button_datadetail_graphic,
                                    R.drawable.button_datadetail_list};
    private String tabItemText[]={"图标汇总","详细数据"};


    @Override
    protected void initLayout() {
        tabHost  = (FragmentTabHost)findViewById(R.id.datadetail_tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.datadetail_realcontent);

        int count = tabFragmentClass.length;
        for(int i = 0; i < count; i++)
        {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabItemText[i]).setIndicator(getTabItemView(i));
            tabHost.addTab(tabSpec, tabFragmentClass[i], null);
            //tabHost.getTabWidget().getChildAt(i).setBackgroundResource();
        }
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int setRootView() {
        return R.layout.activity_datadetail;
    }

    private View getTabItemView(int index)
    {
        View view = LayoutInflater.from(this).inflate(R.layout.datadetail_tab_item,null);
        ImageView imageview = (ImageView)view.findViewById(R.id.datadetail_tabitem_imagebutton);
        imageview.setImageResource(tabItemImage[index]);
        TextView textview = (TextView)view.findViewById(R.id.datadetail_tabitem_textview);
        textview.setText(tabItemText[index]);

        return view;
    }

}
