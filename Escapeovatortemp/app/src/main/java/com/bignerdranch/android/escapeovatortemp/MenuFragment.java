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

/**
 * Fragment for displaying the notes
 * Anthony Hessler
 */
public class MenuFragment extends Fragment {

    private RecyclerView mRecyclerView;     // The RecyclerView that displays all the notes
    private NoteAdapter mAdapter;           // NoteAdapter for adapting the notes for the RecyclerView
    private Button mNewNoteButton;          // Adds a new note
    private int mFloorNum;                  // The floor number
    private UUID mDummyID = UUID.randomUUID();  // A random UUID for starting the NoteFragment

    // Code that executes when the Fragment is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Initialize the RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set the New Note Button to open a new NoteFragment when it is clicked
        mNewNoteButton = (Button) view.findViewById(R.id.new_note_button);
        mNewNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new note
                Intent intent = NoteActivity.newIntent(getActivity(), mDummyID, mFloorNum);
                startActivity(intent);
            }
        });

        // Update the UI
        updateUI();

        return view;
    }

    // Updates the UI when the Fragment is resumed
    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

    // Updates the UI with all the notes
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

    // Helper class for the RecyclerView
    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFloorTextView;    // Displays the floor number
        private TextView mNoteTextView;     // Displays the note text
        private Note mNote;                 // The note being displayed

        // Constructor
        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_note, parent, false));
            itemView.setOnClickListener(this);
            mFloorTextView = (TextView) itemView.findViewById(R.id.floor_number);
            mNoteTextView = (TextView) itemView.findViewById(R.id.note_text);
        }


        // Opens a NoteFragment with the note clicked on
        @Override
        public void onClick(View v) {
            Intent intent = NoteActivity.newIntent(getActivity(), mNote.getId(), mFloorNum);
            startActivity(intent);
        }

        // Binds the data from a note to the TextViews
        public void bind(Note note)
        {
            mNote = note;
            mFloorTextView.setText("Floor " + mNote.getFloorNum());
            mNoteTextView.setText(mNote.getNoteText());
        }
    }

    // Another helper class
    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>
    {
        private List<Note> mNotes;      // The list of all the notes

        // Constructor
        public NoteAdapter(List<Note> notes)
        {
            mNotes = notes;
        }

        // Creates a NoteHolder
        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new NoteHolder(layoutInflater, parent);
        }

        // Binds a note
        @Override
        public void onBindViewHolder(NoteHolder noteHolder, int position)
        {
            Note note = mNotes.get(position);
            noteHolder.bind(note);
        }

        // Returns the number of notes
        @Override
        public int getItemCount()
        {
            return mNotes.size();
        }

        // Sets the list of notes
        public void setNotes(List<Note> notes)
        {
            mNotes = notes;
        }
    }
}
