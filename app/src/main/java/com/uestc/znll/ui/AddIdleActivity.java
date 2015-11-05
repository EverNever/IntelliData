package com.uestc.znll.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uestc.znll.R;

import org.w3c.dom.Text;

import java.sql.Time;

/**
 * Created by Administrator on 2015/11/5.
 */
public class AddIdleActivity extends BaseActivity{

    private TextView textviewTotalData;
    private TextView textviewBeginTime;
    private TextView textviewEndTime;
    private RelativeLayout layoutTotalData;
    private RelativeLayout layoutBeginTime;
    private RelativeLayout layoutEndTime;


    @Override
    protected void initLayout() {
        textviewTotalData = (TextView)findViewById(R.id.addidle_textview_totaldata);
        textviewBeginTime = (TextView)findViewById(R.id.addidle_textview_begintime);
        textviewEndTime = (TextView)findViewById(R.id.addidle_textviwe_endtime);
        layoutTotalData = (RelativeLayout)findViewById(R.id.addidle_layout_total);
        layoutBeginTime = (RelativeLayout)findViewById(R.id.addidle_layout_begintime);
        layoutEndTime = (RelativeLayout)findViewById(R.id.addidle_layout_endtime);
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected void initListener() {
        layoutBeginTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeBirthDialog dialog = new ChangeBirthDialog(AddIdleActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    protected int setRootView() {
        return R.layout.activity_addidle;
    }
}
