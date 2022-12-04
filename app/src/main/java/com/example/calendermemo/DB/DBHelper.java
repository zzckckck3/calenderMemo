package com.example.calendermemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_Version = 1;
    private static final String DB_Name = "Schedule.db";


    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이터 베이스 호출 시 생성
        // id, title, start_date, finish_date, start_time, finish_time, location /  + alarm, repeat(일단 보류)
        db.execSQL("CREATE TABLE IF NOT EXISTS ScheduleList(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, start_date TEXT NOT NULL,finish_date TEXT NOT NULL,start_time TEXT,finish_time TEXT, location TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
