package com.uestc.znll.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.uestc.znll.R;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/10/31.
 */
public class MainListViewAdapter extends BaseAdapter {
    private Context m_context;
    private List<Map<String,Object>> m_list;
    private ProgressBar m_progressbar;
    private TextView TextViewDataType;
    private TextView TextViewDataUsage;
    private TextView TextViewRemainPecent;
    private TextView TextViewRemainDetail;


    public MainListViewAdapter(Context c, List<Map<String,Object>> list){
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
            convertView = LayoutInflater.from(m_context).inflate(R.layout.main_listview_item, null);
        }
        m_progressbar = (ProgressBar)convertView.findViewById(R.id.main_listview_progressbar);
        TextViewDataType = (TextView)convertView.findViewById(R.id.main_listview_textview_datatype);
        TextViewDataUsage = (TextView)convertView.findViewById(R.id.main_listview_textview_datausage);
        TextViewRemainPecent = (TextView)convertView.findViewById(R.id.main_listview_textview_remainpercent);
        TextViewRemainDetail = (TextView)convertView.findViewById(R.id.main_listview_textview_remaindetail);

        Map tmpMap = m_list.get(position);
        setM_progressbar((Integer)tmpMap.get("progressvalue"));
        TextViewDataType.setText((String) tmpMap.get("datatype"));
        TextViewDataUsage.setText((String) tmpMap.get("datausage"));
        TextViewRemainPecent.setText((String)tmpMap.get("remainpecent"));
        TextViewRemainDetail.setText((String)tmpMap.get("remaindetail"));

        System.out.println("datatype  " + (String) tmpMap.get("datatype"));
        System.out.println("datausage  "+(String) tmpMap.get("datausage"));
        System.out.println("remainpecent  "+(String)tmpMap.get("remainpecent"));
        System.out.println("remaindetail  "+(String)tmpMap.get("remaindetail"));

        return convertView;
    }

    private void setM_progressbar(int progress)
    {

        if(progress <0)
        {
            progress = 0;
        }
        m_progressbar.setProgress(progress);
        switch (progress/10)
        {
            case 0:
            case 1:
            case 2:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_0_to_10));
                break;
            case 3:
            case 4:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_10_to_20));
                break;
            case 5:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_20_to_30));
                break;
            case 6:
            case 7:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_30_to_40));
                break;
            case 8:
            case 9:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_40_to_50));
                break;
            case 10:
            default:
                m_progressbar.setProgressDrawable(m_context.getResources().getDrawable(R.drawable.progressbar_color_50_to_60));
                break;

        }
    }
}
