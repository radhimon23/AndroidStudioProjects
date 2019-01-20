package com.example.gauravkapadiya.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class Fragment_main extends AppCompatActivity {

    Button frbutton1,frbutton2;
    AutoCompleteTextView ac;
    String[] we = {"Radhika","Binit","Gaurav","Keval","Ravi","Bhoomi","Grishma","Garima","Kinjal"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        frbutton1=(Button)findViewById(R.id.button3);
        frbutton2=(Button)findViewById(R.id.button4);
        ac = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapt = new ArrayAdapter(this,android.R.layout.select_dialog_item,we);
        ac.setThreshold(2);
        ac.setAdapter(adapt);



    }

    public void ChangeFragment(View view){
        Fragment fragment;


        if(view==findViewById(R.id.button3)){
            fragment= new FragmentOne();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,fragment);
            ft.commit();





    }
    if(view==findViewById(R.id.button4)){
        fragment = new FragmentTwo();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragmentplace,fragment);
        ft.commit();




    }
    }
}
