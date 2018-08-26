package com.example.zarkovic.myapplication12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginLayout1Activity extends AppCompatActivity {
    String name = "admin";
    String pass = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout1);

        Button prijava = (Button) findViewById(R.id.prijava);

        prijava.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                if (name.equals(username.getText().toString().trim()) && pass.equals(password.getText().toString().trim())) {
                    //msg1.setText("");
                    Toast.makeText(getApplicationContext(),"Uspešno ste ulogovani", Toast.LENGTH_LONG).show();
                    //vracam na home, potreban je novi activity
                    //sa "Dobro dosli 'IME'" (potrebno je da uzmem vrednost stringa za login)

                    Intent i = new Intent(getApplicationContext(), AfterLoginActivity.class);
                    startActivity(i);
                    finish();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            //new home_fragment()).commit();
                } else {
                    if(name.equals(username.getText().toString().trim()) &&
                            !pass.equals(password.getText().toString().trim())) {
                        Toast.makeText(getApplicationContext(),"Netačna lozinka",
                                Toast.LENGTH_LONG).show();
                        //msg1.setTextColor(Color.red(3));
                        //msg1.setText("Netacna šifra");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Uneli ste netačne podatke",
                                Toast.LENGTH_LONG).show();
                        //msg1.setText("Uneli ste netacne podatke");
                        //napisati kada su netacni podaci u textviewu
                    }
                }
            }
        });
    }
}
