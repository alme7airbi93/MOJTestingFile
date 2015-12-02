package com.mohammad.mojapplication.MOJdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mohammad.mojapplication.MOJdatabase.MOJDbSchema.EmiratesIDTable;
import com.mohammad.mojapplication.MOJdatabase.MOJDbSchema.UserTable;
import com.mohammad.mojapplication.MOJdatabase.MOJDbSchema.UserTable.Cols;


/**
 * Created by user on 10/28/2015.
 */
public class MOJDbHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "moj.db";

    public MOJDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ UserTable.NAME+"(_id integer primary key autoincrement,"+
                Cols.ID+ ","  +
                Cols.NAME + ","+
                Cols.MOBILE+","+
                Cols.ADDRESS + ","+
                Cols.USER_NAME+","+
                Cols.PASS+","+
                Cols.SERVICEPASS +")");

        db.execSQL("create table "+ EmiratesIDTable.NAME+"(_id integer primary key autoincrement,"+
                EmiratesIDTable.Cols.ID+ ","  +
                EmiratesIDTable.Cols.NAME + ","+
                EmiratesIDTable.Cols.MOBILE+","+
                EmiratesIDTable.Cols.ADDRESS + ","+
                EmiratesIDTable.Cols.DOB +")");


        db.execSQL("create table "+ MOJDbSchema.ServiceTable.NAME+"(_id integer primary key autoincrement,"+
                MOJDbSchema.ServiceTable.Cols.USERID + ","  +
                MOJDbSchema.ServiceTable.Cols.TYPE + ","  +
                MOJDbSchema.ServiceTable.Cols.SERVICEID + ","+
                MOJDbSchema.ServiceTable.Cols.DATE + ","+
                MOJDbSchema.ServiceTable.Cols.SERVICESTATUS + ","+
                MOJDbSchema.ServiceTable.Cols.PARTYID1 + ","+
                MOJDbSchema.ServiceTable.Cols.PARTYID2 + ","+
                MOJDbSchema.ServiceTable.Cols.LOCATION +")");


        db.execSQL("create table "+ MOJDbSchema.PartyTable.NAME+"(_id integer primary key autoincrement,"+
                MOJDbSchema.PartyTable.Cols.PARTYID+ ","  +
                MOJDbSchema.PartyTable.Cols.FNAME + ","+
                MOJDbSchema.PartyTable.Cols.TYPE+","+
                MOJDbSchema.PartyTable.Cols.MOBILE+","+
                MOJDbSchema.PartyTable.Cols.ADDRESS+","+
                MOJDbSchema.PartyTable.Cols.IMAGE1 +")");
    }









    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
