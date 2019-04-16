package com.bignerdranch.android.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class NoteActivity extends SingleFragmentActivity {
    private static final String NOTE_ID =
            "com.bignerdranch.android.finalproject.note_id";

    public static Intent newIntent(Context pkgContext, UUID crimeId) {
        Intent intent = new Intent(pkgContext, NoteActivity.class);
        intent.putExtra(NOTE_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID noteId = (UUID) getIntent().getSerializableExtra(NOTE_ID);

        return NoteFragment.newInstance(noteId);
    }
}