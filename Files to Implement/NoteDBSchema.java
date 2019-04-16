package com.bignerdranch.android.finalproject.database;

public class NoteDBSchema
{
    public static final class NoteTable
    {
        public static final String NAME = "notes";

        public static final class Cols
        {
            public static final String UUID = "uuid";
            public static final String FLOOR_NUMBER = "floor number";
            public static final String NOTE_TEXT = "note text";
            public static final String EDITABLE = "editable";
        }
    }
}
