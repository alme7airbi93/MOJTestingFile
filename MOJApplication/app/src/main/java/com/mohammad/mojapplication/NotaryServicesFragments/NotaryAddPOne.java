package com.mohammad.mojapplication.NotaryServicesFragments;

import android.media.Image;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddPOne extends Fragment {

    private ImageView spLocationTrue,spDocTypeTrue,btnFirstPartyTrue,btnSecondPartyTrue;
    private TextView tvSpLocationStar,tvSpDocTypeStar,tvBtnPartyStar,tvBtnParty2Star;
    private Button btnFirstParty,btnSecondParty,btnNext;
    private Spinner spLoc, spDocType,spNotType;
    private CommunicatorService communicatorService;
    private MOJManager mojManager;
    private Party party,party2;
    private int one, two=0;
    private LinearLayout loFirstParty, loSecondParty;
    private NotaryAddPOne notaryAddPOne;
    private boolean isTypeSelected,isLocationSelected = false;
    private ArrayAdapter<String> spaGen,spaLtd;
    private String[] spaGenArr, spaLtdArr;



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


    private View init(View v) {

        tvBtnParty2Star = (TextView) v.findViewById(R.id.tvBtnAddParty2Star);
        tvBtnPartyStar = (TextView) v.findViewById(R.id.tvBtnAddPartyStar);
        tvSpLocationStar = (TextView) v.findViewById(R.id.tvLocationStar);

        btnSecondPartyTrue = (ImageView) v.findViewById(R.id.btnAddParty2True);
        btnFirstPartyTrue = (ImageView) v.findViewById(R.id.btnAddPartyTrue);
        spLocationTrue = (ImageView) v.findViewById(R.id.spLocationTrue);
        spDocTypeTrue = (ImageView) v.findViewById(R.id.spDocTypeTrue);
        spLoc = (Spinner) v.findViewById(R.id.spLocation);
        spDocType = (Spinner) v.findViewById(R.id.spDocType);
        spNotType = (Spinner) v.findViewById(R.id.spNotType);
        btnFirstParty = (Button) v.findViewById(R.id.btnAddParty);
        btnSecondParty = (Button) v.findViewById(R.id.btnAddParty2);
        btnNext = (Button) v.findViewById(R.id.btnServNextFirst);
        loFirstParty = (LinearLayout) v.findViewById(R.id.loFirstParty);
        loSecondParty = (LinearLayout) v.findViewById(R.id.loSecondParty);
        spaLtdArr = getResources().getStringArray(R.array.NType);
        spaGenArr = getResources().getStringArray(R.array.NTypeG);
        spaGen =  new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,spaGenArr);
        spaLtd =  new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,spaLtdArr);

        spLoc.setSelection(one);
        spNotType.setSelection(two);



        if (spNotType.getSelectedItemPosition() == 0) {
            spDocType.setEnabled(true);
            spDocType.setAdapter(spaLtd);
        }
        else
        {
            spDocType.setEnabled(true);
            spDocType.setAdapter(spaGen);
        }


        spDocType.setEnabled(false);
        spLocationTrue.setVisibility(View.GONE);
        btnFirstPartyTrue.setVisibility(View.GONE);
        btnSecondPartyTrue.setVisibility(View.GONE);

        if (party == null) {
            btnNext.setEnabled(false);
        }
        else
        {
            btnNext.setEnabled(true);
        }



        return v;
    }

    private void validator() {
        if (party2 == null) {

        }
        else {
            btnSecondPartyTrue.setVisibility(View.VISIBLE);
            tvBtnParty2Star.setVisibility(View.GONE);
        }

        if (party == null) {
            loSecondParty.setVisibility(View.GONE);
        } else
        {
            loSecondParty.setVisibility(View.VISIBLE);
            tvBtnPartyStar.setVisibility(View.GONE);
            btnFirstPartyTrue.setVisibility(View.VISIBLE);
        }

        spLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {


                } else {
                    isLocationSelected =true;
                    spLocationTrue.setVisibility(View.VISIBLE);
                    tvSpLocationStar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spDocType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else {
                    isLocationSelected = true;
                    spDocTypeTrue.setVisibility(View.VISIBLE);
                    tvSpDocTypeStar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (isLocationSelected != false && isTypeSelected != false && party != null) {

            btnNext.setEnabled(true);
        }

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
        init(v);
        validator();




        spNotType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    spDocType.setEnabled(true);
                    spDocType.setAdapter(spaLtd);
                }
                else
                {
                    spDocType.setEnabled(true);
                    spDocType.setAdapter(spaGen);
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                                randID + "", new Date(), "Pending", party.getPartyID(), party2.getPartyID(),
                                spLoc.getSelectedItem().toString(),"");
                        communicatorService.sendToStepTwo(service, user, party,party2);
                    }
                    else {
                        Log.d("32131231", party.getPartyID());
                        Service service = new Service(user.getId(), spDocType.getSelectedItem().toString(),
                                randID + "", new Date(), "Pending", party.getPartyID(), "",
                                spLoc.getSelectedItem().toString(), "");
                        communicatorService.sendToStepTwo(service, user, party, party2);
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

                communicatorService.sendToAdd(spLoc.getSelectedItemPosition(), spNotType.getSelectedItemPosition());

            }
        });
        btnSecondParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicatorService.sendToAddTwo(party, spLoc.getSelectedItemPosition(), spNotType.getSelectedItemPosition());
            }
        });

        return v;
    }


}


