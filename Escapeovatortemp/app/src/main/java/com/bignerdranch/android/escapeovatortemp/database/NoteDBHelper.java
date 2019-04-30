package com.bignerdranch.android.escapeovatortemp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema.NoteTable;

public class NoteDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteDB.db";


    public NoteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NoteTable.NAME + "( _id integer primary key autoincrement, " +
                NoteTable.Cols.UUID + ", " + NoteTable.Cols.FLOOR_NUMBER + ", " + NoteTable.Cols.NOTE_TEXT
                + ", " + NoteTable.Cols.EDITABLE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

