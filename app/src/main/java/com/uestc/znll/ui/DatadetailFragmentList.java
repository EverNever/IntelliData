package com.uestc.znll.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uestc.znll.R;

/**
 * Created by Administrator on 2015/11/1.
 */
public class DatadetailFragmentList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.datadetail_fragement_monthgraphic, null);

    }
}