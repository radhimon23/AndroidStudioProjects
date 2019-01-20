package com.example.gauravkapadiya.home;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonDemo1 extends AppCompatActivity {              //JsonDemo and Jsondemo1 class fetch d data into AS from phpmyadmin..

    ArrayList<String> ids1=new ArrayList<>();
    ArrayList<String> idname1=new ArrayList<>();
    ArrayList<String> idroll1=new ArrayList<>();
    ArrayList<String> idstd1 = new ArrayList<>();

    private ListView listview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {                //Jsondemo and jsondemo1 are similar classes..
        super.onCreate(savedInstanceState);                                 //jsondemo done by mam ,jsondemo1 done by me...
        setContentView(R.layout.activity_json_demo1);

        listview1=(ListView)findViewById(R.id.listview1);
        new getdata1().execute();
    }

    OkHttpClient client1=new OkHttpClient();

    String run(String url) throws IOException {           //this method available online.....
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response1 = client1.newCall(request).execute();
        return response1.body().string();
    }

    public class getdata1 extends AsyncTask<Void,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            ids1.clear();
            idname1.clear();
            idroll1.clear();
            idstd1.clear();

            String response1="";
            //String status="";
            try {
                response1 = run("http://192.168.43.87/IOT/service_1.php?action=getradhika");//only change this string for insert update and delete and get data..
            } catch (IOException e) {
                e.printStackTrace();
            }


           // JSONObject jobj1= null;
            try {
                JSONObject jobj1 = new JSONObject(response1);
                JSONArray jsonArray1=jobj1.getJSONArray("data");                //this loop for insert,delete and update.


               // JSONObject jobj2=jsonArray1.getJSONObject(0);                   //run for loop from Jsondemo class for get data...
                //status=jobj2.getString("staus");
                for (int i = 0; i < jsonArray1.length(); i++) {

                    JSONObject jobj2 = jsonArray1.getJSONObject(i);
                    String id = jobj2.getString("id");
                    String name = jobj2.getString("name");
                    String rollno = jobj2.getString("roll");
                    String std = jobj2.getString("std");

                    ids1.add(id);
                    idname1.add(name);
                    idroll1.add(rollno);
                    idstd1.add(std);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(JsonDemo1.this, ids1.toString(), Toast.LENGTH_LONG).show();

            JsonDemo1_adaptor adaptor1 = new JsonDemo1_adaptor(JsonDemo1.this,idname1,idroll1);
            listview1.setAdapter(adaptor1);
            Log.d("he33",ids1.toString());
        }
    }
}
