package com.example.arun.jsonfakeapinetworking;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends Activity {


    TextView title;
    TextView body;
    Button username;
    ListView commentlistview;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        title=findViewById(R.id.title);
        body=findViewById(R.id.body);
        username=findViewById(R.id.username);
        commentlistview=findViewById(R.id.commentlistview);
        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        title.setText(bundle.getString("title"));
        body.setText(bundle.getString("body"));
        username.setText(String.valueOf(bundle.getInt("username")));

        //        username.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });

    }

}
