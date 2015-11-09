package com.uestc.znll.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.uestc.znll.util.AlarmUtil;

/**
 * 开机自启同时启动定时任务，每隔SYNC_INTERVAL向DataReceiver发送广播
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmUtil.startAlarmTask(context);
    }
}
