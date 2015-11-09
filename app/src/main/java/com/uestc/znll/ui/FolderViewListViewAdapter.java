package com.uestc.znll.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.uestc.znll.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/3.
 */
public class FolderViewListViewAdapter extends BaseAdapter {
    private Context m_context;
    private List<Map<String,Object>> m_list;
    private TextView textviewMonth;
    private TextView textviewDetail;
    private ImageButton imagebuttonOpen;

    public FolderViewListViewAdapter(Context c, List<Map<String,Object>> list){
        this.m_context= c;
        this.m_list=list;
    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object getItem(int position) {
        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(m_context).inflate(R.layout.folderview_list_item, null);
        }
        textviewMonth = (TextView)convertView.findViewById(R.id.folderview_listviewitem_textview_month);
        textviewDetail = (TextView)convertView.findViewById(R.id.folderview_listviewitem_textview_detail);
        imagebuttonOpen = (ImageButton)convertView.findViewById(R.id.folderview_listviewitem_button_open);

        Map tmpMap = m_list.get(position);
        textviewMonth.setText((String)tmpMap.get("month"));
        textviewDetail.setText((String) tmpMap.get("detail"));
        imagebuttonOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(m_context,DatadetailActivity.class);
                m_context.startActivity(intent);
            }
        });

        return convertView;
    }
}
