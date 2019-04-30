package com.bignerdranch.android.escapeovatortemp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ElevatorFragment extends DialogFragment {
    private static final String TAG = "ElevatorFragment";
    private static final String KEY_FLOOR = "Floor";
    private static final int REQUEST_FLOOR = 0;

    private RadioGroup mElevatorButtons;
    private RadioButton mFloorButton1;
    private RadioButton mFloorButton2;
    private RadioButton mFloorButton3;
    private RadioButton mFloorButton4;
    private RadioButton mFloorButton5;
    private Button mEnterFloorButton;
    private int mFloor = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog() called");
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (savedInstanceState != null){
            mFloor = savedInstanceState.getInt(KEY_FLOOR, mFloor);
        }

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_elevator, null);

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

        mEnterFloorButton = (Button) view.findViewById(R.id.enter_floor);
        mEnterFloorButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mFloor != 1 && mFloor != 2 && mFloor != 3 && mFloor != 4 && mFloor != 5){
                    Toast.makeText(ElevatorFragment.this, R.string.missing_floor_input, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = ParentFloorActivity.newIntent(getActivity(), mFloor);
                    startActivityForResult(intent, REQUEST_FLOOR);
                }
            }
        });

        builder.setView(view);
        return builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_FLOOR) {
            if (data == null) {
                return;
            }
            mFloor = ParentFloorActivity.insertFloor(data);
        }
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

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_FLOOR, mFloor);
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