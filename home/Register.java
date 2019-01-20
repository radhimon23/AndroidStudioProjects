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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText rFname,rMname,rLname,rGender,rEmail,rMobile,rEpwd,rCpwd,rAdrress;

    EditText Fname, Mname, Lname, Gender, Email,Mobile, E_pwd, C_pwd,Address;
    Button Submit,edit;
    public static String fname, mname, lname, Mno, mail,address,epwd,gender;
    String id="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Fname = (EditText) findViewById(R.id.Fname);
        Mname = (EditText) findViewById(R.id.Mname);
        Lname = (EditText) findViewById(R.id.Lname);
        Gender = (EditText) findViewById(R.id.Gender);
        Email = (EditText) findViewById(R.id.Email);
        Mobile = (EditText) findViewById(R.id.Mobile);
        E_pwd = (EditText) findViewById(R.id.Epwd);
        C_pwd = (EditText) findViewById(R.id.Cpwd);
        Address = (EditText) findViewById(R.id.Addres);
      //  Submit = (Button) findViewById(R.id.Submit);
        edit=(Button)findViewById(R.id.Edit);




        rFname = (EditText) findViewById(R.id.Fname);
        rFname.setText(getIntent().getStringExtra("NAME"));

        rMname = (EditText) findViewById(R.id.Mname);
        rMname.setText(getIntent().getStringExtra("MNAME"));

        rLname = (EditText) findViewById(R.id.Lname);
        rLname.setText(getIntent().getStringExtra("LNAME"));

        rGender = (EditText) findViewById(R.id.Gender);
        rGender.setText(getIntent().getStringExtra("GENDER"));

        rEmail = (EditText) findViewById(R.id.Email);
        rEmail.setText(getIntent().getStringExtra("EMAIL"));

        rMobile = (EditText) findViewById(R.id.Mobile);
        rMobile.setText(getIntent().getStringExtra("MOBILE"));

        rEpwd = (EditText) findViewById(R.id.Epwd);
        rEpwd.setText(getIntent().getStringExtra("EPWD"));


        rCpwd = (EditText) findViewById(R.id.Cpwd);
        rCpwd.setText(getIntent().getStringExtra("CPWD"));


        rAdrress = (EditText) findViewById(R.id.Addres);
        rAdrress.setText(getIntent().getStringExtra("ADDRESS"));






        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Fname.getText().toString().trim().equals("")) {
                    Fname.setError("Please enter first name");
                    Fname.requestFocus();
                } else if (Mname.getText().toString().trim().equals("")) {
                    Mname.setError("Please enter Middle name");
                    Mname.requestFocus();
                } else if (Lname.getText().toString().trim().equals("")) {
                    Lname.setError("Please enter Last name");
                    Lname.requestFocus();
                } else if (Gender.getText().toString().trim().equals("")) {
                    Gender.setError("Please enter your Gender");
                    Gender.requestFocus();
                } else if (Email.getText().toString().trim().equals("")) {
                    Email.setError("Please enter your Email");
                    Email.requestFocus();
                }
                else if(!isvalidemail(Email.getText().toString().trim()))
                {
                    Email.setError("Please enter valid Email");
                    Email.requestFocus();
                }
                else if (Mobile.getText().toString().trim().equals("")) {
                    Mobile.setError("Please enter your mobile no.");
                    Mobile.requestFocus();
                } else if (E_pwd.getText().toString().trim().equals("")) {
                    E_pwd.setError("Please enter your password");
                    E_pwd.requestFocus();
                } else if (E_pwd.getText().toString().trim().length() < 6 || E_pwd.getText().toString().trim().length() > 15) {
                    E_pwd.setError("Password must be in 6 to 15 digit");
                    E_pwd.requestFocus();
                } else if (C_pwd.getText().toString().trim().equals("")) {
                    C_pwd.setError("Please enter confirm password");
                    C_pwd.requestFocus();
                } else if (!E_pwd.getText().toString().trim().equals(C_pwd.getText().toString().trim())) {
                    C_pwd.setError("your password does not match");
                    C_pwd.requestFocus();
                } else {
                    //Toast.makeText(Register.this, "ffbhgjhg", Toast.LENGTH_SHORT).show();
                    fname = Fname.getText().toString();
                    mname = Mname.getText().toString();
                    lname = Lname.getText().toString();
                    Mno = Mobile.getText().toString();
                    mail = Email.getText().toString();
                    address=Address.getText().toString();
                    epwd=E_pwd.getText().toString();
                    gender=Gender.getText().toString();

                         new register().execute();

                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname=Fname.getText().toString();
                String mname=Mname.getText().toString();
                String lname=Lname.getText().toString();
                String gender=Gender.getText().toString();
                String email=Email.getText().toString();
                String mobile=Mobile.getText().toString();
                String epwd=E_pwd.getText().toString();
                String cpwd=C_pwd.getText().toString();
                String address=Address.getText().toString();

                Intent intentupdate = new Intent(Register.this,Updation.class);
                intentupdate.putExtra("NAME",fname);
                intentupdate.putExtra("MNAME",mname);
                intentupdate.putExtra("LNAME",lname);
                intentupdate.putExtra("GENDER",gender);
                intentupdate.putExtra("EMAIL",email);
                intentupdate.putExtra("MOBILE",mobile);
                intentupdate.putExtra("EPWD",epwd);
                intentupdate.putExtra("CPWD",cpwd);
                intentupdate.putExtra("ADDRESS",address);

                startActivity(intentupdate);
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

       class register extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String res="";

        @Override
        protected String doInBackground(String... params) {


            String url = getResources().getString(R.string.url);
            ArrayList<param> parameters = new ArrayList<>();


            parameters.add(new param("action", "reg"));
            parameters.add(new param("Fname", fname));
            parameters.add(new param("Mname",mname));
            parameters.add(new param("Lname",lname ));
            parameters.add(new param("Gender", gender));
            parameters.add(new param("Email",mail));
            parameters.add(new param("Phone_no", Mno));
            parameters.add(new param("Password",epwd));
            parameters.add(new param("Address", address));
           // parameters.add(new param("Password", "enable"));


            try {
                JSONObject obj = new geturl().makeHttpRequestpost(url, parameters);
                JSONArray jarray = obj.getJSONArray("data");

                Log.e("countt123", jarray.length() + "");


                for (int i = 0; i < jarray.length(); i++) {

                    Log.d("JsonData", jarray.toString());
                    JSONObject obj1 = jarray.getJSONObject(i);
                    res=obj1.getString("value");

                }

                return res;


            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }


        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            Log.d("result",result);
            if (result.equals("error")) {
                //Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_SHORT).show();

            } else {

                try {

                    //Intent i=new Intent(Register.this,Login.class);
                    //startActivity(i);



                    if(res.equals("valid"))
                    {
                        Toast.makeText(getApplicationContext(), "registerd successfully!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"This username is Already exist!", Toast.LENGTH_SHORT).show();

                    }



                } catch (Exception e) {
                    Log.e("msgsdsfdsdsd", e.toString());
                }
            }
        }

        @Override
        protected void onPreExecute() {

            try {
                progressDialog = new ProgressDialog(Register.this);
                progressDialog.setMessage("Loading");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}







