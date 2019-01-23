package com.bhupendra.production123;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class AudioActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int REQ_CODE_PICK_SOUNDFILE = 234;
    private Uri filePath;
//    final ImageView img = findViewById(R.id.img);
    private ImageView imageView;
    private  EditText PhotoUrl,audioUrl,name,Subname;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        final EditText name = findViewById(R.id.editText1);
        final EditText Subname = (EditText) findViewById(R.id.editText2);
        audioUrl = (EditText) findViewById(R.id.editText3);
        PhotoUrl = (EditText) findViewById(R.id.editText4);


        Button submit = (Button) findViewById(R.id.button);
        Button audioupload = (Button) findViewById(R.id.buttonUP);
        Button selecte = (Button) findViewById(R.id.buttonse);
        imageView = (ImageView) findViewById(R.id.imgaudio);

//        name.setEnabled(false);
//        Subname.setEnabled(false);

        PhotoUrl.setEnabled(false);
        PhotoUrl.setText("00img");


        //attaching listener
        selecte.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showaudioChooser();
           }
       });

        audioupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("audioFile").push();


                if(name.getText().toString().length()<1 || Subname.getText().toString().length()<1 || audioUrl.getText().toString().length()<1 || PhotoUrl.getText().toString().length()<1 )
                {
                    // Display toast
                    Toast.makeText(getApplicationContext(), "Please enter something !",Toast.LENGTH_LONG).show();
                }
                else{

                    myRef.child("name").setValue(name.getText().toString());
                    myRef.child("subname").setValue(Subname.getText().toString());
                    myRef.child("Url").setValue(audioUrl.getText().toString());
                    myRef.child("Url_img").setValue(PhotoUrl.getText().toString());

                    Toast.makeText(getApplicationContext(),"Data Upload ....",Toast.LENGTH_SHORT).show();


                }

            }
        });
//
//
//    }

    }

    //method to show file chooser

//     private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//     }

    private void showaudioChooser() {
        Intent intent;
        intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/mpeg");

        startActivityForResult(Intent.createChooser(intent, "Select song"), REQ_CODE_PICK_SOUNDFILE);

    }


    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            File file= new File(filePath.getPath());
            file.getName();
            audioUrl.setText(file.getName().toString());
            Log.e( "path: ",  file.getName());



            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_done));
        }
        else
            imageView.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_music));

    }



    //upload

    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            //StorageReference storageRef = storage.getReferenceFromUrl("images/r1.jpg");
            StorageReference riversRef = storage.getReference().child("photoImg/"+audioUrl.getText().toString());

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }

                    });
            audioUrl.setEnabled(false);




        }
        //if there is not any file
        else {
            Toast.makeText(getApplicationContext(), "sdfsdhfgjhsd", Toast.LENGTH_LONG).show();
        }


    }


}
