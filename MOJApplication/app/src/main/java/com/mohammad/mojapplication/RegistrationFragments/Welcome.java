package com.mohammad.mojapplication.RegistrationFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mohammad.mojapplication.Communicator;
import com.mohammad.mojapplication.R;

/**
 * Created by user on 11/5/2015.
 */
public class Welcome extends Fragment
{
    private TextView tvSkipToMain;
    private Button btnReg;
    private Button btnLog;
    Communicator comm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        tvSkipToMain = (TextView) v.findViewById(R.id.tvSkipToMain);
        tvSkipToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comm.sendUsertoMainActivity(null);
            }
        });

        comm = (Communicator) getActivity();
        btnReg = (Button) v.findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                comm.startStepOne();
                comm.stopWelcomeFragment();


            }
        });

        btnLog = (Button) v.findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                comm.startLogin();
                comm.stopWelcomeFragment();


            }
        });

        return v;
    }





}
