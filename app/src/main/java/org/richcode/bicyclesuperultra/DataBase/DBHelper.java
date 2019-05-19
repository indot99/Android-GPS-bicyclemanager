package org.richcode.bicyclesuperultra.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.AlphabeticIndex;
import android.util.Log;

import org.richcode.bicyclesuperultra.DataClass.DataMember;
import org.richcode.bicyclesuperultra.DataClass.DataRecord;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;
    String tag = "SQLite";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ID,D-Day,과목,내용 -> 과제
        //ID,날짜,메모내용 -> 메모장

        db.execSQL("CREATE TABLE MemberInfo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT)");
        db.execSQL("CREATE TABLE RecordInfo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, kg TEXT, kcal TEXT, time TEXT, distance TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void RecordInsert(String date, String kg, String kcal, String time,String distance){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO RecordInfo VALUES(null, '" + date + "', '" + kg + "', '" + kcal + "', '" + time + "', '"+distance+"');");
    }
    public void RecordDelete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM RecordInfo WHERE _id="+id+";");
    }
    public ArrayList<DataRecord> getResultRecordData(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DataRecord> RecordList = new ArrayList<DataRecord>();

        int id;
        String date;
        String kg;
        String kcal;
        String time;
        String distance;

        Cursor cursor = db.rawQuery("SELECT * FROM RecordInfo ORDER BY _id DESC;",null);

        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            date = cursor.getString(1);
            kg = cursor.getString(2);
            kcal = cursor.getString(3);
            time = cursor.getString(4);
            distance = cursor.getString(5);

            RecordList.add(new DataRecord(id,date,kg,kcal,time,distance));
        }


        return RecordList;
    }


    public void MemberInsert(String name, String password){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MemberInfo VALUES(null, '" + name + "', '" + password + "');");
    }
    public void MemberDelete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MemberInfo WHERE _id="+id+";");
    }
    public void UpdateMember(String Name, String Password, int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE MemberInfo SET name='"+Name+"', password='"+Password+"' WHERE _id="+id+";");
    }

    public ArrayList<DataMember> getResultMemberData(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<DataMember> MemberList = new ArrayList<DataMember>();

        int Id;
        String Name;
        String Password;

        Cursor cursor = db.rawQuery("SELECT * FROM MemberInfo;",null);
        while (cursor.moveToNext()){
            Id = cursor.getInt(0);
            Name = cursor.getString(1);
            Password = cursor.getString(2);

            Log.d("회원정보 : ",Id+" : "+Name+" : "+Password);

            MemberList.add(new DataMember(Id,Name,Password));
        }

        return MemberList;
    }

}


