package com.example.android.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;

public class SettingsPageActivity extends AppCompatActivity {

    SwitchCompat switch_1, switch_2, switch_3, switch_4;
    boolean stateSwitch1, stateSwitch2, stateSwitch3, stateSwitch4;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        preferences = getSharedPreferences("PREFS",0);
        //set switch states
        stateSwitch1 = preferences.getBoolean("switch1",false);
        stateSwitch2 = preferences.getBoolean("switch2",false);
        stateSwitch3 = preferences.getBoolean("switch3",false);
        stateSwitch4 = preferences.getBoolean("switch4",false);
        //Get references to SwitchCompat using reference id
        switch_1 = (SwitchCompat)findViewById(R.id.switch_1);
        switch_2 = (SwitchCompat)findViewById(R.id.switch_2);
        switch_3 = (SwitchCompat)findViewById(R.id.switch_3);
        switch_4 = (SwitchCompat)findViewById(R.id.switch_4);
        //Set states to each switch
        switch_1.setChecked(stateSwitch1);
        switch_2.setChecked(stateSwitch2);
        switch_3.setChecked(stateSwitch3);
        switch_4.setChecked(stateSwitch4);
        //check for changes on switch1
        switch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change state to false if true or true if false
                stateSwitch1 = !stateSwitch1;
                switch_1.setChecked(stateSwitch1);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch1",stateSwitch1);
                editor.apply();
            }
        });
        //check for changes on switch2
        switch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change state to false if true or true if false
                stateSwitch2 = !stateSwitch2;
                switch_2.setChecked(stateSwitch2);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch2",stateSwitch2);
                editor.apply();
            }
        });
        //check for changes on switch3
        switch_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change state to false if true or true if false
                stateSwitch3 = !stateSwitch3;
                switch_3.setChecked(stateSwitch3);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch3",stateSwitch3);
                editor.apply();
            }
        });
        //check for changes on switch4
        switch_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change state to false if true or true if false
                stateSwitch4 = !stateSwitch4;
                switch_4.setChecked(stateSwitch4);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch4",stateSwitch4);
                editor.apply();
            }
        });


    }
    //User clicks this button to return back to homepage
    public void returnHome(View v){
        //redirects user to homepage
        Intent intent = new Intent(SettingsPageActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
