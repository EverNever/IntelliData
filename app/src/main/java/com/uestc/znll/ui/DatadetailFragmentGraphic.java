package com.uestc.znll.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.znll.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/11/1.
 */
public class DatadetailFragmentGraphic extends Fragment {

    HashMap<Double, Double> map;
    DataDetailFragmentChartView tu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datadetail_fragement_graphic, null);

        map=new HashMap<Double, Double>();
        map.put(1.0, (double) 0);
        map.put(2.0, 25.0);
        map.put(3.0, 32.0);
        map.put(4.0, 41.0);
        map.put(5.0, 16.0);
        map.put(6.0, 36.0);
        map.put(7.0, 26.0);
        map.put(8.0, 55.0);

        tu= (DataDetailFragmentChartView) view.findViewById(R.id.menulist);
        initChart(map,100,10,"x","y");
        return view;
    }
    void initChart(HashMap < Double, Double > map,int totalvalue,int pjvalue,String xstr,String ystr)
    {
        /**
         * @param map 需要的数据，虽然key是double，但是只用于排序和显示，与横向距离无关
         * @param totalvalue Y轴的最大值
         * @param pjvalue Y的每格值
         * @param xstr X轴的单位
         * @param ystr Y轴的单位
         * @param isylineshow 是否显示纵向网格
         */
        tu.SetTuView(map, totalvalue, pjvalue, xstr, ystr, true);
        tu.setMargint(20);
        tu.setMarginb(50);
        tu.setMstyle(DataDetailFragmentChartView.Mstyle.Line);
    }
}
