package com.example.gauravkapadiya.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class radiobutton extends AppCompatActivity {
    private static RadioGroup rg1;
    private static RadioButton rb;
    private static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);


        rg1=(RadioGroup)findViewById(R.id.rg1);
        button=(Button)findViewById(R.id.button2);
        //rb=(RadioButton)findViewById(R.id.rb)

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected_id=rg1.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selected_id);
                Toast.makeText(radiobutton.this,rb.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    //public void onC
}
