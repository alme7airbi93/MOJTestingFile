package com.mohammad.mojapplication.NotaryServicesFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddPTwo extends Fragment {


    EditText etPin;
    Button btnNext,btnPic2;
    TextView tvPic2;
    MOJManager mojManager;
    Party party,party2;
    Service service;
    User user;
    CommunicatorService communicatorService;
    Uri selectedImage;

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
        View v = inflater.inflate(R.layout.fragment_services_pg_two, container, false);

        communicatorService = (CommunicatorService) getActivity();

        tvPic2 = (TextView) v.findViewById(R.id.tvPic2);
        etPin = (EditText) v.findViewById(R.id.etPinServ);
        btnPic2 = (Button) v.findViewById(R.id.btnPic2);
        btnPic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                communicatorService.PartyToCam(one,two,spPartyType.getSelectedItemPosition(),etNIDCardAddParty.getText().toString());
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                startActivityForResult(i, 111);
            }
        });

        btnNext = (Button) v.findViewById(R.id.btnServNextSecond);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User userCompare = mojManager.findUserById(user.getId());

                if(etPin.getText().toString().equals(userCompare.getServicePass()))
                {

                    communicatorService.sendToStepThree(service,user, party,party2);

                }
                else
                {
                    Toast.makeText(getActivity(), "Wrong Pin", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 111 && resultCode == -1&& data != null)
        {
            selectedImage = data.getData();
            tvPic2.setText("Picture Attached");

        }

    }


}


