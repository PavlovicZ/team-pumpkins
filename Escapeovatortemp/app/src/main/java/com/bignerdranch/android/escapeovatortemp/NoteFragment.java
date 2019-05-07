package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Fragment for editing a note
 * Anthony Hessler
 */
public class NoteFragment extends Fragment {
    // Strings to pass Note ID and floor number into Intents
    private static final String ARG_NOTE_ID = "note_ID";
    private static final String ARG_FLOOR_NUM = "floor_num";

    // Request code for setting the image
    private static final int REQUEST_IMAGE = 0;

    private Note mNote;         // The note being edited
    private EditText mFloorNumberET;    // Displays the floor number
    private EditText mNoteET;           // Displays the text of the note
    private ImageButton mNoteImage;     // Displays the image of the note (Does not work, but we're keeping it in to show that we tried)
    private int mFloorNum;      // The floor number

    public NoteFragment() {
        // Required empty public constructor
    }

    // Creates a new instance of NoteFragment
    public static NoteFragment newInstance(UUID noteID, int floorNum) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteID);
        args.putInt(ARG_FLOOR_NUM, floorNum);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // Code that runs when the Fragment is created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        mFloorNum = getArguments().getInt(ARG_FLOOR_NUM);

        // Check the database for the note
        mNote = Notepad.get(getActivity()).getNote(noteId);
        if(mNote == null)
        {
            // If the note is in the auto-generated note list, retrieve it
            mNote = Floor1Activity.getNote(noteId);
            if(mNote == null)
            {
                // The note is a new note, so generate it now
                mNote = new Note(mFloorNum, "Player-generated note", true);

            }

            // Add the note to the database
            Notepad.get(getActivity()).addNote(mNote);
        }
    }

    // Code to set up how the Fragment looks
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        // Sets the EditText to display the floor number and not be editable
        // This is never editable, but it is still an EditText because that looks nicer than just a TextView
        mFloorNumberET = (EditText) v.findViewById(R.id.floor_number);
        mFloorNumberET.setText("" + mNote.getFloorNum());
        mFloorNumberET.setEnabled(false);

        // Sets the EditText to display the Note Text
        mNoteET = (EditText) v.findViewById(R.id.note_textbox);
        mNoteET.setText(mNote.getNoteText());
        mNoteET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mNote.setNoteText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mNoteET.setEnabled(mNote.isEditable());     // Sets it so you can't edit game-generated notes

        // Sets the note image button to allow the user to select a photo from the gallery
        // Unfortunately, due to the issues with setting the image button we never got to test this
        mNoteImage = (ImageButton) v.findViewById(R.id.note_image);
        mNoteImage.setImageBitmap(mNote.getImage());
        mNoteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open photos app
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker, REQUEST_IMAGE);
            }
        });

        return v;
    }

    // Updates the note when the Fragment is paused
    @Override
    public void onPause()
    {
        super.onPause();

        Notepad.get(getActivity()).updateNote(mNote);
    }

    // Sets the image based on which image was chosen from the photo gallery, if any at all
    // Again, we unfortunately could never test this as the image button does not appear at all.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
            return;

        if(requestCode == REQUEST_IMAGE && data != null)
        {
            try
            {
                Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap image = BitmapFactory.decodeStream(imageStream);
                mNote.setImage(image);
                mNoteImage.setImageBitmap(image);
            }
            catch(FileNotFoundException e)
            {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }
}