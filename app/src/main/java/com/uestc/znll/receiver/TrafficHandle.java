package com.uestc.znll.receiver;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.ListPopupWindow;

import com.uestc.znll.SQLConnection.DataPakBean;
import com.uestc.znll.SQLConnection.FolderViewDataBean;
import com.uestc.znll.SQLConnection.SQLConnection;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ranweizhi on 15/11/8.
 */
public class TrafficHandle {
    private static SQLConnection sqlConnection;

    public static void initSQLConnection(Context context)
    {
        sqlConnection = new SQLConnection(context);
        try {
            sqlConnection.createDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static List<DataPakBean> getDataPakInMonth(Integer Year, Integer Month) {
        return sqlConnection.getAllDataPaksInOneMonth(Year, Month);
    }

    public static List<FolderViewDataBean> getAllFoldersData () {
        return sqlConnection.getAllFolderViewData();
    }

    public static void reduceData (String DataPakId, Long DataPakUsed) {
        sqlConnection.updateDataPak(DataPakId, DataPakUsed.toString());
    }


}
