package com.mohammad.mojapplication.mainActivityFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.MainActivity;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.List;

/**
 * Created by user on 11/1/2015.
 */
public class CaseTrackingFragment extends Fragment {

    private User logedInUser;
    private TextView tvUserName;
    private MOJManager mojManager;
    private CaseHolder caseHolder;
    private CaseAdapter caseAdapter;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.case_tracking_fragment_layout,container,false);


        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_cases);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String id = getActivity().getIntent().getStringExtra("userID");
        mojManager = MOJManager.getMOJManager(getActivity());
        logedInUser = mojManager.findUserById(id);
        /*tvUserName = (TextView)getActivity().findViewById(R.id.tvUserName);
        tvUserName.setText(logedInUser.getName());*/

    }

    private void updateUI()
    {
        MOJManager mojManager = MOJManager.getMOJManager(getActivity());
        List<Service> services = mojManager.findServiceByUserId(getActivity().getIntent().getStringExtra("userID"));
        if(caseAdapter == null)
        {
            caseAdapter = new CaseAdapter(services);
            recyclerView.setAdapter(caseAdapter);

        }
        else
        {
            caseAdapter.setCases(services);
            caseAdapter.notifyDataSetChanged();
        }

    }

    private class CaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvCaseTitle;
        private TextView tvCaseStatus;




        private Service service;



        public CaseHolder(View view)
        {
            super(view);

            view.setOnClickListener(this);

            tvCaseTitle = (TextView)view.findViewById(R.id.tvCaseTitle);
            tvCaseStatus = (TextView)view.findViewById(R.id.tvCaseStatus);


        }

        public void bindService(Service service)
        {
            this.service = service;



            tvCaseTitle.setText(service.getServiceID());
            tvCaseStatus.setText(service.getServiceStatus());

        }




        @Override
        public void onClick(View v) {




        }
    }

    private class CaseAdapter extends RecyclerView.Adapter<CaseHolder>
    {


        private List<Service> services;

        public CaseAdapter(List<Service> services)
        {
            this.services = services;
        }

        @Override
        public int getItemCount() {
            return services.size();
        }

        @Override
        public CaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflator = LayoutInflater.from(getActivity());

            View v = inflator.inflate(R.layout.custom_list_cases,parent,false);

            return new CaseHolder(v);
        }

        @Override
        public void onBindViewHolder(CaseHolder caseHolder, int i) {

            Service service = services.get(i);
            caseHolder.bindService(service);
        }

        public void setCases(List<Service> crimes)
        {
            this.services = services;
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

}
