package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.bignerdranch.android.escapeovatortemp.R;

import java.util.ArrayList;
import java.util.UUID;

public class Floor1Activity extends ParentFloorActivity {
    private static final String TAG = "Floor1Activity";

    public static ArrayList<Note> sAutoGeneratedNotes = new ArrayList<Note>();

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, Floor1Activity.class);
        return intent;
    }

    private int mFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor1);

        mFloor = getIntent().getIntExtra(EXTRA_FLOOR, 1);

        // Set auto-generated notes
        sAutoGeneratedNotes.add(new Note(1, "You found the flashlight! This can shed some light upon the situation!", false));
        sAutoGeneratedNotes.add(new Note(2, "You found the key! This opens up a new opportunity!", false));
        sAutoGeneratedNotes.add(new Note(3, "You found the blacklight! Maybe it will help you see something you missed.", false));
        sAutoGeneratedNotes.add(new Note(4, "You found the lockpick! It can pick locks.", false));
        sAutoGeneratedNotes.add(new Note(5, "You found the X-Ray glasses! This can help you see things differently!", false));

        mElevatorButton = (Button) findViewById(R.id.elevator_button);
        mElevatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElevatorFragment fragment = new ElevatorFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, null);
                transaction.commit();
            }
        });

        mNoteButton = (ImageButton) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Floor1Activity.this, MenuActivity.class);
                startActivity(i);
            }
        });

        mGrabFlashlightButton = (ImageButton) findViewById(R.id.grab_flashlight);
        mGrabFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlashlightHeld = true;
                updateToolbar();

                // Start the noteActivity with the auto-generated note
                Intent i = NoteActivity.newIntent(Floor1Activity.this, sAutoGeneratedNotes.get(0).getId(), 1);
                startActivity(i);
            }
        });

       /*mNoteButton = (ImageButton) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent note = new Intent(getApplicationContext(), MenuFragment.class);
                startActivityForResult(note, NOTE);
            }
        });*/

        mFlashlightButton = (ImageButton) findViewById(R.id.flashlight_button);
        mFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlashlightButton.isEnabled()) {
                    if (mFloor == 5){
                        mGrabXRayGlassesButton.setVisibility(View.VISIBLE);
                    }
                    //changes background of specific floor
                }
            }
        });

        mXRayGlassesButton = (ImageButton) findViewById(R.id.xrayglasses_button);
        mXRayGlassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXRayGlassesButton.isEnabled()) {
                    if (mFloor == 2){
                        mGrabKeyButton.setVisibility(View.VISIBLE);
                        mChestButton.setVisibility(View.VISIBLE);
                    }
                    //changes background of specific floor
                }
            }
        });

        mBlacklightButton = (ImageButton) findViewById(R.id.blacklight_button);
        mBlacklightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlacklightButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mKeyButton = (ImageButton) findViewById(R.id.key_button);
        mKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeyButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mLockpickButton = (ImageButton) findViewById(R.id.lockpick_button);
        mLockpickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLockpickButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        updateToolbar();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_FLASHLIGHT, mFlashlightHeld);
        savedInstanceState.putBoolean(KEY_BLACKLIGHT, mBlacklightHeld);
        savedInstanceState.putBoolean(KEY_KEY, mKeyHeld);
        savedInstanceState.putBoolean(KEY_LOCKPICK, mLockpickHeld);
        savedInstanceState.putBoolean(KEY_XRAYGLASSES, mXRayGlassesHeld);
        savedInstanceState.putBoolean(KEY_CHEST, mChestOpened);
        savedInstanceState.putInt(KEY_FLOOR, mFloor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public static Note getNote(UUID noteID)
    {
        for(Note n: sAutoGeneratedNotes)
        {
            if(n.getId().equals(noteID))
                return n;
        }
        return null;
    }
}
