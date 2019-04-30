package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;


public class NoteFragment extends Fragment {
    private static final String ARG_NOTE_ID = "note_ID";
    private static final String ARG_FLOOR_NUM = "floor_num";

    private Note mNote;
    private EditText mFloorNumberET;
    private EditText mNoteET;
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
        mNote = Notepad.get(getActivity()).getNote(noteId);
        if(mNote == null)
        {
            mNote = new Note(mFloorNum, "Player-generated note", true);
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

        mNoteET = (EditText) v.findViewById((R.id.note_textbox));
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

        return v;
    }

    @Override
    public void onPause()
    {
        super.onPause();

        Notepad.get(getActivity()).updateNote(mNote);
    }
}