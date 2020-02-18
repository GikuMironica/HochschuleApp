package com.example.gicum.hochschuleappx;

        import android.app.AlertDialog;
        import android.app.SearchManager;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.GridView;
        import android.widget.SearchView;
        import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    GridView gridView;
    TextView gridOption;
    String option;
    String us;
    String ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("activities", "main activity created");

            // set the toolbar as a default
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

            // images of the gridview
        int[] images = {
                R.drawable.newspaper, R.drawable.moodle, R.drawable.test,
                R.drawable.email, R.drawable.user, R.drawable.service
        };
            // names of the icons
        String[] names = {
            "News", "Moodle", "Exams Schedule", "WebMail", "Account", "Support"
        };
            // link the gridview with java
        gridView = findViewById(R.id.mainGrid);
            // use this adapter for the main grid
        GridAdapter gridA = new GridAdapter(this, names, images);
        gridView.setAdapter(gridA);

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        gridOption = view.findViewById(R.id.gridOption);
                        option = gridOption.getText().toString();
                        Log.d("Selected", option);
                        switch(option){
                            case "News": {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://studium.hs-ulm.de/de/Seiten/UebersichtNews.aspx"));
                                startActivity(intent);
                                break;
                            }
                            case "Account":{
                                Intent intent = new Intent(MainActivity.this, Account.class);
                                Intent in = getIntent();
                                String user;
                                user = in.getStringExtra("user");
                                intent.putExtra("user",user);
                                user = in.getStringExtra("name");
                                intent.putExtra("name",user);
                                startActivity(intent);
                                break;
                            }
                            case "Moodle": {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://moodle-hs-ulm.de/login/index.php"));
                                startActivity(intent);
                                break;
                            }
                            case "WebMail":{
                                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://webmail.hs-ulm.de/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fwebmail.hs-ulm.de%2fowa%2f"));
                                startActivity(intent2);
                                break;
                            }
                            case "Exams Schedule":{
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hs-ulm.de/en/Fakultaet/Informatik/Pr%c3%bcfungen/"));
                                startActivity(intent);
                                break;
                            }
                            case "Support":{
                                confirmBox();
                                break;
                            }
                            default: {
                                break;
                            }
                        }

                    }
                }
        );                          // main grind view click listener ends here


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

            // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.Search).getActionView();
        searchView.setQueryHint(getString(R.string.Search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search","starting intent with search query = "+query);
                String websearch = "https://studium.hs-ulm.de/de/Seiten/Suchergebnis.aspx?k="+query;
                Intent intentN = new Intent(Intent.ACTION_VIEW, Uri.parse(websearch));
                startActivity(intentN);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                    //
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Settings: {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);
            }
            case R.id.info: {
                Intent intent = new Intent(MainActivity.this, Info.class);
                Intent in = getIntent();
                us = in.getStringExtra("user");
                ps = in.getStringExtra("pass");
                intent.putExtra("name",us);
                intent.putExtra("password",ps);
                Log.d("info1",us+" "+ps+ "MainActiv");
                startActivity(intent);
                return super.onOptionsItemSelected(item);
            }
            default: {

                return super.onOptionsItemSelected(item);
            }
        }
    }


    public void confirmBox(){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(getString(R.string.callSC));
        alertbox.setCancelable(false);

        alertbox.setPositiveButton(getString(R.string.Ja), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:073150208"));
                startActivity(intent);
            }
        });
        alertbox.setNegativeButton(getString(R.string.Nein), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // just cancel
            }
        });

        alertbox.create().show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
