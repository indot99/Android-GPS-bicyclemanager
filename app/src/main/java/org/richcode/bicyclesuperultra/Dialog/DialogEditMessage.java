package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.richcode.bicyclesuperultra.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogEditMessage extends Dialog implements View.OnClickListener {
    private Context context;

    Button OkButton;
    Button CancelButton;
    EditText EditMemo;

    String date;

    public DialogEditMessage(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_editmessage);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date CurrentTIme = new Date();
        date = simpleDateFormat.format(CurrentTIme);

        EditMemo = (EditText)findViewById(R.id.dialog_record_content);
        OkButton = (Button)findViewById(R.id.button_record_complete);
        CancelButton = (Button)findViewById(R.id.button_editmemo_cancel);
        OkButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_editmemo_cancel:
                dismiss();
                break;
            case R.id.button_record_complete:
                dismiss();
                break;
        }
    }

}
