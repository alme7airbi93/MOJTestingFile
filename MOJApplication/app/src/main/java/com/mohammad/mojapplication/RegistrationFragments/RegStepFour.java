package com.mohammad.mojapplication.RegistrationFragments;

import android.opengl.ETC1;
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
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

/**
 * Created by user on 11/1/2015.
 */
public class RegStepFour extends Fragment
{
    private EditText etPin;
    private Button btnFinish;
    private MOJManager mojManager;
 private User  user;
    private Communicator comm;

    public void receiveUser(User user)
    {

        this.user = user;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mojManager = MOJManager.getMOJManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reg_step_four_layout, container, false);

        comm = (Communicator) getActivity();

        etPin = (EditText) v.findViewById(R.id.etTransPass);

        btnFinish = (Button) v.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPin.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(),"Please enter a pin spaces",Toast.LENGTH_LONG).show();

                }else {

                    user.setServicePass(etPin.getText().toString());
                    mojManager.addUser(user);
                    Toast.makeText(getActivity(),"Your Account Added",Toast.LENGTH_LONG).show();

                    try{

                        user = mojManager.findUserByUserName(user.getUserName());

                        if(user != null)
                        {

                                comm.sendUsertoMainActivity(user);

                        }
                    }
                    catch (NullPointerException npe)
                    {
                        Toast.makeText(getActivity(),"Username or password incorrect",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


        return v;
    }


}
