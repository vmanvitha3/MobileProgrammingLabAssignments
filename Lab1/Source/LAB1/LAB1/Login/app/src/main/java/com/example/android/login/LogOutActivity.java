package com.example.android.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
    }

    //If user clicks on Logout button then below method is called
    public void logOutSubmit(View v){
        // Redirect page to MainActivity page if user selects to logout
        Intent redirectHome = new Intent(LogOutActivity.this, MainActivity.class);
        startActivity(redirectHome);

    }
    //If user clicks on cancel Logout option then below method is called
    public void cancelSubmit(View v){
        // Redirect page to HomeActivity page if user does not want to logout
        Intent redirectLogOut = new Intent(LogOutActivity.this, HomeActivity.class);
        startActivity(redirectLogOut);

    }
}
