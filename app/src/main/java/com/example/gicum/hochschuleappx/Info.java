package com.example.gicum.hochschuleappx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Info extends AppCompatActivity {
    TextView acc;
    TextView log1;
    TextView pas1;
    String usr;
    String psd;
    TextView log2;
    TextView pas2;
    private FileOutputStream stream;
    private SharedPreferences pref;
    TextView log2Edit;
    TextView pas2Edit;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
        pref = getPreferences(Context.MODE_PRIVATE);

        context = this;

        Intent in = getIntent();
        log2Edit = findViewById(R.id.user2Edit);
        pas2Edit = findViewById(R.id.pas2Edit);
        log2Edit.setText(in.getStringExtra("name"));

        acc = findViewById(R.id.accInfo);
        log1 = findViewById(R.id.User1);
        log2 = findViewById(R.id.User2);
        pas1 = findViewById(R.id.Pass1);
        pas2 = findViewById(R.id.Pass2);
        acc.setText(getString(R.string.details));
        log1.setText(getString(R.string.User1));
        pas1.setText(getString(R.string.Pas1));

        log2.setText(in.getStringExtra("name"));

            // display info
        usr = in.getStringExtra("name");
        psd = log2.getText().toString();
        try {
            FileInputStream fis = context.openFileInput("users.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                String[] strgs = line.split(" ");
                Log.d("writeFile",usr+" "+strgs[0]);
                if(psd.equals(strgs[0])) {
                    pas2.setText(strgs[1]);
                    pas2Edit.setText(strgs[1]);
                }
            }
            stream.close();
            Log.d("writeFile", "values copied into account info password");
        } catch(Exception e){
            //haha
        }


        // Hide the keyboard when touching anywhere on the screen
        findViewById(R.id.card).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void change(View view){
       // log2.setVisibility(View.GONE);
        pas2.setVisibility(View.GONE);
      //  log2Edit.setVisibility(View.VISIBLE);
        pas2Edit.setVisibility(View.VISIBLE);
        //editText.setVisibility(View.VISIBLE);
    }

    public void changePassword(View view) {
            // update views
        // String username = log2Edit.getText().toString();
        String password = pas2Edit.getText().toString();
       // log2.setText(username);
        pas2.setText(password);
       // log2Edit.setVisibility(View.GONE);
        pas2Edit.setVisibility(View.GONE);
      //  log2.setVisibility(View.VISIBLE);
        pas2.setVisibility(View.VISIBLE);


            // save date from users.txt
        String arr1[] = new String[10];
            // update password db
        try {
            FileInputStream fis = context.openFileInput("users.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                String[] strgs = line.split(" ");
                arr1[i] = strgs[0];
                arr1[i+1] = strgs[1];
                i+=2;
            }
            stream.close();
            Log.d("writeFile", "values copied into array");
        } catch(Exception e){
            //haha
        }

            // delete users.txt
        context.deleteFile("users.txt");
        Log.d("writeFile", String.valueOf(fileExist("users.txt"))+" after deleteing users.txt");

            // create again users.txt
        File path = context.getFilesDir();
        File file = new File(path, "users.txt");
        try {
            stream = new FileOutputStream(file);
            String usr = log2.getText().toString();
            if(usr.equals(arr1[0])){
                stream.write((usr+" "+password+"\n").getBytes());
                stream.write((arr1[2]+" "+arr1[3]+"\n").getBytes());

            } else
                if(usr.equals(arr1[2])){
                stream.write((arr1[2]+" "+password+"\n").getBytes());
                stream.write((arr1[0]+" "+arr1[1]+"\n").getBytes());

            }
            stream.close();
        } catch (Exception e) {
            try {
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        Toast toast = Toast.makeText(Info.this, "New password saved!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}
