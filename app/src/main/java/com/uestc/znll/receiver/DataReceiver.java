package com.uestc.znll.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;

import java.util.Calendar;
import java.util.List;

/**
 * Created by chao on 2015/11/4.
 */
public class DataReceiver extends BroadcastReceiver {

    private TrafficHandler trafficHandler;

    public DataReceiver() {
        super();
        //TODO trafficHandler的构造
//        trafficHandler = new DBAdapter();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        long currentTimeMillis = System.currentTimeMillis();
        long currentBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
//        long lastBytes = trafficHandler.getAllTrafficBytes(currentTimeMillis);
//        long trafficAmount = currentBytes - lastBytes;

        //TODO 判断要减哪个流量包的流量
//        for (PackageBean packageBean : trafficHandler.getPackageList()) {
//            if(!packageBean.isDaily && !packageBean.isMonthly){
//                //节假日包
//                if(packageBean.beginTimeMillis < currentTimeMillis && packageBean.endTimeMillis > currentTimeMillis){
//                    trafficHandler.reduceTrafficBytes(packageBean.id, trafficAmount);
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

    public interface TrafficHandler {
        /**
         *
         * @return
         */
        List<PackageBean> getPackageList();

        /**
         * 减少流量包的剩余流量
         *
         * @param packageId     流量包的ID
         * @param trafficAmount 减少的值，单位byte
         */
        void reduceTrafficBytes(String packageId, long trafficAmount);

        /**
         * 通过时间戳获取某月使用的全部流量，单位byte
         * 注意是全部流量，不分闲时和普通
         *
         * @param timeMillis 时间戳
         * @return 某月的全部流量
         */
        long getAllTrafficBytes(long timeMillis);
    }
}
