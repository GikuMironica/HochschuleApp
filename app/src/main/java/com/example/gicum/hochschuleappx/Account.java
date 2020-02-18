package com.example.gicum.hochschuleappx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class Account extends AppCompatActivity {

    private String name;
    private int flagNr;
    private ImageView flag;
    private TextView nameJ;
    private TextView matrkJ;
    private TextView degreeJ;
    private TextView semestrJ;
    private SharedPreferences pref;
    private Course[] c;
    private ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout);
            // bind views with objects
        flag = findViewById(R.id.imageView);
        nameJ = findViewById(R.id.nameJ);
        matrkJ = findViewById(R.id.matrkJ);
        degreeJ = findViewById(R.id.degreeJ);
        semestrJ = findViewById(R.id.semestrJ);

        Intent in = getIntent();
        name = in.getStringExtra("user");
        Log.d("debugAs",name+" name");
        flagNr = getResources().getIdentifier(name,"drawable", getPackageName());
        flag.setImageResource(flagNr);


            // access user data from "database"

        Person p1 = users.acces(in.getStringExtra("user"));
        // Log.d("users",p1.getUser());
            // update info views
        nameJ.setText(p1.getName());
        matrkJ.setText(String.valueOf(p1.getMatr()));
        degreeJ.setText(p1.getStudy());
        semestrJ.setText(String.valueOf(p1.getSemestr()));
        getCoursesUpdated(p1);
    }

    private void getCoursesUpdated(Person p1) {
        List<Course> c1 = p1.getCourses();
        c = new Course[c1.size()];
        c = c1.toArray(c);
        Log.d("course",String.valueOf(c.length));

        listView = findViewById(R.id.listViewAcc);
        // use this adapter for the main grid
        ListAdapter listA = new ListAdapter(this, c);
        listView.setAdapter(listA);
        //Log.d("users", c1.getName());
       // title.setText(c1.getName());

    }

    public void logOut(View view){
            // delete all saved preferences
        pref = getPreferences(Context.MODE_PRIVATE);
        pref.edit().clear().apply();

            // jump to login screen
        Intent intent = new Intent(Account.this, LoginActivity.class);
        intent.putExtra("destroy",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }
    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}
