package org.richcode.bicyclesuperultra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.richcode.bicyclesuperultra.Adapter.RecordAdapter;
import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.DataClass.DataRecord;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<DataRecord> RecordDataArrayList;
    ArrayList<DataRecord> CopyDataArrayList;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        dbHelper = new DBHelper(getApplicationContext(),"RecordDataBase.db",null,2);

        CopyDataArrayList = dbHelper.getResultRecordData();
        RecordDataArrayList = new ArrayList<DataRecord>();

        recyclerView = (RecyclerView)findViewById(R.id.record_listview);

        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        RecordDataArrayList = new ArrayList<DataRecord>();
        adapter = new RecordAdapter(RecordDataArrayList,this,this);
        recyclerView.setAdapter(adapter);

        for(int i = 0; i<CopyDataArrayList.size(); i++){
            RecordDataArrayList.add(CopyDataArrayList.get(i));
        }
        adapter.notifyDataSetChanged();

    }
}
