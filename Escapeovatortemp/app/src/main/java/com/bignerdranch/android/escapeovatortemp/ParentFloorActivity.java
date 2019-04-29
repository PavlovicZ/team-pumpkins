package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ParentFloorActivity extends AppCompatActivity {
    private static final String TAG = "ParentFloorActivity";
    private static final int NOTE = 0;

    private Button mNoteButton;
    private Button mItemButton;
    private Button mFlashlightButton;
    private Button mBlacklightButton;
    private Button mKeyButton;
    private Button mLockpickButton;

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, ParentFloorActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_floor);

        Button elevatorButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                ElevatorFragment.instantiate(); //I'm fixing this :c
            }
        });

        mNoteButton = (Button) view.findViewById(R.id.note_button);
        mNoteButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent note = newIntent(ParentFloorActivity.this, Notepad.class);
                startActivityForResult(note, NOTE);
            }
        });

        mFlashlightButton = (Button) view.findViewById(R.id.flashlight_button);
        mFlashlightButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will change background
                if (mFlashlightButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mBlacklightButton = (Button) view.findViewById(R.id.blacklight_button);
        mBlacklightButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will change background
                if (mBlacklightButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mKeyButton = (Button) view.findViewById(R.id.key_button);
        mKeyButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will change background
                if (mKeyButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });

        mLockpickButton = (Button) view.findViewById(R.id.lockpick_button);
        mLockpickButton = new Button(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will change background
                if (mLockpickButton.isEnabled()) {
                    //changes background of specific floor
                }
            }
        });
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
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
        //Intent intent = new Intent(Floor1Activity.this, ElevatorFragment.class);
        //startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
