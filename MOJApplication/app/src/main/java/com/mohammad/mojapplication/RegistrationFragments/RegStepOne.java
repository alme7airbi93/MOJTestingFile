package com.mohammad.mojapplication.RegistrationFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.Date;

/**
 * Created by user on 11/1/2015.
 */
public class RegStepOne extends Fragment {



    private EditText etID;
    private Button btnNextID;
    private Communicator communicator;
    private MOJManager mojManager;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mojManager = MOJManager.getMOJManager(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg_step_one_layout,container,false);

        //getting the string
        etID = (EditText)v.findViewById(R.id.etNID);

        // initialzing communicator
        communicator = (Communicator) getActivity();

//        NIDCard user= new NIDCard("789199312345678", "Mohammad", "0503151445", "AD", new Date());
//        mojManager.addNIDCard(user);

        NIDCard user= new NIDCard("784199553043524", "Ali AlZaabi", "0566201995", "AD", new Date());
        mojManager.addNIDCard(user);
        NIDCard usere= new NIDCard("000000000000000", "Ali AlZaabi", "0566201995", "AJ AD", new Date());
        mojManager.addNIDCard(usere);
        NIDCard usere2= new NIDCard("111111111111111", "Hamad AlZaabi", "0566201995", "DXB", new Date());
        mojManager.addNIDCard(usere2);
        NIDCard usere3= new NIDCard("222222222222222", "Mohammed AlZaabi", "0561234567", "SHJ", new Date());
        mojManager.addNIDCard(usere3);
        NIDCard usere4= new NIDCard("333333333333333", "Saoud AlZaabi", "0551234567", "RAK", new Date());
        mojManager.addNIDCard(usere4);
        NIDCard user2= new NIDCard("1", "Saif AlZaabi", "0566201995", "AD", new Date());
        mojManager.addNIDCard(user2);
        NIDCard user3= new NIDCard("2", "Mohammed AlZaabi", "0566201995", "AD", new Date());
        mojManager.addNIDCard(user3);



        // SETTING THE BUTTON
        btnNextID = (Button)v.findViewById(R.id.btnNextID);
        btnNextID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etID.getText().toString().length() == 15) {

                Toast.makeText(getActivity(),"Added",Toast.LENGTH_LONG).show();
                      communicator.sendData(etID.getText().toString());

                }else
                {
                    Toast.makeText(getActivity(),"Please be sure you write the currect number",Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });


        return v;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




}
