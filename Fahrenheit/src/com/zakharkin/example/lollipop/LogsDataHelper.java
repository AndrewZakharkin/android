package com.zakharkin.example.lollipop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by andre on 22.11.2015.
 */
public class LogsDataHelper extends SQLiteOpenHelper {

    public LogsDataHelper(Context context) {
        super(context, "FahrenheitDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("LogsDataHelper", "onCreate called");
        try {
            db.execSQL("CREATE TABLE LOGDATA(id INTEGER PRIMARY KEY, logtext TEXT, created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
            ContentValues cv = new ContentValues(1);
            cv.put("logtext", "InitData");
            db.insert("LOGDATA", "", cv);
        }
        catch (Throwable e){
            Log.e("LogsDataHelper", e.toString() + e.getMessage());
            throw e;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w("LogsDataHelper", "onUpdate called");
    }

    public void logText(String text){
        ContentValues cv = new ContentValues(1);
        cv.put("logtext", text);
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.insert("LOGDATA", "", cv);
        }finally {
            if(db != null){
                db.close();
            }
        }
    }

    public void deleteAll(){
        SQLiteDatabase db = null;
        try{
            db = getWritableDatabase();
            db.delete("LOGDATA", "", null);
            ContentValues cv = new ContentValues(1);
            cv.put("logtext", "InitData");
            db.insert("LOGDATA", "", cv);
        } finally {
            if(db != null){
                db.close();
            }
        }
    }

    public Cursor GetData(SQLiteDatabase db) {
        return db.rawQuery("SELECT created_at, logtext from LOGDATA", null);
    }

    public String GetString(Cursor c) {
        return c.getString(0) + " : " + c.getString(1);
    }
}
