package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.bignerdranch.android.escapeovatortemp.R;

public class Floor1Activity extends ParentFloorActivity {
    private static final String TAG = "Floor1Activity";



    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, Floor1Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor1);

        mElevatorButton = findViewById(R.id.elevator_button);
        mElevatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                //ElevatorFragment.instantiate(); //I'm fixing this :c
            }
        });

        /*
        mNoteButton = (Button) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Floor1Activity.this, MenuActivity.class);
                startActivity(i);
            }
        });
        */

        mGrabFlashlightButton = (ImageButton) findViewById(R.id.grab_flashlight);
        mGrabFlashlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFlashlightHeld = true;
                updateToolbar();
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
