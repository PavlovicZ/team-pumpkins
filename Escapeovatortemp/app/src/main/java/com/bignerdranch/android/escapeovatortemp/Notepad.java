package com.bignerdranch.android.escapeovatortemp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bignerdranch.android.escapeovatortemp.database.NoteCursorWrapper;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBHelper;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema;
import com.bignerdranch.android.escapeovatortemp.database.NoteDBSchema.NoteTable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Interacts with the SQLite database holding the notes
 * Anthony Hessler
 */
public class Notepad
{
    private static Notepad sNotepad;

    private Context mContext;           // The context of the activity opening the notepad
    private SQLiteDatabase mDatabase;   // The SQLite Database

    // Returns a new Notepad if none exists, otherwise retuns the one that does exist
    public static Notepad get(Context context)
    {
        if(sNotepad == null)
            sNotepad = new Notepad(context);
        return sNotepad;
    }

    // Constructor
    private Notepad(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new NoteDBHelper(mContext).getWritableDatabase();
    }

    // Adds a note to the database
    public void addNote(Note n)
    {
        mDatabase.insert(NoteTable.NAME, null, getContentValues(n));
    }

    // Gets the list of all notes
    public List<Note> getNotes()
    {
        List<Note> notes = new ArrayList<>();

        NoteCursorWrapper cursor = queryNotes(null, null);

        try
        {
            // Read through the whole database, retrieving each note
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

    // Retrieves a note from the database, based on UUID
    public Note getNote(UUID id)
    {
        NoteCursorWrapper cursor = queryNotes(NoteTable.Cols.UUID + " = ?", new String[] { id.toString()});

        try
        {
            // Reads through the database, looking for the particular note
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

    // Updates the data for a note in the database
    public void updateNote(Note n)
    {
        String uuidString = n.getId().toString();
        ContentValues values = getContentValues(n);

        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    // Gets the ContentValues for a note so it can go into the database
    private static ContentValues getContentValues(Note note)
    {
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteTable.Cols.FLOOR_NUMBER, note.getFloorNum());
        values.put(NoteTable.Cols.NOTE_TEXT, note.getNoteText());
        values.put(NoteTable.Cols.EDITABLE, note.isEditable() ? 1 : 0);
        values.put(NoteTable.Cols.IMAGE, getBytes(note.getImage()));

        return values;
    }

    // Queries the notes in the database
    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(NoteTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new NoteCursorWrapper(cursor);
    }

    // Converts from bitmap to byte array so the image can be stored in the SQLite database
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try
        {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            stream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    // Convert from byte array to bitmap so the image can be retrieved from the database
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
