package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ParentFloorActivity extends AppCompatActivity {
    private static final String TAG = "ParentFloorActivity";
    public static final String KEY_FLASHLIGHT = "Flashlight";
    public static final String KEY_BLACKLIGHT = "Blacklight";
    public static final String KEY_KEY = "Key";
    public static final String KEY_LOCKPICK = "Lockpick";
    public static final String KEY_XRAYGLASSES = "XRayGlasses";
    public static final String KEY_CHEST = "Chest";
    public static final String KEY_FLOOR = "Floor";
    private static final int NOTE = 0;
    public static final String EXTRA_FLOOR = "com.bignerdranch.android.escapeovatortemp.floor";

    public Button mElevatorButton;

    public ImageButton mGrabFlashlightButton;

    public ImageButton mGrabKeyButton;
    public ImageButton mChestButton;

    public ImageButton mGrabBlacklightButton;

    public ImageButton mGrabLockpickButton;

    public ImageButton mGrabXRayGlassesButton;

    public ImageButton mNoteButton;
    public ImageButton mFlashlightButton;
    public ImageButton mXRayGlassesButton;
    public ImageButton mBlacklightButton;
    public ImageButton mKeyButton;
    public ImageButton mLockpickButton;

    public Boolean mFlashlightHeld = false;
    public Boolean mXRayGlassesHeld = false;
    public Boolean mBlacklightHeld = false;
    public Boolean mKeyHeld = false;
    public Boolean mLockpickHeld = false;
    public Boolean mChestOpened = false;

    public static Intent newIntent(Context packageContext, int mFloor) {
        Intent intent = new Intent(packageContext, ParentFloorActivity.class);
        intent.putExtra(EXTRA_FLOOR, mFloor);
        return intent;
    }

    private int mFloor;

    public static int insertFloor(Intent result) {
        return result.getIntExtra(EXTRA_FLOOR, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_floor);

        mFloor = getIntent().getIntExtra(EXTRA_FLOOR, 1);

        if(mFloor == 1){
            Intent intent = Floor1Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        } else if(mFloor == 2){
            Intent intent = Floor2Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 3){
            Intent intent = Floor3Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 4){
            Intent intent = Floor4Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }else if(mFloor == 5){
            Intent intent = Floor5Activity.newIntent(ParentFloorActivity.this, mFloor);
            startActivity(intent);
        }


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
        //Intent intent = new Intent(Floor1Activity.this, ElevatorFragment.class);
        //startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void updateToolbar() {
        if (mFlashlightHeld = true){
            mFlashlightButton.isEnabled();
        }
        if (mBlacklightHeld = true){
            mBlacklightButton.isEnabled();
        }
        if (mXRayGlassesHeld = true){
            mXRayGlassesButton.isEnabled();
        }
        if (mKeyHeld = true){
            mKeyButton.isEnabled();
        }
        if (mLockpickHeld = true){
            mLockpickButton.isEnabled();
        }
    }
}
