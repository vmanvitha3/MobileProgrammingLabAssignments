package com.example.prasanna.speechtotext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBClass extends SQLiteOpenHelper {

    public static final String dbName = "Records.db";
    public static final String tableName = "Topics";
    public static final String columnId = "id";
    public static final String columnName = "name";
    public static final String columnQuestion = "question";

    public DBClass(Context context)
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Topics " + "(id integer primary key, name text, question text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists data");
    }

    public void Insert(String name, String question)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("question", question);
        db.insert("Topics", null, contentValues);
    }

    public Cursor getData(String questionName)
    {
        //SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor res = db.rawQuery( "select * from Topics where name="+questionName+"", null );
        Cursor res = db.query(tableName, null, "name=?", new String[]{questionName},null,null,null);
        return res;
    }
}
