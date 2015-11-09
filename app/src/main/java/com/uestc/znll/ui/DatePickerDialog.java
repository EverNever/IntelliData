package com.uestc.znll.ui;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.znll.R;
import com.uestc.znll.view.Adapter.AbstractWheelTextAdapter;
import com.uestc.znll.view.OnWheelChangedListener;
import com.uestc.znll.view.OnWheelScrollListener;
import com.uestc.znll.view.WheelView;

import org.w3c.dom.Text;


/**
 * 日期选择对话框
 *
 * @author ywl
 *
 */
public class DatePickerDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView textView;
    private WheelView wvMonth;
    private WheelView wvDay
            ;

    private TextView btnSure;
    private TextView btnCancel;

    private ArrayList<String> arry_Months = new ArrayList<String>();
    private ArrayList<String> arry_Days = new ArrayList<String>();
    private CalendarTextAdapter mMonthAdapter;
    private CalendarTextAdapter mDaydapter;

    private int currentYear = 2015;
    private int currentMonth = 1;
    private int currentDay = 1;

    private int maxTextSize = 24;
    private int minTextSize = 14;

    private boolean issetdata = false;

    private String selectMonth;
    private String selectDay
            ;

    private OnBirthListener onBirthListener;

    public DatePickerDialog(Context context, TextView returnTextView) {
        super(context, R.style.ShareDialog);
        this.context = context;
        textView = returnTextView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_datepicker);
        wvMonth = (WheelView) findViewById(R.id.wv_month);
        wvDay = (WheelView) findViewById(R.id.wv_day);

        btnSure = (TextView) findViewById(R.id.btn_datepicker_sure);
        btnCancel = (TextView) findViewById(R.id.btn_datepicker_cancel);

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        if (!issetdata) {
            initData();
        }

        initMonths();
        mMonthAdapter = new CalendarTextAdapter(context, arry_Months,currentMonth, maxTextSize, minTextSize);
        wvMonth.setViewAdapter(mMonthAdapter);
        wvMonth.setCurrentItem(currentMonth);

        initDays();
        mDaydapter = new CalendarTextAdapter(context, arry_Days, currentDay, maxTextSize, minTextSize);
        wvDay.setViewAdapter(mDaydapter);
        wvDay.setCurrentItem(currentDay);

        wvMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                System.out.println("Month = " + currentText);
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
                System.out.println("Day" + " = " + selectDay);
            }
        });

        wvDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) mDaydapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });

    }

    public void initMonths() {
        for(int i = 0; i < 24;i++)
        {
            arry_Months.add(i + "");
        }
    }

    public void initDays() {
        for (int i = 0; i < 60; i++) {
            arry_Days.add(i + "");
        }
    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnSure) {
            if (onBirthListener != null) {
                onBirthListener.onClick(selectMonth, selectDay);
            }
        } else if (v == btnCancel) {

        } else {
            dismiss();
        }
        dismiss();

    }

    public interface OnBirthListener {
        public void onClick(String Month, String Day
        );
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    public int getYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    public int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }

    public int getDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public void initData() {
        if(!textView.getText().toString().equals(""))
        {
            String text[] = textView.getText().toString().split("-");
            for(int i=0;i<text.length;i++)
            {
                System.out.println(text[i]);
            }
            currentYear = 0;
            currentMonth = Integer.parseInt(text[1]);
            currentDay = Integer.parseInt(text[2]);
        }else
        {
            currentMonth = getMonth();
            currentDay = getDay();
        }
        setDate(currentMonth,currentDay);
    }

    /**
     * 设置年月日
     *
     * @param Month
     * @param Day
     *
     */
    public void setDate( int Month, int Day
    ) {
        selectMonth = Month + "";
        selectDay = Day + "";
        issetdata = true;
        this.currentMonth = Month;
        this.currentDay = Day;
    }
}