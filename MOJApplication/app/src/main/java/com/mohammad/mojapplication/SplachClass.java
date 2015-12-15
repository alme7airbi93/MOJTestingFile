package com.mohammad.mojapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by user on 11/7/2015.
 */
public class SplachClass extends AppCompatActivity
{



    //Set waktu lama splashscreen
    private static int splashInterval = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        LinearLayout splashlayout = (LinearLayout) findViewById(R.id.splashlo);

        Random ran = new Random();
        int rand  = ran.nextInt(5 - 1) +1;


        switch (rand) {
            case 0:
               break;
            case 1:
                splashlayout.setBackgroundResource(R.drawable.sheikhzayedtwo);
                break;
            case 2:
                splashlayout.setBackgroundResource(R.drawable.sheikhzayedthree);
                break;
            case 3:
                splashlayout.setBackgroundResource(R.drawable.sheikhzayedfour);
                break;
            case 4:
                splashlayout.setBackgroundResource(R.drawable.sheikhzayedone);
                break;
        }



        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(SplachClass.this, RegistrationActivity.class);
                startActivity(i);



                this.finish();
            }

            private void finish() {


            }
        }, splashInterval);

    };

}

