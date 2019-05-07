package com.bignerdranch.android.escapeovatortemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * H. Ben Reed
 * */

public class VictoryActivity extends AppCompatActivity {

    private Button mClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        // Closes out of the program entirely
        //Intent: H. Ben Reed
        //Notepad Clear: Anthony Hessler
        mClose = (Button) findViewById(R.id.play_again);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
