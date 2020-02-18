package com.example.gicum.hochschuleappx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

    // custom adapter for the gridView in the main activity
public class GridAdapter extends BaseAdapter {

    Context context;
    TextView txt;
    ImageView img;
    private String[] names;
    private int[] images;
    View view;
    LayoutInflater inflater;

    public GridAdapter(Context context, String[] names, int[] images) {
        this.context = context;
        this.names = names;
        this.images = images;

    }

    @Override
    public int getCount() {
        return names.length;
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
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
                // link the imageView and text view with the xml
        }
        img = (ImageView) convertView.findViewById(R.id.imageView);
        txt = (TextView) convertView.findViewById(R.id.gridOption);
        img.setImageResource(images[position]);
        txt.setText(names[position]);
        return convertView;
    }
}
