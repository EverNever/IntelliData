package com.uestc.znll.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.uestc.znll.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/1.
 */
public class FolderViewActivity extends BaseActivity
{
    private ListView listview;
    private Button editButton;

    public FolderViewActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folderview);
        initLayout();
        initValue();
        initListener();
    }

    @Override
    protected void initLayout() {
        listview = (ListView)findViewById(R.id.folderview_listview);
        editButton = (Button)findViewById(R.id.folderview_button_edit);
    }

    @Override
    protected void initValue() {
        FolderViewListViewAdapter adapter = new FolderViewListViewAdapter(this,getData());
        listview.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FolderViewActivity.this,DatadetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int setRootView() {
        return R.layout.activity_folderview;
    }

    private List<Map<String,Object>> getData()
    {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("month","9 yue fen");
        map.put("detail", "800m");
        list.add(map);
        list.add(map);
        return list;
    }
}
