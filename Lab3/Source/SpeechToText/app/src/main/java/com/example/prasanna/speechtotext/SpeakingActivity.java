package com.example.prasanna.speechtotext;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class SpeakingActivity extends AppCompatActivity {

    Button Play, Stop, Record;
    MediaRecorder recorder;
    String outputFile;

    String fileName ="file.3gp";

    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking);


        Play = (Button)findViewById(R.id.Play);
        Stop = (Button)findViewById(R.id.Stop);
        Record = (Button)findViewById(R.id.Record);

        Stop.setEnabled(false);
        Play.setEnabled(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(SpeakingActivity.this);
        builder.setTitle("Save file");
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setMessage("Please provide the file name");

        input = new EditText(SpeakingActivity.this);
        builder.setView(input);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fileName = input.getText().toString();
                fileName = fileName + ".3gp";
                Toast.makeText(getApplicationContext(), fileName, Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        final AlertDialog ad = builder.create();


        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LabFolder/" +fileName; //file.3gp";

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(outputFile);




        Record.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                try
                {
                    recorder.prepare();
                    recorder.start();
                }
                catch (IllegalStateException e)
                {}
                catch (IOException io)
                {}
                Record.setEnabled(false);
                Stop.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Recording started", Toast.LENGTH_LONG).show();
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder.stop();
                recorder.release();
                recorder = null;
                Record.setEnabled(true);
                Stop.setEnabled(false);
                Play.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Please save the file", Toast.LENGTH_LONG).show();

                ad.show();
            }
        });
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try
                {
                    mediaPlayer.setDataSource(outputFile);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(),"Playing Audio", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {}
            }
        });
    }
}
