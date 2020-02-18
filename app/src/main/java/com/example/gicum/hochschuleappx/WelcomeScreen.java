package com.example.gicum.hochschuleappx;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {
    private View progressBar;
    private TextView nameUser;
    private TextView welcome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        welcome = findViewById(R.id.welcome);
            // set name of the user on the welcome screen
        welcome.setText("Welcome,");
        Intent intent = getIntent();
        String userN = intent.getStringExtra("name");
        nameUser = findViewById(R.id.nameUser);
        nameUser.setText(userN);

            // link object with xml view
        progressBar = findViewById(R.id.progressBar);
            // run animation
        showProgress(true);

            // create thread for making program sleep 2 sec, so we can see animation, stuff
        Runnable r = new Runnable() {
            @Override
            public void run(){
                Intent in = getIntent();
                String inp = in.getStringExtra("user");
                // jump to the main activity
                Intent main = new Intent(WelcomeScreen.this, MainActivity.class);
                main.putExtra("user",inp);
                main.putExtra("name", in.getStringExtra("name"));
                main.putExtra("pass",in.getStringExtra("pass"));
                Log.d("info1",in.getStringExtra("name")+" "+in.getStringExtra("pass")+ " WelcomeActiv");
                startActivity(main);
                finish();
            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 2000);

    }

    // progress bar animation
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

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
}
