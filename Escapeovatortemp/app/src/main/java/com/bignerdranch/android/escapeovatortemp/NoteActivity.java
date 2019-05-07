package com.bignerdranch.android.escapeovatortemp;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Holds a NoteFragment
 * Anthony Hessler
 */
public class NoteActivity extends SingleFragmentActivity {
    // Strings to pass Note ID and Floor Number in Intents
    private static final String EXTRA_NOTE_ID =
            "com.bignerdranch.android.escapeovatortemp.note_id";
    private static final String EXTRA_FLOOR_NUM =
            "com.bignerdranch.android.escapeovatortemp.floor_num";

    // Creates a new Intent, passing in the Note ID and floor number
    public static Intent newIntent(Context pkgContext, UUID noteId, int floorNum) {
        Intent intent = new Intent(pkgContext, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        intent.putExtra(EXTRA_FLOOR_NUM, floorNum);
        return intent;
    }

    // Creates a NoteFragment
    @Override
    protected Fragment createFragment() {

        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        int floorNum = getIntent().getIntExtra(EXTRA_FLOOR_NUM, 1);
        return NoteFragment.newInstance(noteId, floorNum);
    }
}