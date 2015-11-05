package com.uestc.znll.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View view = inflater.inflate(R.layout.main_edit_dialog,null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        findWidget();
        setListener();
    }
    void findWidget()
    {
        listview = (ListView)findViewById(R.id.main_edit_dialog_listview);
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
                        AddPacketDialog dialog = new AddPacketDialog(m_context);
                        dialog.show();
                        break;
                    case 1:
                        Intent intent = new Intent(m_context,AddIdleActivity.class);
                        m_context.startActivity(intent);
                        MainEditDialog.this.dismiss();
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
