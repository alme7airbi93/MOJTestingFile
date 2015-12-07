package com.mohammad.mojapplication.mainActivityFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mohammad.mojapplication.R;

/**
 * Created by alisa on 12/7/2015.
 */
public class DebugDialog extends DialogFragment {

    AlertDialog dialog;
    Button btnApproved,btnDeclined,btnSignature;
    EditText etRef;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.settings_dialog_fragment, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialog = builder.setView(v).create();

        btnApproved = (Button) v.findViewById(R.id.btnApproved);
        btnDeclined = (Button) v.findViewById(R.id.btnDeclined);
        btnSignature = (Button) v.findViewById(R.id.btnSignature);
        etRef = (EditText) v.findViewById(R.id.etDebug);

        btnDeclined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return dialog;
    }
}
