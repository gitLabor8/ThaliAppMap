package com.example.AppArt.thaliapp.Settings.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AppArt.thaliapp.FoodList.Activities.Restaurant;
import com.example.AppArt.thaliapp.Calendar.Activities.Calendar;
import com.example.AppArt.thaliapp.R;

/**
 * Loginscreen for the admin
 *
 * @author Frank Gerlings (s4384873), Lisa Kalse (s4338340), Serena Rietbergen (s4182804)
 */

public class Login extends ActionBarActivity {

    private final String inlog = "admin";
    private final String wachtwoord = "admin";
    private EditText name;
    private EditText password;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "Settings";

    /**
     *
     * @param savedInstanceState, the saved instances
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlog);
        name = (EditText) findViewById(R.id.Inlognaam);
        password = (EditText) findViewById(R.id.Wachtwoord);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Log in");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E61B9B")));
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu, the menu that needs to be created
     * @return whether it succeeded
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     * as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item, the item in the menu
     * @return if the action succeeded
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Calendar:
                Intent intent1 = new Intent(this, Calendar.class);
                startActivity(intent1);
                break;
            case R.id.Restaurant:
                Intent intent2 = new Intent(this, Restaurant.class);
                startActivity(intent2);
                break;
            case R.id.Settings:
                Intent intent4 = new Intent(this, Settings.class);
                startActivity(intent4);
                break;
        }
        return true;
    }

    /**
     * Function that is called when you click on the button Send,
     * It checks whether you are allowed to enter the class with all of the receipts
     *
     * @param v: the view of the Inlog activity
     */
    public void send(View v) {
        String s1, s2;

        s1 = name.getText().toString();
        s2 = password.getText().toString();
        if (s1.equals(inlog) && s2.equals(wachtwoord)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("access", true);
            editor.commit();
            Intent i = new Intent(this, Overview.class);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, "U heeft een foutieve combinatie van gebruikersnaam en wachtwoord. " +
                    "Probeer opnieuw.", Toast.LENGTH_SHORT).show();
        }
    }
}
