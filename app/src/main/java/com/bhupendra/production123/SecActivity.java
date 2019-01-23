package com.bhupendra.production123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        Button audio = (Button) findViewById(R.id.buttonaudio);
        Button video = (Button) findViewById(R.id.buttonvideo);
        Button user = (Button) findViewById(R.id.buttonuser);


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SecActivity.this,MainActivity.class));
            }
        });


        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecActivity.this,AudioActivity.class));
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecActivity.this,UserListActivity.class));
            }
        });


    }
}
