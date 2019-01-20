package com.example.gauravkapadiya.home;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Add_newservice extends AppCompatActivity {

    Spinner spinner;
    OkHttpClient client = new OkHttpClient();
    ArrayList<String> ser_cat_id=new ArrayList<>();
    ArrayList<String> service_cat_name=new ArrayList<>();
    String selected_cat_id="",servicename="";
    EditText edittext,editText1;
    LinearLayout cat_layout;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newservice);

        spinner=(Spinner)findViewById(R.id.spinner);
        edittext = (EditText)findViewById(R.id.editText);
        add=(Button)findViewById(R.id.button5);

        cat_layout = (LinearLayout)findViewById(R.id.cat_layout);
        editText1 = (EditText)findViewById(R.id.editText1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edittext.getText().toString().trim().equals(""))
                {
                    edittext.setError("Please Enter Service Name");
                }
                else {
                    if(cat_layout.getVisibility()==View.VISIBLE){
                        if(editText1.getText().toString().trim().equals("")){
                            editText1.setError("Please Enter Category Name");
                        }
                        else {
                            servicename=edittext.getText().toString();
                        }
                    }
                    servicename=edittext.getText().toString();
                    new adddata().execute();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_cat_id=ser_cat_id.get(i);
                if(service_cat_name.get(i).equals("Other")){
                    cat_layout.setVisibility(View.VISIBLE);
                }
                else {
                    cat_layout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        new getspinerdata().execute();
    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public class getspinerdata extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            ser_cat_id.clear();
            service_cat_name.clear();

            String response = "";
            try {
                response = run("http://172.20.10.9/IOT/service_2.php?action=getservice_category");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jobj1 = jsonArray.getJSONObject(i);
                    String id = jobj1.getString("id");
                    String service_category_name = jobj1.getString("service_cat_name");


                    ser_cat_id.add(id);
                    service_cat_name.add(service_category_name);


                }
            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

            return "valid";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           if(s.equals("valid")){
               ArrayAdapter adapter=new ArrayAdapter(Add_newservice.this,android.R.layout.simple_spinner_dropdown_item,service_cat_name);
               spinner.setAdapter(adapter);
               selected_cat_id=ser_cat_id.get(0);
           }
        }
    }

    public class adddata extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String response = "";
            String status="";
            try {
                response = run("http://172.20.10.9/IOT/service_2.php?action=insertservice_cat_detail&service_id="+selected_cat_id+"&service_name="+servicename);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");

                JSONObject jobj1 = jsonArray.getJSONObject(0);
                status=jobj1.getString("status");

            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

            return status;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("valid")){
                Toast.makeText(Add_newservice.this, "Added Successfully...", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }



}
