package com.example.gauravkapadiya.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class splash extends AppCompatActivity {
Thread splashthread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashthread= new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    super.run();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent i=new Intent(splash.this,MainActivity.class);
                    startActivity(i);
                }
            }

        };
        splashthread.start();
    }
    @Override
    protected void onPause(){
        finish();
        super.onPause();

    }

}
