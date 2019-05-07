package com.bignerdranch.android.escapeovatortemp.database;

/**
 * Sets up the String constants for use in the database
 * Anthony Hessler
 */
public class NoteDBSchema
{
    public static final class NoteTable
    {
        public static final String NAME = "notes";

        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String FLOOR_NUMBER = "floor";
            public static final String NOTE_TEXT = "note";
            public static final String EDITABLE = "editable";
            public static final String IMAGE = "image";
        }
    }
}
