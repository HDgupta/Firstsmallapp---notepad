package com.example.task2;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListEditActivity extends AppCompatActivity {

    long size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        //Going back to main activity
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListEditActivity.this,MainActivity.class));
                finish();
            }
        });

        //Loading list of files
        File filesDir = getFilesDir();
        final List<MyFileitems> fileItems = new ArrayList<>();
        File[] files = filesDir.listFiles();


        for (File fl : files) {
            //converting long to date
            long ms = fl.lastModified();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy,HH:mm");
            Date resultdate = new Date(ms);


            size = fl.length();
            fileItems.add(new MyFileitems(fl.getName(), SizeChange(size), "" + sdf.format(resultdate), R.drawable.ic_insert_drive_file_black_24dp));
        }
        ((ListView) findViewById(R.id.lstView)).setAdapter(new MyListViewAdapter(this, fileItems));
        ((ListView) findViewById(R.id.lstView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MyFileitems clickedItem = fileItems.get(position);
                String nam = clickedItem.name;

                //Passing name to next activity
                Bundle bundle = new Bundle();
                bundle.putString("KeyName",nam);

                Intent intent = new Intent(ListEditActivity.this, EditFileinfo.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }
    //Converting size of file from bytes to kb,mb,gb
    private String SizeChange(long size){

        String hrSize = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }
}
