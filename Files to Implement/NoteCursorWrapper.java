package com.bignerdranch.android.finalproject.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.finalproject.Note;
import com.bignerdranch.android.finalproject.database.NoteDBSchema.NoteTable;

import java.util.UUID;

public class NoteCursorWrapper extends CursorWrapper
{
    public NoteCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public Note getNote()
    {
        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        int floorNumber = getInt(getColumnIndex(NoteTable.Cols.FLOOR_NUMBER));
        String noteText = getString(getColumnIndex(NoteTable.Cols.NOTE_TEXT));
        int isEditable = getInt(getColumnIndex(NoteTable.Cols.EDITABLE));

        return new Note(UUID.fromString(uuidString), floorNumber, noteText, isEditable);
    }
}
