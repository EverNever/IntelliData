package com.uestc.znll.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.uestc.znll.constant.Config;
import com.uestc.znll.receiver.DataReceiver;

/**
 * Created by chao on 2015/11/4.
 */
public class AlarmUtil {
    /**
     * 启动Alarm定时任务
     * @param context 上下文
     */
    public static void startAlarmTask(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent dataIntent = new Intent(context, DataReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context,
                1, dataIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                Config.SYNC_INTERVAL, sender);
    }
}
