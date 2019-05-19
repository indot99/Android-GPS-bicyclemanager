package org.richcode.bicyclesuperultra.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.richcode.bicyclesuperultra.DataBase.DBHelper;
import org.richcode.bicyclesuperultra.DataClass.DataRecord;
import org.richcode.bicyclesuperultra.Dialog.DialogContentRecord;
import org.richcode.bicyclesuperultra.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {


    private ArrayList<DataRecord> list = new ArrayList<DataRecord>();
    private Context context;
    private DBHelper dbHelper;
    private Activity activity;

    public RecordAdapter(ArrayList<DataRecord>list, Context context, Activity activity){
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull

    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record,viewGroup,false);

        this.dbHelper = new DBHelper(context,"RecordDataBase.db",null,2);

        RecordAdapter.ViewHolder vh = new RecordAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecordAdapter.ViewHolder holder, int position) {

        final int pos = position;

        holder.TextTime.setText(list.get(pos).getTime());
        holder.TextDate.setText(list.get(pos).getDate());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogContentRecord dialogContentRecord = new DialogContentRecord(context,list.get(pos));
                dialogContentRecord.setCanceledOnTouchOutside(false);
                dialogContentRecord.show();
            }
        });
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Vibrator vibrator = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(50);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("삭제 하시겠습니까?");

                alertDialogBuilder
                        .setMessage("삭제할까요?")
                        .setCancelable(false)
                        .setPositiveButton("삭제",
                                new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dbHelper.RecordDelete(list.get(pos).getId());
                                        list.remove(pos);
                                        notifyDataSetChanged();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return false;
            }
        });

    }

//    int id;
//    String date;
//    String kg;
//    String kcal;
//    String time;

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextTime;
        TextView TextDate;
        LinearLayout item;

        public ViewHolder(View view) {
            super(view);

            TextTime = (TextView)view.findViewById(R.id.item_record_time);
            TextDate = (TextView)view.findViewById(R.id.item_record_date);
            item = (LinearLayout)view.findViewById(R.id.item_record_to_view);

        }
    }

}
