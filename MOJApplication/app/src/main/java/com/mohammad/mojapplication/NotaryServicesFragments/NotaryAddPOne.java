package com.mohammad.mojapplication.NotaryServicesFragments;

import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.Date;
import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddPOne extends Fragment {

    Button btnFirstParty,btnSecondParty,btnNext;
    Spinner spLoc, spDocType;
    CommunicatorService communicatorService;
    MOJManager mojManager;
    Party party,party2;
    int one, two;
    LinearLayout loFirstParty, loSecondParty;
    NotaryAddPOne notaryAddPOne;



    public void receiveExtraFromParty(Party party,int one,int two) {


        this.party = party;
        this.one = one;
        this.two = two;

    }

    public void receiveExtraFromSecondParty(Party party,Party party2,int one,int two) {

        this.party = party;
        this.party2 = party2;
        this.one = one;
        this.two = two;

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
        View v = inflater.inflate(R.layout.fragment_services_pg_one, container, false);
        communicatorService = (CommunicatorService) getActivity();

        spLoc = (Spinner) v.findViewById(R.id.spLocation);
        spDocType = (Spinner) v.findViewById(R.id.spDocType);
        btnFirstParty = (Button) v.findViewById(R.id.btnAddParty);
        btnSecondParty = (Button) v.findViewById(R.id.btnAddParty2);
        btnNext = (Button) v.findViewById(R.id.btnServNextFirst);
        loFirstParty = (LinearLayout) v.findViewById(R.id.loFirstParty);
        loSecondParty = (LinearLayout) v.findViewById(R.id.loSecondParty);


        if(party == null)
        {
            loSecondParty.setVisibility(View.GONE);
        }
        else
        {
            loSecondParty.setVisibility(View.VISIBLE);
        }


        spLoc.setSelection(one);
        spDocType.setSelection(two);




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(party != null)
                {
                    User user = mojManager.findUserById(getActivity().getIntent().getStringExtra("userID"));

                    Random ran = new Random();
                    int randID = ran.nextInt(999999 - 91111) + 91111;

                    if(party2 != null) {
                        Service service = new Service(user.getId(), spDocType.getSelectedItem().toString(),
                                randID + "", new Date(), "Pending", party.getPartyID(), party2.getPartyID(), spLoc.getSelectedItem().toString());
                        communicatorService.sendToStepTwo(service, user, party,party2);
                    }
                    else
                    {
                        Service service = new Service(user.getId(), spDocType.getSelectedItem().toString(),
                                randID + "", new Date(), "Pending", party.getPartyID(), "",spLoc.getSelectedItem().toString());
                        communicatorService.sendToStepTwo(service, user, party,party2);
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "Please add a party", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnFirstParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                communicatorService.sendToAdd(spLoc.getSelectedItemPosition(), spDocType.getSelectedItemPosition());

            }
        });
        btnSecondParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicatorService.sendToAddTwo(party, spLoc.getSelectedItemPosition(), spDocType.getSelectedItemPosition());
            }
        });

        return v;
    }


}


