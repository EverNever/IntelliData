package com.uestc.znll.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.uestc.znll.R;

/**
 * Created by Administrator on 2015/11/5.
 */
public class MainEditDialog extends Dialog {
    private Context m_context;
    private View.OnClickListener onClickListener;
    private ListView listview;
    private String listStrings[];

    public MainEditDialog(Context context) {
        super(context);
        m_context = context;
        listStrings = new String[]{"套餐流量","闲时流量","节假日流量","单月叠加包"};
    }
    void findWidget()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(m_context, android.R.layout.simple_list_item_1);
        adapter.addAll(listStrings);
        listview.setAdapter(adapter);
    }

    void setListener()
    {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        //流量套餐界面
                        break;
                    case 1:
                        //
                        break;
                    case 2:
                        //
                        break;
                    case 3:
                        //
                        break;
                }
            }
        });
    }
}
