package com.hnincherry.fingerprintapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseOpenHelper extends SQLiteOpenHelper {


    private static final String db_name = "note.db";
    private static final String tb_name = "note_table";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NOTE";
    private static final String COL_3 = "Date";

    public DatabaseOpenHelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tb_name + " (ID Integer Primary Key Autoincrement,NOTE TEXT,Date date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + tb_name);
    }

    public boolean insertData(String note,Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,note);
        cv.put(COL_3,memo.getDate());

        long result = db.insert(tb_name,null,cv);
        db.close();

        if(result == -1) {
            return false;
        }else {
            return true;
        }
    }

    public boolean updateData(String id,String note,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_2,note);
        cv.put(COL_3,date);

        int result = db.update(tb_name, cv, "ID = ?", new String[]{id});
        if(result > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(tb_name, "ID = ?", new String[]{id});
        return i;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + tb_name,null);
        return res;
    }


}
