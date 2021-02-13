package com.projects.anoop.avsolutions.touristattractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.anoop.avsolutions.touristattractionapp.session_manager.Session;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String,String> usernamePassword;

    EditText email,password;
    String strEmail,strPass;
    CheckBox checkBox;
    boolean isChecked;
    TextView resulsLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        checkBox = findViewById(R.id.checkbox);

        usernamePassword = new HashMap<String, String>();

        usernamePassword.put("thanos@gmail.com","1234");
        usernamePassword.put("wonderwoman@yahoo.com","abc!!");

    }

    private  void doLogin()
    {
        if(usernamePassword.containsKey(strEmail) &&
                usernamePassword.get(strEmail).equals(strPass))
        {
            if(isChecked)
            {
                Toast.makeText(MainActivity.this,"checked",Toast.LENGTH_SHORT).show();
                Session session = new Session(MainActivity.this);
                session.setValues(strEmail);
            }

            Intent i = new Intent(MainActivity.this,TouristList.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(MainActivity.this,"Email or password not valid",Toast.LENGTH_SHORT).show();
        }

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        isChecked = ((CheckBox) view).isChecked();
        if(isChecked)
        {
            Toast.makeText(MainActivity.this,"checked",Toast.LENGTH_SHORT).show();
        }
    }


    public void login(View view) {

        strEmail = email.getText().toString().trim();
        strPass = password.getText().toString().trim();

        if(strPass.isEmpty() || strEmail.isEmpty())
        {
            Toast.makeText(MainActivity.this,"Email or password not valid",Toast.LENGTH_SHORT).show();
            return;
        }

        doLogin();

    }

    @Override
    protected void onStart() {

        Session session_ = new Session(MainActivity.this);
        if(session_.isIsLogin())
        {
            Intent intent = new Intent(MainActivity.this,TouristList.class);
            startActivity(intent);
            finish();
        }
        super.onStart();
    }
}
