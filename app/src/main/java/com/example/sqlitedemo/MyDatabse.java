package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class MyDatabse extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="student.db";
    private static final String TABLE_NAME="student";
    private static final String COL_1="ID";
    private static final String COL_2="FIRST_NAME";
    private static final String COL_3="LAST_NAME";
    private static final String COL_4="ROLL_NO";


    public MyDatabse(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+TABLE_NAME+" "+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRST_NAME TEXT,LAST_NAME TEXT,ROLL_NO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String nem,String last_nem,String roll)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,nem);
        contentValues.put(COL_3,last_nem);
        contentValues.put(COL_4,roll);

       long data= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(data==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result=database.rawQuery("select * from "+ TABLE_NAME,null);
        return result;
    }

    public boolean isUpDate(String id,String nem,String last_nem,String roll)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,nem);
        contentValues.put(COL_3,last_nem);
        contentValues.put(COL_4,roll);

        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return  true;
    }


    public boolean isDeleteData(String id,String nem,String last_nem,String roll)
    {

            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
            sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{id});


        return true;
    }
}
