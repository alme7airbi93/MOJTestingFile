package com.mohammad.mojapplication.mainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mohammad.mojapplication.Communicator;
import com.mohammad.mojapplication.CommunicatorMain;
import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.MainActivity;
import com.mohammad.mojapplication.R;
import com.mohammad.mojapplication.ServicesActivity;

/**
 * Created by user on 10/31/2015.
 */


public class ServicesFragments extends Fragment {


    private CommunicatorMain communicator;
    private MOJManager mojManager;
    private LinearLayout loNotary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.services_fragment_layout, container, false);

        communicator = (CommunicatorMain) getActivity();
        loNotary = (LinearLayout) v.findViewById(R.id.btnNotary);
        loNotary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communicator.sendToStepOne();
            }
        });




        return v;
    }
}