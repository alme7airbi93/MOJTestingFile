package com.mohammad.mojapplication.RegistrationFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohammad.mojapplication.Communicator;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.R;

import java.util.Random;

/**
 * Created by user on 11/1/2015.
 */
public class RegStepTwo extends Fragment {

    private Button btnNextSMS;
    private EditText etSMSCode;
    private String IDNumber ;
    private MOJManager mojManager;
    private String randomNumber;
    private Communicator communicator;
    private NIDCard nidCard;

    public void reciveIDNumber(String idNumber) {

        this.IDNumber = idNumber;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg_step_two_layout, container, false);

        mojManager = MOJManager.getMOJManager(getActivity());
        btnNextSMS = (Button)v.findViewById(R.id.btnNextSMS);
        etSMSCode = (EditText)v.findViewById(R.id.etSMS);
        communicator = (Communicator) getActivity();

         nidCard = mojManager.findNIDCardById(IDNumber);
        String mobile = nidCard.getMobile();

        Random ran = new Random();
        int rand  = ran.nextInt(99999 - 91111) +91111;
        randomNumber = rand +"";

        SmsManager manager =SmsManager.getDefault();
        manager.sendTextMessage(mobile,null,randomNumber,null,null);

        btnNextSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etSMSCode.getText().toString().equals(randomNumber)) {
                    communicator.sendNIDcardObject(nidCard);
                }
                else {
                    Toast.makeText(getActivity(), "Write the code !", Toast.LENGTH_LONG).show();
                }
            }
        });



        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }


}
