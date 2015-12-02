package com.mohammad.mojapplication.RegistrationFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohammad.mojapplication.Communicator;
import com.mohammad.mojapplication.CommunicatorMain;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.MainActivity;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.io.Serializable;

/**
 * Created by alisa on 11/5/2015.
 */
public class Login extends Fragment
{
    private Button btnSignIn;
    private MOJManager mojManager;
    private User user;
    private EditText etUserName, etPass;

    Communicator comm;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        etUserName = (EditText)v.findViewById(R.id.etUserLog);
        etPass = (EditText) v.findViewById(R.id.etPassLog);
        comm = (Communicator)getActivity();

        btnSignIn = (Button) v.findViewById(R.id.btnSignIn);
        mojManager = MOJManager.getMOJManager(getActivity());
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                User user= new User("12345", "mohammad", "0503151445", "kha", "123", "123","123");
//                mojManager.addUser(user);
//                Toast.makeText(getActivity(),"Added",Toast.LENGTH_LONG).show();
//                user = mojManager.findUserByUserName("alme7airbi");
//                Toast.makeText(getActivity(),etUserName.getText().toString(),Toast.LENGTH_LONG).show();

                try{

                    user = mojManager.findUserByUserName(etUserName.getText().toString());
                    String userName = user.getUserName();
                    String pass = user.getPass().toString();
                   if(user != null)
                   {
                       if(pass.equals(etPass.getText().toString()))
                       {
                           comm.sendUsertoMainActivity(user);

                       }else
                       {
                           Toast.makeText(getActivity(),etPass.getText().toString() +" password incorrect" + pass,Toast.LENGTH_LONG).show();
                       }
                   }
                }
                catch (NullPointerException npe)
                {
                    Toast.makeText(getActivity(),"Username or password incorrect",Toast.LENGTH_LONG).show();
                }


            }
        });

        return v;
    }


}

