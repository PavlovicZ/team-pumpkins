package com.bignerdranch.android.escapeovatortemp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import com.bignerdranch.android.escapeovatortemp.database.NoteCursorWrapper;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBHelper;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema.NoteTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is pretty much the CrimeLab class from CriminalIntent repurposed to hold Notes
 */
public class Notepad
{
    private static Notepad sNotepad;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static Notepad get(Context context)
    {
        if(sNotepad == null)
            sNotepad = new Notepad(context);
        return sNotepad;
    }

    private Notepad(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new NoteDBHelper(mContext).getWritableDatabase();
    }

    public void addNote(Note n)
    {
        mDatabase.insert(NoteTable.NAME, null, getContentValues(n));
    }

    public List<Note> getNotes()
    {
        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null, null);

        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                notes.add(cursor.getNote());
                cursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }

        return notes;
    }

    public Note getNote(UUID id)
    {
        NoteCursorWrapper cursor = queryNotes(NoteTable.Cols.UUID + " = ?", new String[] { id.toString()});

        try
        {
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getNote();
        }
        finally
        {
            cursor.close();
        }
    }

    public void updateNote(Note n)
    {
        String uuidString = n.getId().toString();
        ContentValues values = getContentValues(n);

        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private static ContentValues getContentValues(Note note)
    {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteTable.Cols.FLOOR_NUMBER, note.getFloorNum());
        values.put(NoteTable.Cols.NOTE_TEXT, note.getNoteText());
        values.put(NoteTable.Cols.EDITABLE, note.isEditable() ? 1 : 0);

        return values;
    }

    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(NoteTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new NoteCursorWrapper(cursor);
    }
}
