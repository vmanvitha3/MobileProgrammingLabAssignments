package com.example.android.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//This is the first activity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //When user clicks on SignIn button it calls below method to move from mainactivity page to SignIn page
    public void loginPage(View v){

        Intent login = new Intent(MainActivity.this, LoginPageActivity.class);
        startActivity(login);

    }

    //When user clicks on SignUp button it calls below method to move from mainactivity page to SignUp page
    public void signUpPage(View v){

        Intent register = new Intent(MainActivity.this, RegisterAccountActivity.class);
        startActivity(register);

    }

}
