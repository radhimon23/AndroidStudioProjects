package com.example.gauravkapadiya.home;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class checkbox extends AppCompatActivity {

    private CheckBox checkbox1,checkbox2,checkbox3;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        checkbox1=(CheckBox)findViewById(R.id.checkBox);
        checkbox2=(CheckBox)findViewById(R.id.checkBox3);
        checkbox3=(CheckBox)findViewById(R.id.checkBox2);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                result.append("Sparrow :").append(checkbox1.isChecked());
                result.append("Pigeon :").append(checkbox3.isChecked());
                result.append("Dove :").append(checkbox2.isChecked());

                Toast.makeText(checkbox.this,result.toString(),Toast.LENGTH_LONG).show();
            }
        });


        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    Toast.makeText(checkbox.this,"Sparrow is selected",Toast.LENGTH_LONG).show();
                }
            }
        });

        checkbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    Toast.makeText(checkbox.this,"Dove is selected",Toast.LENGTH_LONG).show();
                }
            }
        });

        checkbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    Toast.makeText(checkbox.this,"Pigeon is selected",Toast.LENGTH_LONG).show();
                }
            }
        });
        //checkbox1.addOnAttachStateChangeListener();
    }


   // public void addL
}
