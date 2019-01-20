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

public class JsonDemo extends AppCompatActivity {

    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> idname = new ArrayList<>();
    ArrayList<String> idroll = new ArrayList<>();
    ArrayList<String> idstd = new ArrayList<>();

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_demo);

        listview = (ListView) findViewById(R.id.listview);

        new getdata().execute();
    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public class getdata extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            ids.clear();
            idname.clear();
            idroll.clear();
            idstd.clear();
            String response = "";
            try {
                response = run("http://192.168.43.87/IOT/service_1.php?action=getradhika");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobj1 = jsonArray.getJSONObject(i);
                    String id = jobj1.getString("id");
                    String name = jobj1.getString("name");
                    String rollno = jobj1.getString("roll");
                    String std = jobj1.getString("std");

                    ids.add(id);
                    idname.add(name);
                    idroll.add(rollno);
                    idstd.add(std);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(JsonDemo.this, ids.toString(), Toast.LENGTH_LONG).show();

            JsonDemo_adaptor adaptor=new JsonDemo_adaptor(JsonDemo.this,idname,idroll,idstd);
            listview.setAdapter(adaptor);


            Log.d("he33", ids.toString());
        }
    }
}
