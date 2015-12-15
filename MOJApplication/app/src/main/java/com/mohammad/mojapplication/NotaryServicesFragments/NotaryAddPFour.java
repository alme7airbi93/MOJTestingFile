package com.mohammad.mojapplication.NotaryServicesFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.MOJdatabase.MOJDbSchema;
import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.NotaryTemlpate;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddPFour extends Fragment {


    Button btnPay;
    CommunicatorService communicatorService;
    Party party,party2;
    User user;
    Service service;
    MOJManager mojManager;
    String Notary;
    EditText etNotary;

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
        View v = inflater.inflate(R.layout.fragment_services_pg_four, container, false);
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
        etNotary = (EditText) v.findViewById(R.id.etNotary);
        NotaryTemlpate notaryTemlpate = mojManager.findNotaryTempByType(service.getType());
        Log.d("123", service.getType());
        etNotary.setText("ليكن معلوم لدى الجميع بأنني انا الموقع ادناه /" + user.getName() + "من الجنسية الإماراتية ");
        etNotary.append(" واحمل بطاقة الهوية الوطنية لدولة الامارات العربية المتحدة, رقم " + user.getId());
        if (service.getType().equals("Limited Power of Attorney to Act and Manage a Vehicle \n")) {
            etNotary.append("/ بصفتي مالك السيارة رقم ------------------- / -------------------- بموجب هذا السند وكلت السيد");
            etNotary.append(party.getfName() + ",من الجنسية الإماراتية");
            etNotary.append(" و يحمل بطاقة الهوية الوطنية لدولة الامارات العربية المتحدة, رقم  --------");
            etNotary.append(" " + notaryTemlpate.getNotary());
        }
        if (service.getType().equals("General Power of Attorney to Act and Manage \n")) {
            etNotary.append("بصفتي الشخصية وباي صفة كانت بموجبه اعين :");
            etNotary.append(party.getfName() + ",من الجنسية الإماراتية");
            etNotary.append(" و يحمل بطاقة الهوية الوطنية لدولة الامارات العربية المتحدة, رقم  --------");
            etNotary.append(" " + notaryTemlpate.getNotary());
        }



        service.setNotary(etNotary.getText().toString());




        btnPay = (Button) v.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mojManager.addService(service);
                mojManager.addParty(party);
                if(party2 != null)
                {
                    mojManager.addParty(party2);
                }

                SmsManager manager =SmsManager.getDefault();
                NIDCard nidCard = mojManager.findNIDCardById(getActivity().getIntent().getStringExtra("userID"));
                String mobile = nidCard.getMobile();
                String msg = "Dear, " + user.getName() + " your " + service.getType()
                        + " with reference no." + service.getServiceID() + " document request has " +
                        "been recieved and is pending";


                String mobile2 = party.getMobile();
                String msg2 = "Dear," + party.getfName() + " " + user.getName() +
                        " started a notary document that gives you " + service.getType() +
                        " is pending";
                manager.sendTextMessage(mobile,null,msg,null,null);
                manager.sendTextMessage(mobile2,null,msg2,null,null);


                communicatorService.backtoMain(getActivity().getIntent().getStringExtra("userID"));






            }
        });
        return v;
    }

}


