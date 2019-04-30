package com.bignerdranch.android.escapeovatortemp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class Floor2Activity extends ParentFloorActivity {
    private static final String TAG = "Floor2Activity";

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, Floor2Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor2);

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
        Intent intent = new Intent(Floor2Activity.this, ElevatorFragment.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
