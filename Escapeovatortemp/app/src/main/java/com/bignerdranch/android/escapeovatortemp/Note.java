package com.bignerdranch.android.escapeovatortemp;

import java.util.UUID;

public class Note {

    private UUID mId;
    private int mFloorNum;
    private String mNoteText;
    private boolean mEditable;

    //use this constructor for player-generated notes
    public Note(int floorNum, String noteText) {

        mId = UUID.randomUUID();
        this.mFloorNum = floorNum;
        this.mNoteText = noteText;
        mEditable = true;

    }

    //SQLite constructor
    public Note(UUID id, int floorNum, String noteText, int isEditable)
    {
        mId = id;
        mFloorNum = floorNum;
        mNoteText = noteText;
        mEditable = isEditable != 0;
    }

    //use this constructor for game generated notes, makes it so the player can't edit these
    public Note (int floorNum, String noteText, boolean isEditable) {

        mId = UUID.randomUUID();
        this.mFloorNum = floorNum;
        this.mNoteText = noteText;
        this.mEditable = isEditable;
    }

    //empty constructor
    public Note() {
        mId = UUID.randomUUID();
        mFloorNum = 0;
        mNoteText = "";
        mEditable = true;
    }

    public int getFloorNum() {
        return mFloorNum;
    }

    public void setFloorNum(int floorNum) {
        this.mFloorNum = floorNum;
    }

    public String getNoteText() {
        return mNoteText;
    }

    public void setNoteText(String noteText) {
        this.mNoteText = noteText;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public boolean isEditable()
    {
        return mEditable;
    }
}