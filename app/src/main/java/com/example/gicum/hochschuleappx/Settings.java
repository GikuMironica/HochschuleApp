package com.example.gicum.hochschuleappx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    TextView option;
    ListView list;
    String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

      final  String s[] =            {"Prittwitzstraße 10, 89075 Ulm"
                                ,"Eberhard-Finckh-Straße 11, 89075 Ulm"
                                ,"Albert-Einstein-Allee 55, 89081 Ulm"};
            // add list view
        list = findViewById(R.id.list2);
        // use this adapter for the main grid
        InfoAdapter listA = new InfoAdapter(this, s);
        list.setAdapter(listA);

        list.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("street",String.valueOf(position));
                    Uri location = Uri.parse("geo:0,0?q=" + s[position]);
                    Intent map = new Intent(Intent.ACTION_VIEW, location);
                    map.setPackage("com.google.android.apps.maps");
                    startActivity(map);
                }
            }
        );



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
