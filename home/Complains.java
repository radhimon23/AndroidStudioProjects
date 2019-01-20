package com.example.gauravkapadiya.home;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gauravkapadiya.home.Utils.complains_adaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Complains extends AppCompatActivity {

    ArrayList<String> image = new ArrayList<>();
    ArrayList<String> username = new ArrayList<>();
    ArrayList<String> complain_name = new ArrayList<>();

    ListView listofcomplains1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complainsactivity);
        listofcomplains1 = (ListView) findViewById(R.id.listofcomplains);


        new getcomplaindata().execute();

    }

    OkHttpClient client1 = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client1.newCall(request).execute();
        return response.body().string();
    }

    public class getcomplaindata extends AsyncTask<Void,String,String>{

        protected void onPreExecute(){
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Void... voids) {
            image.clear();
            username.clear();
            complain_name.clear();

            String response ="";

            try {
                response = run("http://192.168.0.3/IOT/service_2.php?action=getcomplain");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                JSONObject jobj4=new JSONObject(response);
                JSONArray jArray = jobj4.getJSONArray("data");

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject jobj5 = jArray.getJSONObject(i);

                    String image1 = jobj5.getString("image");
                    String username1 = jobj5.getString("first_name");
                    String complain_name1 = jobj5.getString("service_name");

                    image.add(image1);
                    username.add(username1);
                    complain_name.add(complain_name1);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            complains_adaptor adaptorcom;
            adaptorcom = new complains_adaptor(Complains.this,image,username,complain_name);
            listofcomplains1.setAdapter(adaptorcom);
        }
    }



}
