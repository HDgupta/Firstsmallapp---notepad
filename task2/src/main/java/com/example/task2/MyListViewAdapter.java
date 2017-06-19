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
 * Created by divya on 15/6/17.
 */

public class MyListViewAdapter extends BaseAdapter {

    private  final Context context;
    public final List<MyFileitems> file;
    private final LayoutInflater inflater;

    public MyListViewAdapter(Context context,List<MyFileitems> file){
        this.context = context;
        this.file = file;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return file.size();
    }

    @Override
    public Object getItem(int position) {
        return file.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      View view = null;
        if(convertView == null)
            view = inflater.inflate(R.layout.mylistfiles, parent, false);
        else
            view = convertView;

        ((ImageView)view.findViewById(R.id.imgList)).setImageResource(file.get(position).imgid);
        ((TextView)view.findViewById(R.id.fileName)).setText(file.get(position).name);
        ((TextView)view.findViewById(R.id.fileSize)).setText(file.get(position).size);
        ((TextView)view.findViewById(R.id.fileDate)).setText(file.get(position).mdate);
        return view;
    }
}
