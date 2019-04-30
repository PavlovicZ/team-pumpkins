package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Floor2Activity extends ParentFloorActivity {
    private static final String TAG = "Floor2Activity";

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, Floor2Activity.class);
        return intent;
    }

    private int mFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor2);

        mFloor = getIntent().getIntExtra(EXTRA_FLOOR, 1);

        mChestButton = (ImageButton) findViewById(R.id.chest_button);
        mChestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChestOpened = true;
                Toast.makeText(Floor2Activity.this, R.string.safe_code, Toast.LENGTH_SHORT).show();
            }
        });


        mGrabKeyButton = (ImageButton) findViewById(R.id.grab_key);
        mGrabKeyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyHeld = true;
                updateToolbar();

                // Start the noteActivity with the auto-generated note
                Intent i = NoteActivity.newIntent(Floor2Activity.this, Floor1Activity.sAutoGeneratedNotes.get(1).getId(), 2);
                startActivity(i);
            }
        });

        if (savedInstanceState != null){
            mXRayGlassesHeld = savedInstanceState.getBoolean(KEY_XRAYGLASSES, false);
            mFlashlightHeld = savedInstanceState.getBoolean(KEY_FLASHLIGHT, false);
            mBlacklightHeld = savedInstanceState.getBoolean(KEY_BLACKLIGHT, false);
            mKeyHeld = savedInstanceState.getBoolean(KEY_KEY, false);
            mLockpickHeld = savedInstanceState.getBoolean(KEY_LOCKPICK, false);
            mChestOpened = savedInstanceState.getBoolean(KEY_CHEST, false);
        }

        mElevatorButton = (Button) (findViewById(R.id.elevator_button));
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
                    mGrabKeyButton.setVisibility(View.VISIBLE);
                    mChestButton.setVisibility(View.VISIBLE);
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

        mNoteButton = (ImageButton) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Floor2Activity.this, MenuActivity.class);
                startActivity(i);
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
}
