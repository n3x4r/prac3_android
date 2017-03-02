package com.udl.android.thirdappop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (TextView) findViewById(R.id.text);

        Intent newIntent = getIntent();
        if(newIntent.hasExtra(Intent.EXTRA_TEXT)){
            String broadcast = newIntent.getStringExtra(Intent.EXTRA_TEXT);
            texto.setText(broadcast);
        }

    }
}
