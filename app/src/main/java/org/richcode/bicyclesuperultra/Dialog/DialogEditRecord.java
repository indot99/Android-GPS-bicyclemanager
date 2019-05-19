package org.richcode.bicyclesuperultra.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogEditRecord extends Dialog implements View.OnClickListener {
    private Context context;

    Button OkButton;
    Button CancelButton;
    EditText EditKg;

    String date;
    double kcal=0;
    int time;
    double distance;

    DBHelper dbHelper;


    public DialogEditRecord(@NonNull Context context,int time, double distance) {
        super(context);
        this.context = context;
        this.time = time;
        this.distance = distance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_editkg);

        dbHelper = new DBHelper(getContext(),"RecordDataBase.db",null,2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date CurrentTIme = new Date();
        date = simpleDateFormat.format(CurrentTIme);

        EditKg = (EditText)findViewById(R.id.edit_kg_content);
        OkButton = (Button)findViewById(R.id.button_editkg_complete);
        CancelButton = (Button)findViewById(R.id.button_editkg_cancel);
        OkButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);

        int minutes = time/60;
        int seconds = time%60;
        String Textcalorie = String.format("%.2f", kcal)+"cal";
        String TextTime = Integer.toString(minutes)+"분 "+Integer.toString(seconds)+"초";
        String TextDistance = String.format("%.2f",distance)+"m";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_editkg_cancel:
                dismiss();
                break;
            case R.id.button_editkg_complete:
                kcal = 0.113 * time * Double.valueOf(EditKg.getText().toString());
                int minutes = time/60;
                int seconds = time%60;

                String Textkg = EditKg.getText().toString()+"kg";
                String Textcalorie = String.format("%.2f", kcal)+"cal";
                String TextTime = Integer.toString(minutes)+"분 "+Integer.toString(seconds)+"초";
                String TextDistance = String.format("%.2f",distance)+"m";

                dbHelper.RecordInsert(date,Textkg,Textcalorie,TextTime,TextDistance);

                dismiss();
                break;
        }
    }

}
