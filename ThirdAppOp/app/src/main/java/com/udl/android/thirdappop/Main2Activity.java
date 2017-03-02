package com.udl.android.thirdappop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView texto1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        texto1 = (TextView) findViewById(R.id.text1);

        Intent newIntent = getIntent();

        Uri intentUri = newIntent.getData();
        String strinUri = intentUri.toString();
        texto1.setText(strinUri);

    }
}
