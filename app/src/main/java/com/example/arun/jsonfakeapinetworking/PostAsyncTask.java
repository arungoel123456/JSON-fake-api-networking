package com.example.arun.jsonfakeapinetworking;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class PostAsyncTask extends AsyncTask<String,Void,ArrayList<Post>> {

    ArrayList<Post> posts=new ArrayList<>();

    PostDownloadListener listener;

    PostAsyncTask(PostDownloadListener listener){
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Post> doInBackground(String... strings) {
        String urlString = strings[0];
        try {
            URL url= new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String result = "";
            while (scanner.hasNext())
            {
                result = result + scanner.next();
            }

            Gson gson= new Gson();

             posts = gson.fromJson(result,new TypeToken<ArrayList<Post>>(){}.getType());
//            Log.i("Size",post.size()+"");

//            JSONArray rootObject = new JSONArray(result);
//
//            for(int i=0;i<post.size();i++)
//            {
////                JSONObject currPost=rootObject.getJSONObject(i);
////                Post post=new Post(currPost.getInt("userId"),currPost.getInt("id"), currPost.getString("title"), currPost.getString("body"));
//                posts.add(post.get(i));
//            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    protected void onPostExecute(ArrayList<Post> posts) {
        super.onPostExecute(posts);
        listener.onDownload(posts);

    }
}
