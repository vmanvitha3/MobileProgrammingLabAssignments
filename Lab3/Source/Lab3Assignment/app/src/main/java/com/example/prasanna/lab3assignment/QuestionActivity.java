package com.example.prasanna.lab3assignment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "QuestionActivity";
    DatabaseHelper mDatabaseHelper;
    TextToSpeech tts;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    TextView mVoiceInput;
    ImageButton mSpeakBtn;
    Toolbar mToolbar;
    TextView mTextView;
    Button listenbtn;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        preferences = getApplicationContext().getSharedPreferences("pref", getApplicationContext().MODE_PRIVATE);
        editor = preferences.edit();

        tts = new TextToSpeech(this, this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        mTextView = (TextView) findViewById(R.id.txtView);
        mVoiceInput = (TextView) findViewById(R.id.editTxt);
        listenbtn = (Button) findViewById(R.id.listenBtn);
        mSpeakBtn = (ImageButton) findViewById(R.id.speakBtn);
        mDatabaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("topics"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("country")) {
                mTextView.setText("Choose a recent event in your country that people want to talk about. Why are people interested in the event? Explain with specific details and reasons.");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Tourism")) {
                mTextView.setText("Choose a place to talk about tourist attractions");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("NASA")) {
                mTextView.setText("Choose a recent event in your country that people want to talk about NASA. Explain with specific details and reasons.");
            }

        }
    }
    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData){
            toastMessage("Data Successfully Inserted");
        }
        else {
            toastMessage("Something Went Wrong while inserting data");
        }
    }

    private void toastMessage(String s) {
        Toast.makeText(this, s ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                listenbtn.setEnabled(true);
                speak();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    public void listenQuestion(View view) {
        speak();
    }

    public void speakAnswer(View v) {
        startVoiceInput();
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please Answer");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    private void speak() {
        String text = mTextView.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //mVoiceInput.setText(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInput.setText(result.get(0));
                    String newEntry = mVoiceInput.getText().toString();
                    if(!newEntry.isEmpty()){
                        AddData(newEntry);
                    }
                    else{
                        toastMessage("Speak something");
                    }
                }
            }
        }
    }
}
