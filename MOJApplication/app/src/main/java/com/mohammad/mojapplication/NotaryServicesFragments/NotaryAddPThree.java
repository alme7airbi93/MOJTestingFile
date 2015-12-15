package com.mohammad.mojapplication.NotaryServicesFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddPThree extends Fragment {


    Button btnPay;
    CommunicatorService communicatorService;
    Party party,party2;
    User user;
    Service service;
    MOJManager mojManager;
    EditText etPinNotary;
    String randomNumber;

    public void receiveUser(Service service,User user,Party party,Party party2) {

        this.user = user;
        this.service = service;
        this.party = party;
        this.party2 = party2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mojManager = MOJManager.getMOJManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_services_pg_three, container, false);
        communicatorService = (CommunicatorService) getActivity();
//        TextView tvTest = (TextView) v.findViewById(R.id.tvTest);
//
//
//        if(party2 != null)
//        {
//            tvTest.setText(party.getPartyID()+ "   " +party2.getPartyID() + "     "  +party2.getfName()+ "     " + service.getServiceID());
//        }
//        else
//        {
//            tvTest.setText(party.getPartyID()+ "   " +party.getfName() + "     "  +party.getImage1()+ "     " + service.getServiceID() + service.getUserID());
//
//        }

        Random ran = new Random();
        int rand  = ran.nextInt(99999 - 91111) +91111;
        randomNumber = rand +"";
        SmsManager manager =SmsManager.getDefault();
        NIDCard nidCard = mojManager.findNIDCardById(getActivity().getIntent().getStringExtra("userID"));
        String mobile = nidCard.getMobile();
        manager.sendTextMessage(mobile,null,randomNumber,null,null);

        etPinNotary = (EditText) v.findViewById(R.id.etPinServ);


        btnPay = (Button) v.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etPinNotary.getText().toString().equals(randomNumber)) {
                    communicatorService.sendToStepFour(service, user, party, party2);
                }
                else {
                    Toast.makeText(getActivity(), "Incorrect code or Code Expired", Toast.LENGTH_LONG).show();
                }


            }
        });
        OTP();
        return v;
    }

    public void OTP() {
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Random ran = new Random();
                int rand  = ran.nextInt(99999 - 91111) +91111;
                randomNumber = rand + "";
            }

            private void finish() {


            }
        }, 45000);
    }

}


