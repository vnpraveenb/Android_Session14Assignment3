package com.example.praveen.session14assignment3;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    Button saveFileButton, checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References the button view objects from buttons in the layout
        saveFileButton =  findViewById(R.id.saveFileButton);
        checkButton =  findViewById(R.id.checkButton);

        saveFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Writes to Internal Storage
                writeToInternalStorage(getApplicationContext(),"abc.txt","Some Text");
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gets Absolute App Path including the File Name and folder.
                String path = getApplicationContext().getFilesDir().getAbsolutePath() + "/appDir/abc.txt";
                // Retrieves File based on the path.
                File file = new File(path);
                if (file.exists()) // If exists displays file exists toast
                    Toast.makeText(MainActivity.this,  "File Found", Toast.LENGTH_LONG).show();
                else // else displays file not found toast/
                    Toast.makeText(MainActivity.this, "File Not Found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void writeToInternalStorage(Context context, String fileName, String contentsToWrite){
        // Creates file object with current context and required folder.
        File file = new File(context.getFilesDir(),"appDir");

        // If directory does not exist, just create directory.
        if(!file.exists()){
            file.mkdir();
        }
        try{
            // Creates File Object.
            File textFile = new File(file,fileName);
            // Writes Data into file
            FileWriter fileWriter =new FileWriter(textFile);

            // Adds Contents into file
            fileWriter.append(contentsToWrite);

            // Closes File Writer Object
            fileWriter.flush();
            fileWriter.close();


            // Displays Toast.
            Toast.makeText(context, "File Created...", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}