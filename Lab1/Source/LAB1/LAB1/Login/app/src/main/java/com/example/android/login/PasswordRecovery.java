package com.example.android.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordRecovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
    }
    public void updatePassword(View v){

        //Get references to controls like EditText's using respective id's from xml layout
        EditText newPsswdCtrl = (EditText)findViewById(R.id.NewPsswd);
        EditText confirmPsswdCtrl = (EditText)findViewById(R.id.confirmPsswd);

        //Initialize variables with user entered values
        String newPsswd = newPsswdCtrl.getText().toString();
        String confirmPsswd = confirmPsswdCtrl.getText().toString();

        //Verify if the username and password are not empty

        if( !newPsswd.isEmpty() && !confirmPsswd.isEmpty()){

            //This code redirects from password recovery page to homepage
            Intent homePage = new Intent(PasswordRecovery.this, HomeActivity.class);
            startActivity(homePage);
        }
        else if(newPsswd.isEmpty()){
            //Toast sends user an appropriate mesaage in case if new password is empty
            Toast.makeText(getBaseContext(),"Please enter new password", Toast.LENGTH_LONG).show();
        }
        else if(confirmPsswd.isEmpty()) {
            //Toast sends user an appropriate mesaage in case if confirm password is empty
            Toast.makeText(getBaseContext(),"Please confirm password", Toast.LENGTH_LONG).show();
        }



    }
}
