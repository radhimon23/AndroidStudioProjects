package com.example.gauravkapadiya.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Updation extends AppCompatActivity {


    EditText UFname, UMname, ULname, UGender, UEmail, UMobile, UE_pwd, UC_pwd, UAddress;
    Button uUpdate;
    String id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updation);


        UFname = (EditText) findViewById(R.id.uFname);
        UMname = (EditText) findViewById(R.id.uMname);
        ULname = (EditText) findViewById(R.id.uLname);
        UGender = (EditText) findViewById(R.id.uGender);
        UEmail = (EditText) findViewById(R.id.uEmail);
        UMobile = (EditText) findViewById(R.id.uMobile);
        UE_pwd = (EditText) findViewById(R.id.uEpwd);
        UC_pwd = (EditText) findViewById(R.id.uCpwd);
        UAddress = (EditText) findViewById(R.id.uAddres);
        uUpdate = (Button) findViewById(R.id.uUpdate);


        new getdata().execute();


        uUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UFname.getText().toString().trim().equals("")) {
                    UFname.setError("Please enter first name");
                    UFname.requestFocus();
                } else if (UMname.getText().toString().trim().equals("")) {
                    UMname.setError("Please enter Middle name");
                    UMname.requestFocus();
                } else if (ULname.getText().toString().trim().equals("")) {
                    ULname.setError("Please enter Last name");
                    ULname.requestFocus();
                } else if (UGender.getText().toString().trim().equals("")) {
                    UGender.setError("Please enter your Gender");
                    UGender.requestFocus();
                } else if (UEmail.getText().toString().trim().equals("")) {
                    UEmail.setError("Please enter your Email");
                    UEmail.requestFocus();
                }
                else if(!isvalidemail(UEmail.getText().toString().trim()))
                {
                    UEmail.setError("Please enter valid Email");
                    UEmail.requestFocus();
                }
                else if (UMobile.getText().toString().trim().equals("")) {
                    UMobile.setError("Please enter your mobile no.");
                    UMobile.requestFocus();
                } else if (UE_pwd.getText().toString().trim().equals("")) {
                    UE_pwd.setError("Please enter your password");
                    UE_pwd.requestFocus();
                } else if (UE_pwd.getText().toString().trim().length() < 6 || UE_pwd.getText().toString().trim().length() > 15) {
                    UE_pwd.setError("Password must be in 6 to 15 digit");
                    UE_pwd.requestFocus();
                } else if (UC_pwd.getText().toString().trim().equals("")) {
                    UC_pwd.setError("Please enter confirm password");
                    UC_pwd.requestFocus();
                } else if (!UE_pwd.getText().toString().trim().equals(UC_pwd.getText().toString().trim())) {
                    UC_pwd.setError("your password does not match");
                    UC_pwd.requestFocus();
                }
                else{
                   new updatedata().execute();

                }
            }
        });


    }

    private boolean isvalidemail(String email)
    {
        //String email_ptn="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String email_ptn = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        Pattern pattern = Pattern.compile(email_ptn);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

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


        String fname, mname, lname, gender, emai, phone, addre, pasword;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String response = "";
            try {
                response = run("http://172.20.10.9/IOT/service_2.php?action=getregistration&id=" + id);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");


                // for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jobj1 = jsonArray.getJSONObject(0);
                fname = jobj1.getString("first_name");
                mname = jobj1.getString("middle_name");
                lname = jobj1.getString("last_name");
                gender = jobj1.getString("gender");
                emai = jobj1.getString("email_id");
                phone = jobj1.getString("mobile_no.");
                addre = jobj1.getString("user_address");
                pasword = jobj1.getString("password");


                // }
            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

            return "valid";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("valid")) {

                UFname.setText(fname);
                UMname.setText(mname);
                ULname.setText(lname);
                UGender.setText(gender);
                UEmail.setText(emai);
                UMobile.setText(phone);
                UE_pwd.setText(pasword);
                UC_pwd.setText(pasword);
                UAddress.setText(addre);

            } else {
                Toast.makeText(Updation.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        }


    }

    public class updatedata extends AsyncTask<Void, String, String> {


        String status="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {

            String response = "";
            try {
                response = run("http://172.20.10.9/IOT/service_2.php?action=updateregistration&id=" + id+"&name="+UFname.getText().toString()+"&mname="+UMname.getText().toString()+"&lname="+ULname.getText().toString()+"&gender="+UGender.getText().
                        toString()+"&email="+UEmail.getText().toString()+"&password="+UE_pwd.getText().toString()+"&mobil="+UMobile.getText().toString()+"&addre="+UAddress.getText().toString()+"&type=admin");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONObject jobj = new JSONObject(response);
                JSONArray jsonArray = jobj.getJSONArray("data");

                // for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jobj1 = jsonArray.getJSONObject(0);
                status = jobj1.getString("staus");



                // }
            } catch (JSONException e) {
                e.printStackTrace();
                return "error";
            }

            return status;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("valid")) {

                Toast.makeText(Updation.this, "Successfully Updated...", Toast.LENGTH_SHORT).show();
                finish();
            } else {

                Toast.makeText(Updation.this, "NETWORK ERROR", Toast.LENGTH_SHORT).show();

            }
        }


    }
}
