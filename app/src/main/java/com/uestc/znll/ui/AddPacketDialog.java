package com.uestc.znll.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.uestc.znll.R;

/**
 * Created by Administrator on 2015/11/5.
 */
public class AddPacketDialog extends Dialog {

    private Context m_context;
    private EditText editText;
    private Spinner spinner;

    public AddPacketDialog(Context context) {
        super(context);
        m_context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
        View view = inflater.inflate(R.layout.addpacket_dialog,null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        findWidget();
        setListener();
    }

    private void findWidget()
    {
        editText = (EditText)findViewById(R.id.addpacket_dialog_edittext);
        spinner = (Spinner)findViewById(R.id.addpacket_dialog_sppiner);
    }
    private void setListener()
    {
    }
}
