package com.example.android.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
    }
    public void registerAccount(View v){

        //Get references to controls like EditText's using respective id's from xml layout
        EditText NameCtrl = (EditText)findViewById(R.id.input_Name);
        EditText emailIdCtrl = (EditText)findViewById(R.id.input_Email);
        EditText userCtrl = (EditText)findViewById(R.id.NewUserName);
        EditText passwordCtrl = (EditText)findViewById(R.id.NewPassword);

        //Initialize variables with user entered values
        String name = NameCtrl.getText().toString();
        String emailId = emailIdCtrl.getText().toString();
        String userName = userCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        //Verify if the username and password are not empty

        if( !name.isEmpty() && !emailId.isEmpty() && !userName.isEmpty() && !password.isEmpty()){

            //This code redirects from registration page to homepage
            Intent regLoginPage = new Intent(RegisterAccountActivity.this, HomeActivity.class);
            startActivity(regLoginPage);
        }
        else if(name.isEmpty()){
            //Toast sends user an appropriate mesaage in case if name is empty
            Toast.makeText(getBaseContext(),"Please enter Name", Toast.LENGTH_LONG).show();
        }
        else if(emailId.isEmpty()) {
            //Toast sends user an appropriate mesaage in case if email address is empty
            Toast.makeText(getBaseContext(),"Please enter Email Address", Toast.LENGTH_LONG).show();
        }
        else if(userName.isEmpty()){
            //Toast sends user an appropriate mesaage in case if username is empty
            Toast.makeText(getBaseContext(),"Please enter username", Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()) {
            //Toast sends user an appropriate mesaage in case if password is empty
            Toast.makeText(getBaseContext(),"Please enter password", Toast.LENGTH_LONG).show();
        }
    }
}
