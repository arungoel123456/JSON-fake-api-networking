package com.example.arun.jsonfakeapinetworking;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class User extends Activity {

    TextView name;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
    }

}
