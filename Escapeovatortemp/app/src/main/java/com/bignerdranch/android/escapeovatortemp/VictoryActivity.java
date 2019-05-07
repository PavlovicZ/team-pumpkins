package com.bignerdranch.android.escapeovatortemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VictoryActivity extends AppCompatActivity {

    private Button mPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        //Calls the Floor1Activity and clears the notepad, effectively the SQLite information, to start the game again.
        //Intent: H. Ben Reed
        //Notepad Clear: Anthony Hessler
        mPlayAgain = (Button) findViewById(R.id.play_again);
        mPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(VictoryActivity.this, Floor1Activity.class);
                startActivity(intent);
            }
        });
    }
}
