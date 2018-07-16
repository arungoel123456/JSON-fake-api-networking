package com.example.arun.jsonfakeapinetworking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class gMainActivity extends AppCompatActivity {

    ListView listview;
    ArrayList<Post> posts = new ArrayList<>();
    ArrayList<String> postTitle = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,postTitle);
        listview.setAdapter(adapter);
//        Gson gson=new Gson();
//
//        PostAsyncTask task= new PostAsyncTask(new PostDownloadListener() {
//            @Override
//            public void onDownload(ArrayList<Post> titles) {
//                posts.addAll(titles);
//                for(int i=0;i<posts.size();i++)
//                {
//                    postTitle.add(posts.get(i).title);
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//        task.execute("https://jsonplaceholder.typicode.com/posts");


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PostService service = retrofit.create(PostService.class);

        Call<ArrayList<Post>> call = service.getPosts();

        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {

                posts = response.body();
                for(int i=0;i<posts.size();i++)
                {
                    postTitle.add(posts.get(i).title);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {

            }


        });





        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    Post post= posts.get(i);
                    bundle.putInt("userId", post.userId);
                    bundle.putInt("id", post.id);
                    bundle.putString("title",post.title);
                    bundle.putString("body",post.body);
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        );

    }
}
