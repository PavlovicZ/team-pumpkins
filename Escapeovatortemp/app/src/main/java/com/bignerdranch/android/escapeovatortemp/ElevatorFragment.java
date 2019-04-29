package com.bignerdranch.android.escapeovatortemp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ElevatorFragment extends DialogFragment {
    private static final String TAG = "ElevatorFragment";
    private static final String KEY_INDEX = "index";

    private RadioGroup mElevatorButtons;
    private RadioButton mFloorButton1;
    private RadioButton mFloorButton2;
    private RadioButton mFloorButton3;
    private RadioButton mFloorButton4;
    private RadioButton mFloorButton5;
    private int mFloor;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_elevator, null);

        mElevatorButtons = (RadioGroup) view.findViewById(R.id.elevator_buttons);

        mFloorButton1 = (RadioButton) view.findViewById(R.id.elevator_floor_1);
        mFloorButton2 = (RadioButton) view.findViewById(R.id.elevator_floor_2);
        mFloorButton3 = (RadioButton) view.findViewById(R.id.elevator_floor_3);
        mFloorButton4 = (RadioButton) view.findViewById(R.id.elevator_floor_4);
        mFloorButton5 = (RadioButton) view.findViewById(R.id.elevator_floor_5);
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
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
        Intent intent = new Intent(ElevatorFragment.this, ParentFloorActivity.class);
        startActivity(intent, Bundle savedInstanceState);
    }

    public void onSaveInstanceState() {
        super.onSaveInstanceState(Bundle savedInstanceState);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
