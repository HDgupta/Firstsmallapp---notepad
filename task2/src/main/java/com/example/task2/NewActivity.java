package com.example.task2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();
    String name,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(NewActivity.this,MainActivity.class));
                finish();
            }
        });

        findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = ((EditText)findViewById(R.id.edtName)).getText().toString();
                data =  ((EditText)findViewById(R.id.edtData)).getText().toString();


                if (name.equals("")){
                    validity();
                }
                else{
                    try {
                        FileOutputStream fos = openFileOutput(name+".txt",MODE_APPEND);
                        fos.write(data.getBytes());
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    findViewById(R.id.edtName).setEnabled(false);
                    msg("Saved successfully");
                }
            }
        });
    }

    private void validity() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Error")
                .setMessage("Please Enter name of file")
                .setPositiveButton(R.string.name, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.drawable.ic_error_black_24dp).create().show();

        }

    private void msg(String mt){
        Toast.makeText(this,mt,Toast.LENGTH_SHORT).show();
    }
}
