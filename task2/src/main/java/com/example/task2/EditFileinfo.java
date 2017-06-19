package com.example.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditFileinfo extends AppCompatActivity {
    String nam,afterEdit,beforeEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fileinfo);

        Intent responsibleintent=getIntent();
        Bundle bundle=responsibleintent.getExtras();
        nam=bundle.getString("KeyName");
        ((EditText)findViewById(R.id.edtName)).setText(nam);

        ReadFile();


        findViewById(R.id.imgBack2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        findViewById(R.id.imgSave2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterEdit = ((EditText)findViewById(R.id.edtData)).getText().toString();

                if(beforeEdit.equals(afterEdit)){
                    Toast.makeText(EditFileinfo.this,"Edit the file first..!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    ((EditText)findViewById(R.id.edtData)).setText("");
                    ((EditText)findViewById(R.id.edtData)).setText(afterEdit);
                    try {
                        FileOutputStream fos = openFileOutput(nam,1);
                        fos.write(afterEdit.getBytes());
                        fos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(EditFileinfo.this,"File edited..",Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.imgDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filePath = getFilesDir().getAbsolutePath();
                File file = new File(filePath,nam);
                Boolean deletedFileStatus = file.delete();
                Toast.makeText(EditFileinfo.this,"File Deleted",Toast.LENGTH_SHORT).show();
                goBack();
            }
        });
    }


    private void goBack() {
            startActivity(new Intent(EditFileinfo.this,ListEditActivity.class));
             finish();
         }

         private String ReadFile(){
             FileInputStream fis= null;
             try {
                 fis = openFileInput(nam);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }StringBuilder builder = new StringBuilder();
             while (true){
                 int ch = 0;
                 try {
                     ch = fis.read();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 if(ch == -1) break;
                 else builder.append((char)ch);
             }
             ((EditText)findViewById(R.id.edtData)).setText(builder);

             beforeEdit = builder.toString();

             return beforeEdit;
         }
}
