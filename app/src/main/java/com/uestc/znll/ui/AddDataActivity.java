package com.uestc.znll.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uestc.znll.R;
import com.uestc.znll.SQLConnection.DataPakBean;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/11/8.
 */
public class AddDataActivity extends BaseActivity {

    private EditText packageName;
    private Spinner dataCategory;
    private RelativeLayout lybegintTime;
    private RelativeLayout lyendTime;
    private TextView beginTime;
    private LinearLayout lyTime;
    private TextView endTime;
    private EditText totalData;
    private Spinner totalDataMorG;
    private EditText usedData;
    private Spinner usedDataMorG;
    private Button btnOk;

    private DataPakBean bean;
    private boolean modifyOrAdd;//0-add 1-modify

    public void AddDataActivity(DataPakBean bean)
    {
        this.bean = bean;
        modifyOrAdd = true;
    }
    public void AddDataActivity()
    {
        this.bean = null;
        modifyOrAdd = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(modifyOrAdd)
        {
            packageName.setText(bean.getDataPakName());
//            dataCategory.
            //上面一行需要设置流量套餐的种类，dataCategory 是一个spinner
//            beginTime.setText()
//            endTime.setText();
//            totalData.setText(bean.getDataPakSum().toString());
//            usedData.setText(bean.getDataPakUsed().toString());
            //上面的两行需要修改，totalData设置为流量数字，totalDataMorG设置为单位
            //totalDataMorG usedDataMorG 都是spinner
        }
    }

    @Override
    protected void initLayout() {
        packageName = (EditText)findViewById(R.id.adddata_edittext_packagename);
        dataCategory = (Spinner)findViewById(R.id.adddata_spinner_package_category);
        lyTime = (LinearLayout)findViewById(R.id.adddata_layout_time);
        lybegintTime = (RelativeLayout)findViewById(R.id.adddata_layout_begintime);
        lyendTime = (RelativeLayout)findViewById(R.id.adddata_layout_endtime);
        beginTime = (TextView)findViewById(R.id.adddata_textview_begintime);
        endTime = (TextView)findViewById(R.id.adddata_textviwe_endtime);
        totalData = (EditText)findViewById(R.id.adddata_edittext_totaldata);
        totalDataMorG = (Spinner)findViewById(R.id.adddata_spinner_total_MorG);
        usedData = (EditText)findViewById(R.id.adddata_edittext_useddata);
        usedDataMorG = (Spinner)findViewById(R.id.adddata_spinner_used_MorG);
        btnOk = (Button)findViewById(R.id.adddata_button_ok);
    }

    @Override
    protected void initValue() {
        packageName.setText(dataCategory.getSelectedItem().toString());
    }

    private int lyTimeOldHeight = 0;
    @Override
    protected void initListener() {
        dataCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String currentText = (String)dataCategory.getSelectedItem();
                packageName.setText(currentText);
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)lyTime.getLayoutParams();
                if(p.height != 0)
                {
                    lyTimeOldHeight = p.height;
                }
                if("普通套餐".equals(currentText))
                {
                    p.height = 0;
                }else if("闲时流量".equals(currentText)){
                    p.height = lyTimeOldHeight;
                    setIdleBeginAndEndTimeTextView();

                }else if("节假日流量".equals(currentText)){
                    p.height = lyTimeOldHeight;
                    setHolidayBeginAndEndTimeTextView();

                }else if("单月流量包".equals(currentText)){
                    p.height = 0;
                }else {

                }
                lyTime.setLayoutParams(p);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lybegintTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("节假日流量".equals(dataCategory.getSelectedItem().toString()))
                {
                    DatePickerDialog dialog = new DatePickerDialog(AddDataActivity.this,beginTime);
                    dialog.show();
                }else
                {
                    TimePickerDialog dialog = new TimePickerDialog(AddDataActivity.this, beginTime);
                    dialog.show();
                }
            }
        });
        lyendTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("节假日流量".equals(dataCategory.getSelectedItem().toString()))
                {
                    DatePickerDialog dialog = new DatePickerDialog(AddDataActivity.this,endTime);
                    dialog.show();
                }else
                {
                    TimePickerDialog dialog = new TimePickerDialog(AddDataActivity.this, endTime);
                    dialog.show();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData())
                {
                    //往数据库中添加数据
                }
            }
        });
    }

    @Override
    protected int setRootView() {
        return R.layout.activty_add_data;
    }

    private boolean checkData(){
        if(packageName.getText().toString().isEmpty())
        {
            Toast.makeText(this,"套餐名不能为空",Toast.LENGTH_LONG).show();
            return false;
        }
        double dataTotal;
        double dataUsed;
        try {
            dataTotal = Double.parseDouble(totalData.getText().toString());
        }catch (NumberFormatException e)
        {
            Toast.makeText(this,"套餐总数格式有误",Toast.LENGTH_LONG).show();
            return false;
        }
        try{
            dataUsed = Double.parseDouble(usedData.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(this,"套餐总数格式有误",Toast.LENGTH_LONG).show();
            return false;
        }

        if(dataTotal < dataUsed)
        {
            Toast.makeText(this,"已使用套餐量大于套餐总量",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void setIdleBeginAndEndTimeTextView()
    {
        beginTime.setText("23:00");
        endTime.setText("07:00");
    }
    private void setHolidayBeginAndEndTimeTextView()
    {
        Calendar c = Calendar.getInstance();
        String tmpText = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);
        beginTime.setText(tmpText);
        endTime.setText(tmpText);
    }

}
