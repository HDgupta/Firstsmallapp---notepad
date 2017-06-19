package com.example.task2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by divya on 13/6/17.
 */

public class MyGridviewAdapter extends BaseAdapter {

    public LayoutInflater inflater;
    public Context context;
    public List<Myitems> dataset;

    public MyGridviewAdapter(Context context,List<Myitems> dataset){

        this.context=context;
        this.dataset=dataset;
        inflater=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int position) {
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View main = null;
        if(convertView==null)
            main=inflater.inflate(R.layout.mygrid_view,parent,false);
        else
            main=convertView;

        ((ImageView)main.findViewById(R.id.imageView3)).setImageResource(dataset.get(position).imgid);
        ((TextView)main.findViewById(R.id.textView4)).setText(dataset.get(position).name);
        return main;
    }
}
