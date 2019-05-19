package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.DataClass.DataRecord;
import org.richcode.bicyclesuperultra.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogContentRecord extends Dialog implements View.OnClickListener {
    private Context context;

    private Button CompleteButton;
    private TextView ContentText;
    private DataRecord dataRecord;


    public DialogContentRecord(@NonNull Context context, DataRecord dataRecord) {
        super(context);
        this.context = context;
        this.dataRecord = dataRecord;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_showmessage);

        CompleteButton = (Button)findViewById(R.id.dialog_record_complete);
        ContentText = (TextView)findViewById(R.id.dialog_record_content);

        CompleteButton.setOnClickListener(this);

        ContentText.setText(
                  "날짜        : " + dataRecord.getDate() + "\n"
                + "운동시간 : " + dataRecord.getTime() + "\n"
                + "운동거리 : " + dataRecord.getDistance() + "\n"
                + "몸무게     : " + dataRecord.getKg() + "\n"
                + "칼로리량 : " + dataRecord.getKcal()
        );


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_record_complete:
                dismiss();
                break;
        }
    }

}
