package com.example.calendermemo.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.calendermemo.model.ScheduleData;

import java.util.ArrayList;


public class DBLoader {
    private Context context;
    private DBHelper db;

    public DBLoader(Context context){
        this.context = context;
        db = new DBHelper(context);
    }

    public void save(String title, String start_date, String finish_date,String start_time,String finish_time,String location){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("start_date",start_date);
        contentValues.put("finish_date",finish_date);
        contentValues.put("start_time",start_time);
        contentValues.put("finish_time",finish_time);
        contentValues.put("location", location);
        db.getWritableDatabase().insert("ScheduleList",null,contentValues);
        db.close();
        Toast.makeText(context, "일정 저장", Toast.LENGTH_SHORT).show();
    }
    //데이터 수정
    public void updateData(ScheduleData data){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",data.getTitle());
        contentValues.put("start_date",data.getStart_date());
        contentValues.put("finish_date",data.getFinish_date());
        contentValues.put("start_time",data.getStart_time());
        contentValues.put("finish_time",data.getFinish_time());
        contentValues.put("location", data.getLocation());
        /*String query = "UPDATE ScheduleList" +
                "SET "+"title ='"+data.getTitle()+"'"+"start_date ='"+data.getStart_date()+"'"+
                "finish_date ='"+data.getFinish_date()+"'"+"start_time ='"+data.getStart_time()+"'"+
                "finish_time ='"+data.getFinish_time()+"'"+"location ='"+data.getTitle()+"'"+
                "WHERE id="+data.getId();*/
        db.getWritableDatabase().update("ScheduleList",contentValues,"id = ?", new String[]{Integer.toString(data.getId())});
        db.close();
    }

    //데이터 삭제
    public void deleteData(int id){
        db.getWritableDatabase().delete("ScheduleList","id=?", new String[] {Integer.toString(id)});
        db.close();
    }

    public ArrayList<ScheduleData> getScheduleList(String selectDay){
        ArrayList<ScheduleData> scheduleItems = new ArrayList<ScheduleData>();
        Cursor cursor = db.getWritableDatabase().rawQuery("SELECT * FROM ScheduleList Where start_date LIKE '%"+ selectDay +"%'",null);
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String start_date = cursor.getString(cursor.getColumnIndex("start_date"));
                @SuppressLint("Range") String finish_date = cursor.getString(cursor.getColumnIndex("finish_date"));
                @SuppressLint("Range") String start_time = cursor.getString(cursor.getColumnIndex("start_time"));
                @SuppressLint("Range") String finish_time = cursor.getString(cursor.getColumnIndex("finish_time"));
                @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex("location"));

                ScheduleData scheduleItem = new ScheduleData(id,title,start_date,finish_date,start_time,finish_time,location);
                scheduleItems.add(scheduleItem);
            }
        }
        db.close();

        return scheduleItems;
    }
}
