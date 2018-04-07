package com.example.android.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HelpPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);

        //Get references to WebView using respective id from xml layout
        WebView webView = (WebView)findViewById(R.id.web_ViewId);
        //it will display URL in our app but not in external browser
        webView.setWebViewClient(new WebViewClient());
        //When HelpPage is loaded it will display below mentioned URL using webview
        webView.loadUrl("https://developer.android.com/support.html");
    }
    public void helpPage(View v){

    }
}
