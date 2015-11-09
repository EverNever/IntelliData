package com.uestc.znll.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.uestc.znll.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2015/11/9.
 */
public class MainActivityListViewItemClickMenuDialog extends Dialog {
    private TextView textviewDelete;
    private TextView textviewEdit;
    private Context m_context;

    public MainActivityListViewItemClickMenuDialog(Context context) {
        super(context);
        m_context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.dialog_main_listviewitem_longclick, null);
        setContentView(view);
        findWidgets();
        setLinstener();
    }

    void findWidgets(){
        textviewDelete = (TextView)findViewById(R.id.main_listviewitem_dialog_delete);
        textviewEdit = (TextView)findViewById(R.id.main_listviewitem_dialog_edit);
    }
    void setLinstener(){
        textviewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(m_context);
                builder.setMessage("是否要删除？");
                builder.setPositiveButton("确认", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 将该条item从数据库中删除
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();

            }
        });
        textviewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
