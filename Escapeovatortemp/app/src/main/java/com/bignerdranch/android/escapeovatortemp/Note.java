package com.bignerdranch.android.escapeovatortemp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.UUID;

public class Note {
    public static final Bitmap sDefaultImage = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_notebook);

    private UUID mId;
    private int mFloorNum;
    private String mNoteText;
    private boolean mEditable;
    private Bitmap mImage;

    //use this constructor for player-generated notes
    // This appears to go unused; we will probably end up deleting it
    public Note(int floorNum, String noteText) {

        mId = UUID.randomUUID();
        this.mFloorNum = floorNum;
        this.mNoteText = noteText;
        mEditable = true;

    }

    //SQLite constructor
    public Note(UUID id, int floorNum, String noteText, int isEditable, byte[] image)
    {
        mId = id;
        mFloorNum = floorNum;
        mNoteText = noteText;
        mEditable = isEditable != 0;
        mImage = Notepad.getImage(image);
    }

    // This is currently in use for just player-generated notes and will probably stay that way
    public Note (int floorNum, String noteText, boolean isEditable) {

        mId = UUID.randomUUID();
        this.mFloorNum = floorNum;
        this.mNoteText = noteText;
        this.mEditable = isEditable;
        // Set default image
        mImage = sDefaultImage;
    }

    //use this constructor for game generated notes, makes it so the player can't edit these
    public Note (UUID id, int floorNum, String noteText, boolean isEditable, Bitmap image)
    {
        mId = id;
        mFloorNum = floorNum;
        mNoteText = noteText;
        mEditable = isEditable;
        mImage = image;
    }

    //empty constructor - also appears to go unused
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

    public void setImage(Bitmap image)
    {
        mImage = image;
    }

    public Bitmap getImage()
    {
        return mImage;
    }
}