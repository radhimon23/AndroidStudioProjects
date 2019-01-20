package com.example.gauravkapadiya.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class List_View extends AppCompatActivity {

    ListView card;
    TextView userid;

    String mytext[] = {"Dead Animal Removal", "Street Light Issue","Tree Obstruction",
            "Illegal Dumping", "Metal Household Appliances", "Drainage",
            "Road Repair","Illegal Excavation","Illegal Construction"};

    int myimg[] = {R.drawable.dead, R.drawable.street, R.drawable.tree,
            R.drawable.garbage,R.drawable.metal, R.drawable.drainage ,
            R.drawable.footpath, R.drawable.excavation , R.drawable.excavation};

    List_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        card = (ListView) findViewById(R.id.mylist);
        //userid = (TextView) findViewById(R.id.userid);

        String user=getIntent().getStringExtra("userid");

        //userid.setText("Welcome "+user);

        adapter = new List_Adapter(List_View.this, mytext, myimg);
        card.setAdapter(adapter);
    }
}
