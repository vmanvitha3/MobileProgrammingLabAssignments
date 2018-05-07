package com.example.prasanna.speechtotext;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class TaskActivity extends AppCompatActivity {

    String str = "";
    TextView textView;
    Button answer;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        textView = (TextView)findViewById(R.id.textView);
        answer = (Button)findViewById(R.id.Answer);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            str = extras.getString("question");
            //The key argument here must match that used in the other activity

            textView.setText(str.toString());
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        // Change this to match your
                        // locale
                        tts.setLanguage(Locale.US);
                        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

        }
    }

    public void RecordAnswer(View view)
    {
        Intent redirect = new Intent(this, SpeakingActivity.class);
        startActivity(redirect);
    }


}
