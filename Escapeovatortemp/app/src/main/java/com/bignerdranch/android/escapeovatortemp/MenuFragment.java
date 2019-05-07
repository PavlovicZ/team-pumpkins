package com.bignerdranch.android.escapeovatortemp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


public class MenuFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private NoteAdapter mAdapter;
    private Button mNewNoteButton;
    private int mFloorNum;
    private UUID mDummyID = UUID.randomUUID();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewNoteButton = (Button) view.findViewById(R.id.new_note_button);
        mNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new note
                Intent intent = NoteActivity.newIntent(getActivity(), mDummyID, mFloorNum);
                startActivity(intent);
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

    private void updateUI()
    {
        Notepad notepad = Notepad.get(getActivity());
        List<Note> notes = notepad.getNotes();

        if(mAdapter == null)
        {
            mAdapter = new NoteAdapter(notes);
            mRecyclerView.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.setNotes(notes);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFloorTextView;
        private TextView mNoteTextView;
        private Note mNote;

        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_note, parent, false));
            itemView.setOnClickListener(this);
            mFloorTextView = (TextView) itemView.findViewById(R.id.floor_number);
            mNoteTextView = (TextView) itemView.findViewById(R.id.note_text);
        }


        //use this to launch into the note editor activity
        @Override
        public void onClick(View v) {
            Intent intent = NoteActivity.newIntent(getActivity(), mNote.getId(), mFloorNum);
            startActivity(intent);
        }

        public void bind(Note note)
        {
            mNote = note;
            mFloorTextView.setText("Floor " + mNote.getFloorNum());
            mNoteTextView.setText(mNote.getNoteText());
        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>
    {
        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes)
        {
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new NoteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(NoteHolder noteHolder, int position)
        {
            Note note = mNotes.get(position);
            noteHolder.bind(note);
        }

        @Override
        public int getItemCount()
        {
            return mNotes.size();
        }

        public void setNotes(List<Note> notes)
        {
            mNotes = notes;
        }
    }
}
