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


/**
 * 日期选择对话框
 * 
 * @author ywl
 *
 */
public class ChangeBirthDialog extends Dialog implements View.OnClickListener {

	private Context context;
	private WheelView wvYear;
	private WheelView wvMonth;
	private WheelView wvDay;

	private View vChangeBirth;
	private View vChangeBirthChild;
	private TextView btnSure;
	private TextView btnCancel;

	private ArrayList<String> arry_years = new ArrayList<String>();
	private ArrayList<String> arry_months = new ArrayList<String>();
	private ArrayList<String> arry_days = new ArrayList<String>();
	private CalendarTextAdapter mYearAdapter;
	private CalendarTextAdapter mMonthAdapter;
	private CalendarTextAdapter mDaydapter;

	private int day;

	private int currentYear = getYear();
	private int currentMonth = 1;
	private int currentDay = 1;

	private int maxTextSize = 24;
	private int minTextSize = 14;

	private String selectYear;
	private String selectMonth;
	private String selectDay;

	private TextView textView;

	private OnBirthListener onBirthListener;

	public ChangeBirthDialog(Context context, TextView returnTextview) {
		super(context, R.style.ShareDialog);
		this.context = context;
		textView = returnTextview;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changebirth);
		wvYear = (WheelView) findViewById(R.id.wv_birth_year);
		wvMonth = (WheelView) findViewById(R.id.wv_birth_month);
		wvDay = (WheelView) findViewById(R.id.wv_birth_day);

		vChangeBirth = findViewById(R.id.ly_myinfo_changebirth);
		vChangeBirthChild = findViewById(R.id.ly_myinfo_changebirth_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);

		vChangeBirth.setOnClickListener(this);
		vChangeBirthChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		initData();

		initYears();
		mYearAdapter = new CalendarTextAdapter(context, arry_years, 1, maxTextSize, minTextSize);
		wvYear.setVisibleItems(5);
		wvYear.setViewAdapter(mYearAdapter);
		wvYear.setCurrentItem(1);

		initMonths();
		mMonthAdapter = new CalendarTextAdapter(context, arry_months, currentMonth - 1, maxTextSize, minTextSize);
		wvMonth.setVisibleItems(5);
		wvMonth.setViewAdapter(mMonthAdapter);
		wvMonth.setCurrentItem(currentMonth - 1);

		initDays();
		mDaydapter = new CalendarTextAdapter(context, arry_days, currentDay -1 , maxTextSize, minTextSize);
		wvDay.setVisibleItems(5);
		wvDay.setViewAdapter(mDaydapter);
		wvDay.setCurrentItem(currentDay - 1);

		wvYear.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				selectYear = currentText;
				setTextviewSize(currentText, mYearAdapter);
				currentYear = Integer.parseInt(currentText);
			}
		});

		wvYear.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mYearAdapter);
			}
		});

		wvMonth.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
				selectMonth = currentText;
				currentMonth = Integer.parseInt(currentText);
				setTextviewSize(currentText, mMonthAdapter);
				initDays();
				currentDay = 1;
				mDaydapter = new CalendarTextAdapter(context, arry_days, currentDay - 1, maxTextSize, minTextSize);
				wvDay.setVisibleItems(5);
				wvDay.setViewAdapter(mDaydapter);
				wvDay.setCurrentItem(currentDay - 1);
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
				currentDay = Integer.parseInt(currentText);
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

	public void initYears() {
		for (int i = -1; i < 2; i++) {
			arry_years.add((i + currentYear) + "");
		}
	}

	public void initMonths() {
		arry_months.clear();
		for (int i = 1; i <= 12; i++) {
			arry_months.add(i + "");
		}
	}

	public void initDays() {
		arry_days.clear();
		for (int i = 1; i <= calDays(this.currentYear,this.currentMonth); i++) {
			arry_days.add(i + "");
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

	public void setBirthdayListener(OnBirthListener onBirthListener) {
		this.onBirthListener = onBirthListener;
	}

	@Override
	public void onClick(View v) {

		if (v == btnSure) {
			if (onBirthListener != null) {
				onBirthListener.onClick(selectYear, selectMonth, selectDay);
			}
			textView.setText(currentYear + "-" + currentMonth + "-" + currentDay);
		} else if (v == btnCancel) {

		} else if (v == vChangeBirthChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();

	}

	public interface OnBirthListener {
		public void onClick(String year, String month, String day);
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

	public int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	public void initData() {
		setDate(getYear(), getMonth(), getDay());
	}

	/**
	 * 设置年月日
	 *
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setDate(int year, int month, int day) {

		if(!textView.getText().toString().isEmpty())
		{
			String tmpString[] = textView.getText().toString().split("-");
			currentYear = Integer.parseInt(tmpString[0]);
			currentMonth = Integer.parseInt(tmpString[1]);
			currentDay = Integer.parseInt(tmpString[2]);
		}else{
			this.currentYear = year;
			this.currentMonth = month;
			this.currentDay = day;
		}
		selectYear = currentYear + "";
		selectMonth = currentMonth + "";
		selectDay = currentDay + "";
	}

	/**
	 * 计算每月多少天
	 *
	 * @param month
	 */
	public int calDays(int year, int month) {
		boolean leayyear = false;
		if (year % 4 == 0 && year % 100 != 0) {
			leayyear = true;
		} else {
			leayyear = false;
		}
		for (int i = 1; i <= 12; i++) {
			switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					return 31;
				case 2:
					if (leayyear) {
						return 29;
					} else {
						return 28;
					}
				case 4:
				case 6:
				case 9:
				case 11:
					return 30;
			}
		}
		return 30;
	}
}