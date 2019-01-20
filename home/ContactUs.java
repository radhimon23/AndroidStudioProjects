package com.example.gauravkapadiya.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    TextView name,email1,email2,phone1,phone2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        name=(TextView)findViewById(R.id.name);
        email1=(TextView)findViewById(R.id.email1);
        email2=(TextView)findViewById(R.id.email2);
        phone1=(TextView)findViewById(R.id.phone1);
        phone2=(TextView)findViewById(R.id.phone2);


    }
}
