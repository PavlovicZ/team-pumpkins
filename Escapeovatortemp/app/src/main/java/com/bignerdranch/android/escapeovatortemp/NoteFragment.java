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


public class NoteFragment extends Fragment {
    private static final String ARG_NOTE_ID = "note_ID";
    private static final String ARG_FLOOR_NUM = "floor_num";
    private static final int REQUEST_IMAGE = 0;

    private Note mNote;
    private EditText mFloorNumberET;
    private EditText mNoteET;
    private ImageButton mNoteImage;
    private int mFloorNum;

    public NoteFragment() {
        // Required empty public constructor
    }


    public static NoteFragment newInstance(UUID noteID, int floorNum) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteID);
        args.putInt(ARG_FLOOR_NUM, floorNum);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        mFloorNumberET = (EditText) v.findViewById(R.id.floor_number);
        mFloorNumberET.setText("" + mNote.getFloorNum());
        mFloorNumberET.setEnabled(false);

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

    @Override
    public void onPause()
    {
        super.onPause();

        Notepad.get(getActivity()).updateNote(mNote);
    }

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