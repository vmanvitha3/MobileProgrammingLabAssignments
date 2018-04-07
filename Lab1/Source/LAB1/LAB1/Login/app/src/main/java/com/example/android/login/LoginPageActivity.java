package com.example.android.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginPageActivity extends AppCompatActivity {
    private LoginButton loginButton;
    //Manages user login/logout
    private CallbackManager callbackManager;
    URL profile_pic;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //Initialize Facebbok SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //callback manager handles login responses
        callbackManager = CallbackManager.Factory.create();
        //to get user details from facebook we set readpermissions
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //a unique access token is generated for every user in encrypted format
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);
                //Data is stored in a graph in fb server
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        //Multiple data can be stored in Bundle and data is got using getFaceBookData()
                        // and gets firstname, lastname, email, profile_pic if they are present
                        //created an object "bFacebookdata" of Bundle class
                        Bundle bFacebookData = getFacebookData(object);
                        String firstname = bFacebookData.getString("first_name");
                        String lastname = bFacebookData.getString("last_name");
                        String email = bFacebookData.getString("email");
                        String profilepic = bFacebookData.getString("profile_pic");
                        Intent in = new Intent(LoginPageActivity.this, HomeActivity.class);
                        //Send data from one activity to another activity
                        in.putExtra("firstname",firstname);
                        in.putExtra("lastname",lastname);
                        in.putExtra("email",email);
                        in.putExtra("Image",profilepic);
                        startActivity(in);
                        finish();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    private Bundle getFacebookData(JSONObject object) {

        //create a bundle to store details of user and return it
        Bundle bundle = new Bundle();
        try {
            //get user id
            id = object.getString("id");
            // using id we get profile pic
            profile_pic = new URL("https://graph.facebook.com" + id + "/picture?width=100&height=100");
            Log.i("profile_pic", profile_pic + "");
            //stored profile pic in bundle using putString() from fbserver using JSON object
            bundle.putString("profile_pic", profile_pic.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        bundle.putString("idFacebook", id);
        //if JSON object has firstname
        if (object.has("first_name")) {
            try {
                // It stores firstname in bundle
                bundle.putString("first_name", object.getString("first_name"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        //if JSON object has lastname
        if (object.has("last_name")) {
            try {
                // It stores lastname in bundle
                bundle.putString("last_name", object.getString("last_name"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        //if JSON object has email
        if (object.has("email")) {
            try {
                // It stores email in bundle
                bundle.putString("email", object.getString("email"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        //returns bundle with firstname,lastname,email and profilepic
        return bundle;
    }
    //it will forward login response to the oncreate() method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        loginButton.setVisibility(View.GONE);
    }
    // when user tries to do manual login then he clicks on login button which calls this method
    public void checkCredentials(View v) {
        //Get reference to username and password using reference id
        EditText userCtrl = (EditText)findViewById(R.id.inputEmail);
        EditText passwordCtrl = (EditText)findViewById(R.id.inputPsswd);

        String userName = userCtrl.getText().toString();
        String password = passwordCtrl.getText().toString();

        //Verify if the username and password are not empty

        if( !userName.isEmpty() && !password.isEmpty()){

            //This code redirects from login page to homepage
            Intent manualLoginPage = new Intent(LoginPageActivity.this, HomeActivity.class);
            startActivity(manualLoginPage);
        }
        else if(userName.isEmpty()){
            // if username is empty, user gets a message to enter username
            Toast.makeText(getBaseContext(),"Please enter username", Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()) {
            // if password is empty, user gets a message to enter password
            Toast.makeText(getBaseContext(),"Please enter password", Toast.LENGTH_LONG).show();
        }
    }
    //when user clicks on forgot password button this method is called
    public void forgotPassword(View v){
        //This code redirects from login page to password recovery page
        Intent createNewPassword = new Intent(LoginPageActivity.this, PasswordRecovery.class);
        startActivity(createNewPassword);
    }
}
