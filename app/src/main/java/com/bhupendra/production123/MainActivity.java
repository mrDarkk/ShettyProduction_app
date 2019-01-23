package com.bhupendra.production123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.editText1);
        final EditText url = (EditText) findViewById(R.id.editText2);
        final EditText production = (EditText) findViewById(R.id.editText3);
        final EditText date = (EditText) findViewById(R.id.editText4);
        final EditText year = (EditText) findViewById(R.id.editText5);
        submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("videoFile").push();


                if(name.getText().toString().length()<1 || url.getText().toString().length()<1 || production.getText().toString().length()<1 || date.getText().toString().length()<1 || year.getText().toString().length()<1 )
                {
                    // Display toast
                    Toast.makeText(getApplicationContext(), "Please enter something !",Toast.LENGTH_LONG).show();
                }
                else{

                    myRef.child("Name").setValue(name.getText().toString());
                    myRef.child("url").setValue(url.getText().toString());
                    myRef.child("production").setValue(production.getText().toString());
                    myRef.child("day").setValue(date.getText().toString());
                    myRef.child("year").setValue(year.getText().toString());
                    Toast.makeText(getApplicationContext(),"Data Upload ....",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
