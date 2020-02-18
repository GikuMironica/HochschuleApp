package com.example.gicum.hochschuleappx;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private Course[] c1;
    private Context context;

        // for the list_item
    private TextView title;
    private TextView prof;
    private TextView day;
    private TextView time;
    private TextView room;
    private LayoutInflater inflater;


    public ListAdapter(Context context, Course[] courses){
        this.context = context;
        this.c1 = courses;
    }

    @Override
    public int getCount() {
        return c1.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context bg = parent.getContext();
        if (convertView == null) {
                // convert xml layout to java object
            inflater = (LayoutInflater) bg.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        title = convertView.findViewById(R.id.title);
        prof = convertView.findViewById(R.id.prof);
        time = convertView.findViewById(R.id.time);
        day = convertView.findViewById(R.id.day);
        room = convertView.findViewById(R.id.room);

        Log.d("course",c1[position].getName());
        title.setText(c1[position].getName());
        prof.setText(c1[position].getProfesor());
        time.setText(c1[position].getTime());
        day.setText(c1[position].getDate());
        room.setText(c1[position].getRoom());
        return convertView;
    }
}
