package com.example.zarkovic.myapplication12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class AfterLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        //potrebno setovanje Bundle da se preuzme username String i ispise u txtview-u
        TextView dobrodoslica = (TextView) findViewById(R.id.dobrodoslica2);

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
