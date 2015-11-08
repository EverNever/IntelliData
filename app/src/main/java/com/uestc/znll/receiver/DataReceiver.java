package com.uestc.znll.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;

import com.uestc.znll.SQLConnection.DataPakBean;
import com.uestc.znll.SQLConnection.SQLConnection;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chao on 2015/11/4.
 */
public class DataReceiver extends BroadcastReceiver {
    private SQLConnection connection;
    private Context context;
    private long lastBytes = 0;//上次的receiver到从开机到现在的流量

    @Override
    public void onReceive(Context context, Intent intent) {
        if(connection==null)connection = new SQLConnection(context);
        try {
            connection.createDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<DataPakBean> dataPakBeanList = connection.getAllDataPaksInOneMonth(new Date().getYear(), new Date().getMonth());

        long currentTimeMillis = System.currentTimeMillis();
        long currentBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
        long trafficAmount = currentBytes - lastBytes;
        lastBytes = currentBytes;

        //TODO 判断要减哪个流量包的流量

        //TODO 获取used流量

        //TODO updateused流量

//        for (DataPakBean packageBean : dataPakBeanList) {
//            if(!packageBean.getIsDataPakDaily() && !packageBean.getIsDataPakMonthly()){
//                //节假日包
//                if(Long.parseLong(packageBean.getDataPakStart()) < currentTimeMillis
//                        && Long.parseLong(packageBean.getDataPakEnd()) > currentTimeMillis){
//                    Double used = packageBean.getDataPakUsed() + trafficAmount;
//                    connection.updateDataPak(packageBean.getId().toString(), used.toString());
//                    break;
//                }
//            } else if(packageBean.isDaily && !packageBean.isMonthly){
//                //购买的单月流量包
//                if(packageBean.beginTimeMillis < currentTimeMillis && packageBean.endTimeMillis > currentTimeMillis){
//                    trafficHandler.reduceTrafficBytes(packageBean.id, trafficAmount);
//                    break;
//                }
//            } else if(packageBean.isDaily && packageBean.isMonthly){
//                //普通或者闲时
//                if((packageBean.endTimeMillis - packageBean.beginTimeMillis)/3600000 < 24){
//                    //闲时流量
//
//                    break;
//                } else {
//                    //普通流量
//                    trafficHandler.reduceTrafficBytes(packageBean.id, currentTimeMillis);
//                    break;
//                }
//            } else {
//                //异常，不管
//            }
//        }

    }

    public static long getTimesMorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

}
