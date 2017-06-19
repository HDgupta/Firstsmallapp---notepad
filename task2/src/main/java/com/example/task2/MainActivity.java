package com.example.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Myitems> dataset=new ArrayList<>();
        dataset.add(new Myitems("New",R.drawable.ic_plus));
        dataset.add(new Myitems("Edit",R.drawable.ic_edit_black_24dp));

        MyGridviewAdapter adapter = new MyGridviewAdapter(this,dataset);
        ((GridView)findViewById(R.id.grdView)).setAdapter(adapter);

        ((GridView)findViewById(R.id.grdView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= null;

                if(dataset.get(position).equals(dataset.get(0))){
                    intent = new Intent(MainActivity.this,NewActivity.class);
                    startActivity(intent);
                }
                else if(dataset.get(position).equals(dataset.get(1))) {
                    intent = new Intent(MainActivity.this, ListEditActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}

