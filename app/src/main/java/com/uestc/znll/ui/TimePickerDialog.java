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
public class TimePickerDialog extends Dialog implements View.OnClickListener {

	private Context context;
	private TextView textView;
	private WheelView wvHour;
	private WheelView wvMinute;

	private TextView btnSure;
	private TextView btnCancel;

	private ArrayList<String> arry_Hours = new ArrayList<String>();
	private ArrayList<String> arry_Minutes = new ArrayList<String>();
	private CalendarTextAdapter mHourAdapter;
	private CalendarTextAdapter mMinutedapter;

	private int currentHour = 1;
	private int currentMinute = 1;

	private int maxTextSize = 24;
	private int minTextSize = 14;


	private String selectHour;
	private String selectMinute;

	private OnBirthListener onBirthListener;

	public TimePickerDialog(Context context,TextView returnTextView) {
		super(context, R.style.ShareDialog);
		this.context = context;
		textView = returnTextView;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_timepicker);

		wvHour = (WheelView) findViewById(R.id.wv_hour);
		wvMinute = (WheelView) findViewById(R.id.wv_minute);

		btnSure = (TextView) findViewById(R.id.btn_timepicker_sure);
		btnCancel = (TextView) findViewById(R.id.btn_timepicker_cancel);

		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		initData();

		initHours();
		mHourAdapter = new CalendarTextAdapter(context, arry_Hours,currentHour, maxTextSize, minTextSize);
		wvHour.setViewAdapter(mHourAdapter);
		wvHour.setCurrentItem(currentHour);

		initMinutes();
		mMinutedapter = new CalendarTextAdapter(context, arry_Minutes, currentMinute, maxTextSize, minTextSize);
		wvMinute.setViewAdapter(mMinutedapter);
		wvMinute.setCurrentItem(currentMinute);

		wvHour.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mHourAdapter.getItemText(wheel.getCurrentItem());
				System.out.println("hour = " + currentText);
				selectHour = currentText;
				setTextviewSize(currentText, mHourAdapter);
			}
		});

		wvHour.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mHourAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mHourAdapter);
			}
		});

		wvMinute.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mMinutedapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mMinutedapter);
				selectMinute = currentText;
				System.out.println("minute = " + selectMinute);
			}
		});

		wvMinute.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mMinutedapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mMinutedapter);
			}
		});

	}

	public void initHours() {
		for(int i = 0; i < 24;i++)
		{
			arry_Hours.add(i + "");
		}
	}

	public void initMinutes() {
		for (int i = 0; i < 60; i++) {
			arry_Minutes.add(i + "");
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
				onBirthListener.onClick(selectHour, selectMinute);
			}
			if(selectHour.length()<2)
			{
				selectHour = "0"+selectHour;
			}
			if(selectMinute.length()<2)
			{
				selectMinute = "0" + selectMinute;
			}
			textView.setText(selectHour + ":" + selectMinute);
		} else if (v == btnCancel) {

		} else {
			dismiss();
		}
		dismiss();

	}

	public interface OnBirthListener {
		public void onClick(String Hour, String Minute);
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


	public int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MINUTE);
	}

	public void initData() {
		System.out.println(textView.getText().toString());
		if(!textView.getText().toString().equals(""))
		{
			String text[] = textView.getText().toString().split(":");
			for(int i=0;i<text.length;i++)
			{
				System.out.println(text[i]);
			}
			currentHour = Integer.parseInt(text[0]);
			currentMinute = Integer.parseInt(text[1]);
		}else
		{
			currentHour = getHour();
			currentMinute = getMinute();
		}
		setDate(currentHour,currentMinute);
	}

	/**
	 * 设置年月日
	 *
	 * @param Hour
	 * @param Minute
	 */
	public void setDate( int Hour, int Minute) {
		selectHour = Hour + "";
		selectMinute = Minute + "";
		this.currentHour = Hour;
		this.currentMinute = Minute;
	}
}