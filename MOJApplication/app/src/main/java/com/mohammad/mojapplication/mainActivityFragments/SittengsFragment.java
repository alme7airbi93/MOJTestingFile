package com.mohammad.mojapplication.mainActivityFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

/**
 * Created by user on 10/31/2015.
 */
public class SittengsFragment extends Fragment {

    private String userId;
    private Button btnChangePass,btnChangePhoneNumber,btnChangeAddress;
    private MOJManager mojManager;
    private  User user;
    private static final int REQUEST_PASS = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.sittengs_fragment_layout,container,false);
        mojManager = MOJManager.getMOJManager(getActivity());
        btnChangePhoneNumber = (Button) v.findViewById(R.id.btnChangePhoneNumber);
        btnChangePass = (Button) v.findViewById(R.id.btnChangePass);
        btnChangeAddress = (Button) v.findViewById(R.id.btnChangeAddress);

        userId = getActivity().getIntent().getStringExtra("userID");
        user = mojManager.findUserById(userId);

        btnChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SettingsDialogFragment settingDialogFragment = new SettingsDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userID",user.getId());
                bundle.putInt("mode", 1);
                settingDialogFragment.setArguments(bundle);
                settingDialogFragment.setTargetFragment(SittengsFragment.this,REQUEST_PASS);

                settingDialogFragment.show(fm,"SettingDialog");
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
//                Toast.makeText(getActivity(),user.getName() ,Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                SettingsDialogFragment settingDialogFragment = new SettingsDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userID",user.getId());
                bundle.putInt("mode", 0);
                settingDialogFragment.setArguments(bundle);
                settingDialogFragment.setTargetFragment(SittengsFragment.this,REQUEST_PASS);

                settingDialogFragment.show(fm,"SettingDialog");
            }
        });
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SettingsDialogFragment settingDialogFragment = new SettingsDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userID",user.getId());
                bundle.putInt("mode", 2);
                settingDialogFragment.setArguments(bundle);
                settingDialogFragment.setTargetFragment(SittengsFragment.this,REQUEST_PASS);

                settingDialogFragment.show(fm,"SettingDialog");
            }
        });



        return v;
    }


}
