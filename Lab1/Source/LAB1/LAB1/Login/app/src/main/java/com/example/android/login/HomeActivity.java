package com.example.android.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle homeToggle;
    ImageView imageView;
    TextView textname, textemail;
    Button button;
    String firstname;
    String lastname;
    String email;
    String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //we select DrawerLayout View
        drawerLayout = (DrawerLayout)findViewById(R.id.navDrawer);

        // create a ActionBarDrawerToggle() instance using both activities open and close defined in strings.xml
        homeToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        //Using listener we keep track of events and send appropriate response back
        drawerLayout.addDrawerListener(homeToggle);
        //It syncs when user clicks on the button
        homeToggle.syncState();
        //User gets a back button to go back to previous state
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        // "this" will take all the items when we click on them
        navigationView.setNavigationItemSelectedListener(this);

        //Get references to ImageView, TextView's and button using reference id
        imageView = (ImageView)findViewById(R.id.imageView);
        textname = (TextView)findViewById(R.id.textView);
        textemail = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button2);

        //Get information from previous activity and store them in variables
        firstname = getIntent().getExtras().getString("firstname");
        lastname = getIntent().getExtras().getString("lastname");
        email = getIntent().getExtras().getString("email");
        image = getIntent().getExtras().getString("Image");

        //Check if first and lastname is null
        if(firstname!=null && lastname!=null){
            //if not null make those view's visible and set them with value
            textname.setVisibility(View.VISIBLE);
            textname.setText(firstname+" "+lastname);
        }
        else{
            //If they are null send appropriate message to user
            Toast.makeText(getApplicationContext(),"Name Not Available",Toast.LENGTH_SHORT).show();
        }
        //Check if email is null
        if(email!=null){
            //if not null make those view's visible and set them with value
            textemail.setVisibility(View.VISIBLE);
            textemail.setText(email);
        }
        else{
            //If they are null send appropriate message to user
            Toast.makeText(getApplicationContext(),"Email Not Available",Toast.LENGTH_SHORT).show();
        }
        //Check if image is null
        if(image!=null){
            //if not null make those view's visible and set them with value
            imageView.setVisibility(View.VISIBLE);
            Glide.with(HomeActivity.this).load(image).into(imageView);
            //Glide.with(getApplicationContext()).load(image).into(imageView);
        }
        else{
            //If they are null send appropriate message to user
            Toast.makeText(getApplicationContext(),"Image Not Available",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (homeToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        //if home item is selected from menu
        if(id == R.id.home){
            //Close Drawer first
            drawerLayout.closeDrawers();
            //then redirect to home page
            redirectTohomePage();
        }
        //if help item is selected from menu
        else if(id == R.id.help){
            //Close Drawer first
            drawerLayout.closeDrawers();
            //then redirect to Help page
            redirectToHelpPage();
        }
        //if settings item is selected from menu
        else if(id == R.id.setting){
            //Close Drawer first
            drawerLayout.closeDrawers();
            //then redirect to settings page
            redirectToSettingsPage();
        }
        //if logout item is selected from menu
        else if(id == R.id.logout){
            //Close Drawer first
            drawerLayout.closeDrawers();
            //then redirect to logout page
            redirectToLogOutPage();
        }
        return false;
    }

    private void redirectTohomePage() {
        //This code redirects back to home page
        Intent redirect = new Intent(HomeActivity.this, HomeActivity.class);
        //if firstname, lastname, email or image is not null then u send their information to distplay on homepage
        if(firstname!=null || lastname!=null || email!=null || image!=null){

            redirect.putExtra("firstname",firstname);
            redirect.putExtra("lastname",lastname);
            redirect.putExtra("email",email);
            redirect.putExtra("Image",image);
        }

        startActivity(redirect);

    }
    //this method is called when user selects help from navigation menu
    private void redirectToHelpPage() {
        //This code redirects from homepage to help page
        Intent helpPage = new Intent(HomeActivity.this, HelpPageActivity.class);
        startActivity(helpPage);

    }
    //this method is called when user selects settings from navigation menu
    private void redirectToSettingsPage() {
        //This code redirects from homepage to settings page
        Intent settingsPage = new Intent(HomeActivity.this, SettingsPageActivity.class);

        startActivity(settingsPage);

    }
    //this method is called when user selects logout from navigation menu
    private void redirectToLogOutPage() {
        //This code redirects from homepage to logout page
        Intent logoutPage = new Intent(HomeActivity.this, LogOutActivity.class);
        startActivity(logoutPage);

    }
    //this method is called when user selects fb logout button in homepage
    public void logOut(View v){
        LoginManager.getInstance().logOut();
        //This code redirects from homepage to mainactivity page
        Intent login = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(login);
        finish();
    }
}
