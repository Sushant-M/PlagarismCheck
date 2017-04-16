package com.example.sushant.plagarismcheck;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    String TAG = "MAINACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String filename = "content_file";
        String string = getResources().getString(R.string.to_check);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void check_plagirism(View view){
        EditText text = (EditText)findViewById(R.id.editText);
        String to_compare = String.valueOf(text.getText());
        String original = getResources().getString(R.string.to_check);

        String delims = "[.]";

        String[] tokens_recieved = to_compare.split(delims);
        String[] tokens_actual = original.split(delims);

        float total = 3;
        float counter = 0;

        for(int i = 0; i<3;i++){
            if(tokens_recieved[i].equals(tokens_actual[i])){
                counter++;
                Log.d(TAG,"Added to counter");
            }
            Log.d(TAG, tokens_recieved[i]);
            Log.d(TAG, tokens_actual[i]);
        }
        float calculated_out = counter/total *100;

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(String.valueOf(calculated_out));
    }
}
