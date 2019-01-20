package com.example.gauravkapadiya.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.gauravkapadiya.home.Utils.geturl;
import com.example.gauravkapadiya.home.Utils.param;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText ulname,password;
    Button login,register,guest;

  //  String name,pass1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ulname=(EditText)findViewById(R.id.ulname);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.registration);
        guest=(Button)findViewById(R.id.guest);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);

            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login.this,List_View.class);
                i.putExtra("userid", "Guest");
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ulname.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
                {

                    Toast.makeText(Login.this, "Enter All Fields ", Toast.LENGTH_SHORT).show();
                     /*Intent i =new Intent(Login.this,List_View.class);
                    startActivity(i);
*/
                }
                else
                {

                    new login().execute();
                   // onPause();
                }



                /*Intent i3 = new Intent(Login.this,My_Detail.class);
                i3.putExtra("output", "Welcome \n" + ulname.getText().toString());
                startActivity(i3);*/
            }
        });


    }

    class login extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String res="";
        String userid="";

        @Override
        protected String doInBackground(String... params) {


            String url = getResources().getString(R.string.url);
            ArrayList<param> parameters = new ArrayList<>();

            //ArrayList<String> image = new ArrayList<String>();

            parameters.add(new param("action", "login"));
            parameters.add(new param("Email",ulname.getText().toString() ));
            parameters.add(new param("Password", password.getText().toString()));


            try {

                JSONObject obj = new geturl().makeHttpRequestpost(url, parameters);
                Log.d("jsonres",""+obj);

                JSONArray jarray = obj.getJSONArray("data");

                Log.e("countt123", jarray.length() + "");


                for (int i = 0; i < jarray.length(); i++) {

                    Log.d("loginData", jarray.toString());
                    JSONObject obj1 = jarray.getJSONObject(i);

                    res=obj1.getString("value");

                    if(res.equals("valid"))
                    {
                        userid=obj1.getString("id");
                    }

                }


            } catch (Exception e) {

                Log.d("logexe",""+e);
                e.printStackTrace();
                return "error";
            }
            return res;

        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();

            Log.d("loginresult",result);
            if (result.equals("error")) {
                Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_SHORT).show();

            } else {

                try {


                    if(result.equals("valid"))
                    {

                        Intent i=new Intent(Login.this,List_View.class);
                        i.putExtra("userid", userid);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Invalid Email or password!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Log.e("msgsdsfdsdsd", e.toString());
                }
            }
        }

        @Override
        protected void onPreExecute() {

            try {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
