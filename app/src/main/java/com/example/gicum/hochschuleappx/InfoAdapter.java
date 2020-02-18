package com.example.gicum.hochschuleappx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private TextView street;
    private String streets[];

    public InfoAdapter(Context context, String s[]){
        this.context = context;
        this.streets = s;
    }

    @Override
    public int getCount() {
        return streets.length;
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
        context = parent.getContext();
        if (convertView == null) {
                // convert xml layout to java object
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.info_list_item, parent, false);
        }
        street = convertView.findViewById(R.id.street);
        street.setText(streets[position]);
        return convertView;
    }
}
