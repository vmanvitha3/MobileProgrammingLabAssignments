package com.example.prasanna.speechtotext;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    Button addNew;

    ArrayList<String> arrayList;
    DBClass dbObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);

        addNew = (Button)findViewById(R.id.addNew);

        arrayList = new ArrayList<String>();
        dbObj = new DBClass(this);

        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LabFolder");
        if(folder.isDirectory())
        {
            //ReadData();

            arrayList.add("Purchase");
            dbObj.Insert("Purchase", "Talk about a purchase you’ve made that you are happy with. Describe what you purchased and explain why you are happy about it.");
            arrayList.add("Generic");
            dbObj.Insert("Generic", "Talk about a time when you found a task harder to accomplish than you expected. What were you trying to accomplish? Why did it become difficult?");
            arrayList.add("Personality");
            dbObj.Insert("Personality", "What personality quality do you think is most important in a good friend? Explain why.");
            arrayList.add("Country");
            dbObj.Insert("Country", "Choose a recent event in your country that people want to talk about. Why are people interested in the event? Explain with specific details and reasons.");
            arrayList.add("Future of country");
            dbObj.Insert("Future of country", "Imagine the ways in which your country will change over the next five years. Talk about one way you expect it to change. Use details to explain your answer.");
            arrayList.add("Famous person");
            dbObj.Insert("Famous person", "Choose a famous person who you think would be enjoyable to have a conversation with. Explain why you would like to talk with them, using specific details and reasons.");
            arrayList.add("School");
            dbObj.Insert("School", "Choose a subject that students study in school but you think is not important. Explain why you feel it is not important to study. Use details and examples in your response.");
            arrayList.add("Transportation");
            dbObj.Insert("Transportation", "What type of long-distance transportation do you think is most enjoyable? Explain why you like it with specific details and examples.");
            arrayList.add("Movie");
            dbObj.Insert("Movie", "Think of a movie you have not seen but would like to see. Explain why you expect to like the movie. Give specific details and reasons in your response.");
            Log.e("ArrayLength",String.valueOf(arrayList.size()));


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList)
            {
                public View getView(int position, View convertView, ViewGroup parent)
                {

                    View view = super.getView(position, convertView, parent);

                    TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);

                    ListItemShow.setTextColor(Color.parseColor("#ffffff"));

                    return view;
                }};

            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, textView1, arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int position = i;
                    String val = (String)listView.getItemAtPosition(position);
                    Intent redirect = new Intent(MainActivity.this, TaskActivity.class);

                    if(position>0) {

                        Cursor cursor = dbObj.getData(val);
                        cursor.moveToFirst();
                        String question = cursor.getString(cursor.getColumnIndex(DBClass.columnQuestion));
                        redirect.putExtra("question", question);
                        startActivity(redirect);

                    }
                }
            });
        }
        else
        {
            File fileDirectory = Environment.getExternalStorageDirectory();
            fileDirectory = new File(fileDirectory+"/music");
            File fileList[] = fileDirectory.listFiles();
            String fileName = fileList[0].getAbsolutePath();
            File LabFolder = new File("/sdcard/LabFolder/");
            LabFolder.mkdir();
            File outputFile = new File(LabFolder, fileName);
            try
            {
                FileOutputStream fo = new FileOutputStream(outputFile);
            }
            catch (FileNotFoundException ex)
            {}
        }
    }


    public void ReadData()
    {
        File fileDirectory = Environment.getExternalStorageDirectory();
        fileDirectory = new File(fileDirectory+"/LabFolder");
        File fileList[] = fileDirectory.listFiles();
        for(int i = 0; i<fileList.length; i++)
        {
            arrayList.add(fileList[i].getName());
            dbObj.Insert(fileList[i].getName(), fileList[i].getAbsolutePath());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        listView.setAdapter(adapter);
    }

    public void addItem(View view)
    {
        Intent redirect = new Intent(MainActivity.this, TaskActivity.class);
        startActivity(redirect);
    }


}
