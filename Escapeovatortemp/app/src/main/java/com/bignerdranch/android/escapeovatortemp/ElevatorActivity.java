package com.bignerdranch.android.escapeovatortemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;

import com.bignerdranch.android.escapeovatortemp.R;

public class ElevatorActivity extends AppCompatActivity {
    private static final String TAG = "ElevatorActivity";
    private static final String KEY_INDEX = "index";

    private RadioGroup mElevatorButtons;
    private RadioButton mFloorButton1;
    private RadioButton mFloorButton2;
    private RadioButton mFloorButton3;
    private RadioButton mFloorButton4;
    private RadioButton mFloorButton5;
    private int mFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator);

        mElevatorButtons = (RadioGroup) findViewById(R.id.elevator_buttons);

        mFloorButton1 = (RadioButton) findViewById(R.id.elevator_floor_1);
        mFloorButton2 = (RadioButton) findViewById(R.id.elevator_floor_2);
        mFloorButton3 = (RadioButton) findViewById(R.id.elevator_floor_3);
        mFloorButton4 = (RadioButton) findViewById(R.id.elevator_floor_4);
        mFloorButton5 = (RadioButton) findViewById(R.id.elevator_floor_5);
        mElevatorButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup mElevatorButtons, int checkedId){
                if (mFloorButton1.isChecked()) {
                    mFloor = 1;
                }
                else if (mFloorButton2.isChecked()) {
                    mFloor = 2;
                }
                else if (mFloorButton3.isChecked()) {
                    mFloor = 3;
                }
                else if (mFloorButton4.isChecked()) {
                    mFloor = 4;
                }
                else if (mFloorButton5.isChecked()) {
                    mFloor = 5;
                }
            }
        });
    }

    @Override
    protected void onStart() {
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
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mFloor);
        if (mFloor == 1) {
            Intent intent = new Intent(ElevatorActivity.this, Floor1Activity.class);
            startActivity(intent);
        }
        else if (mFloor == 2){
            Intent intent = new Intent(ElevatorActivity.this, Floor2Activity.class);
            startActivity(intent);
        }
        else if (mFloor == 3){
            Intent intent = new Intent(ElevatorActivity.this, Floor3Activity.class);
            startActivity(intent);
        }
        else if (mFloor == 4){
            Intent intent = new Intent(ElevatorActivity.this, Floor4Activity.class);
            startActivity(intent);
        }
        else if (mFloor == 5){
            Intent intent = new Intent(ElevatorActivity.this, Floor5Activity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
