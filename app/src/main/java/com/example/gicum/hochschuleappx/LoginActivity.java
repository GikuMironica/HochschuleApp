package com.example.gicum.hochschuleappx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
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
import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private Context context;
    private View progressBar;
    private SharedPreferences pref;
    private String user;
    private Boolean akk;
    private FileOutputStream stream;
    private FileInputStream fis;
    private String pass;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        pref = getPreferences(Context.MODE_PRIVATE);
        context = this;
                // if file for password doesn't exist create it and store the hardcoded passwords
            if(fileExist("users.txt")==false){
                Log.d("writeFile","file doesnt exist loginActivity Top");
                File path = context.getFilesDir();
                File file = new File(path, "users.txt");
                try {
                    stream = new FileOutputStream(file);
                    stream.write(("admin admin\n").getBytes());
                    Log.d("writeFile","in login admin admin\n");
                    stream.write(("student1 student1\n").getBytes());
                    Log.d("writeFile","in login student1 student1\n");
                    stream.close();
                } catch (Exception e) {
                    try {
                        stream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
                // check if logout button was pressed
            if(destroyed()) {
                pref.edit().clear().apply();
            }
                // check if we logged in
        boolean ok = pref.getBoolean("Logged",false);
        user = pref.getString("User","");
        Log.d("check", "Are we logged in ?"+String.valueOf(ok));

                // if we logged in already, jump to welcome page
            if (ok == true) {
                Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
                    Log.d("debugPass",String.valueOf(akk)+"  when we were logged in");
                    intent.putExtra("name", users.acces(user).getName());
                    intent.putExtra("user", users.acces(user).getUser());
                startActivity(intent);
                finish();
            }
            // else keep going

        setContentView(R.layout.login_layout);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        progressBar = findViewById(R.id.progressBar);

        username.setHint(getString(R.string.Username));
        password.setHint(getString(R.string.Password));

                    // Hide the keyboard when touching anywhere on the screen
            findViewById(R.id.imageView2).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    return false;
                }
            });

    }


    // when logout button was pressed
    private boolean destroyed() {
            Intent in = getIntent();
            boolean ok = in.getBooleanExtra("destroy",false);
            Log.d("check","log out was called"+String.valueOf(ok));
            if (ok==true)
                    return true;
            else
                return false;
    }

        // button login was pressed
    public void start(View view){
        user = username.getText().toString();
        pass = password.getText().toString();
        Log.d("debug1",user+" user and pass "+pass);

            if (checkMetaData(user, pass)) {
                Intent main = new Intent(LoginActivity.this, WelcomeScreen.class);
                main.putExtra("name", users.acces(user).getName());
                main.putExtra("user", users.acces(user).getUser());
                saveAccPreferences();
                startActivity(main);
                finish();
            }

    }

    // check that password, login matches
    private boolean checkMetaData(String user1, String pass1){
            try {
                FileInputStream fis = context.openFileInput("users.txt");
                InputStreamReader isr = new InputStreamReader(fis);

                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    String[] strgs = line.split(" ");
                    if (strgs[0].equals(user1) && strgs[1].equals(pass1)){
                        Log.d("writeFile", user1 +" "+ pass1 +" "+ strgs[0]+" "+strgs[1]+" match!");
                        return true;
                    }
                }
            } catch(Exception e){
                // haha
            }
            sendToast("Wrong Username/Password");
        return false;
    }
            // save preferences
    private void saveAccPreferences() {
            Log.d("debug3", "setted to true and user is sent "+user);
        pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean("Logged",true);
        edit.putBoolean("firstTime",true);
        edit.putString("User", user);

        edit.apply();
    }
            // send all kind of toasts
    public void sendToast(String s){
        Toast toast = Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0,0);
        toast.show();
    }
        // progress bar animation
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        showProgress(false);
    }
    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
