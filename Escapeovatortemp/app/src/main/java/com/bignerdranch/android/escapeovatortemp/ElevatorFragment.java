package com.bignerdranch.android.escapeovatortemp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
        Log.d(TAG, "onCreateDialog() called");
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
        builder.setView(view);
        return builder.show();
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
        Intent intent = new Intent(getContext(), ParentFloorActivity.class);
        startActivity(intent, new Bundle());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public static ElevatorFragment newInstance() {
        Bundle args = new Bundle();

        ElevatorFragment fragment = new ElevatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
