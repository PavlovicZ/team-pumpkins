package com.bignerdranch.android.escapeovatortemp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.UUID;

/**
 * The Notes in the notepad
 * Anthony Hessler
 */
public class Note {
    public static final Bitmap sDefaultImage = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_notebook);

    private UUID mId;   // An ID for the note
    private int mFloorNum;  // The floor the note was taken on
    private String mNoteText;   // The text of the note
    private boolean mEditable;  // Whether the note can be edited by the user
    private Bitmap mImage;  // An image associated with the note

    // SQLite constructor
    public Note(UUID id, int floorNum, String noteText, int isEditable, byte[] image)
    {
        mId = id;
        mFloorNum = floorNum;
        mNoteText = noteText;
        mEditable = isEditable != 0;
        mImage = Notepad.getImage(image);
    }

    // Constructor for creating player-generated notes
    public Note (int floorNum, String noteText, boolean isEditable) {

        mId = UUID.randomUUID();
        this.mFloorNum = floorNum;
        this.mNoteText = noteText;
        this.mEditable = isEditable;
        // Set default image
        mImage = sDefaultImage;
    }

    // Constructor for creating auto-generated notes
    public Note (UUID id, int floorNum, String noteText, boolean isEditable, Bitmap image)
    {
        mId = id;
        mFloorNum = floorNum;
        mNoteText = noteText;
        mEditable = isEditable;
        mImage = image;
    }

    // Getters and setters

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