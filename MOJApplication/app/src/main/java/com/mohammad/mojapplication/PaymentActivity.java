package com.mohammad.mojapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mohammad.mojapplication.NotaryServicesFragments.NotaryAddPFour;
import com.mohammad.mojapplication.NotaryServicesFragments.NotaryAddPOne;
import com.mohammad.mojapplication.NotaryServicesFragments.NotaryAddPThree;
import com.mohammad.mojapplication.NotaryServicesFragments.NotaryAddPTwo;
import com.mohammad.mojapplication.NotaryServicesFragments.NotaryAddParty;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;

/**
 * Created by alisa on 11/19/2015.
 */
public class PaymentActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button btn = (Button) findViewById(R.id.btnSubmitPay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(PaymentActivity.this, MainActivity.class);
                i.putExtra("userID", getIntent().getStringExtra("userID"));

                startActivity(i);
                PaymentActivity.this.finish();
            }
        });


    }

}
