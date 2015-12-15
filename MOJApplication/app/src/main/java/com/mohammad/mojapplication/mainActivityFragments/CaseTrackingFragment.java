package com.mohammad.mojapplication.mainActivityFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.CommunicatorMain;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.MainActivity;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.PaymentActivity;
import com.mohammad.mojapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/1/2015.
 */
public class CaseTrackingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private User logedInUser;
    private TextView tvUserName;
    private MOJManager mojManager;
    private ListView listView;
    private ServicesAdapter servicesAdapter;
    private String id = "";
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommunicatorMain communicator;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.case_tracking_fragment_layout,container,false);



        communicator = (CommunicatorMain) getActivity();

        mojManager = MOJManager.getMOJManager(getActivity());

        id= getActivity().getIntent().getStringExtra("userID");

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.grey);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_dark, android.R.color.holo_green_dark, android.R.color.white
                , android.R.color.black);

        swipeRefreshLayout.setOnRefreshListener(this);
        listView = (ListView) v.findViewById(R.id.list_view);
        List<Service> listest= mojManager.findServiceByUserId(this.id);
        servicesAdapter = new ServicesAdapter(getActivity(), listest);
        listView.setAdapter(servicesAdapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Service service = servicesAdapter.getItem(position);



            if (service.getServiceStatus().equals("Approved Needs Signature")) {
                communicator.startSignature(getActivity().getIntent().getStringExtra("userID"),service.getServiceID());
            }
              if (service.getServiceStatus().equals("Approved Waiting Payment")) {

                  Intent i = new Intent(getActivity(), PaymentActivity.class);
                  i.putExtra("userID", getActivity().getIntent().getStringExtra("userID"));

                  startActivity(i);

            }
              if (service.getServiceStatus().equals("Decline")) {
                  Toast.makeText(getActivity(), "Open New Case", Toast.LENGTH_SHORT).show();
            }







        }
    });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mojManager = MOJManager.getMOJManager(getActivity());
        id= getActivity().getIntent().getStringExtra("userID");
        logedInUser = mojManager.findUserById(id);
        /*tvUserName = (TextView)getActivity().findViewById(R.id.tvUserName);
        tvUserName.setText(logedInUser.getName());*/

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {

                List<Service> listest = mojManager.findServiceByUserId(id);
                servicesAdapter = new ServicesAdapter(getActivity(), listest);
                listView.setAdapter(servicesAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);
    }


    public class ServicesAdapter extends ArrayAdapter<Service> {
        public ServicesAdapter(Context context, List<Service> services) {
            super(context, 0, services);


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Service service = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_list_cases, parent, false);
            }


            // Lookup view for data population
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvCaseTitle);
            TextView tvStatus = (TextView) convertView.findViewById(R.id.tvCaseStatus);
            TextView tvDate = (TextView) convertView.findViewById(R.id.tvCaseDate);
            LinearLayout loList = (LinearLayout) convertView.findViewById(R.id.loList);
            // Populate the data into the template view using the data object
            if (position % 2 == 0) {
                loList.setBackgroundResource(R.color.darkgrey);
            }
            tvTitle.setText("Case Ref No.: " + service.getServiceID());
            tvStatus.setText("Status: " + service.getServiceStatus());
            tvDate.setText("Date: "+ service.getDate());
            // Return the completed view to render on screen
            return convertView;
        }


    }

//    private void updateUI()
//    {
//        MOJManager mojManager = MOJManager.getMOJManager(getActivity());
//        List<Service> services = mojManager.findServiceByUserId(getActivity().getIntent().getStringExtra("userID"));
//        if(!services.isEmpty())
//        {
//            Toast.makeText(getActivity(), "NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOS", Toast.LENGTH_SHORT).show();
//        }
//        if(servicesAdapter == null)
//        {
//            servicesAdapter = new ServicesAdapter(getActivity(),services);
//            listView.setAdapter(servicesAdapter);
//
//        }
//        else
//        {
//            caseAdapter.setCases(services);
//            caseAdapter.notifyDataSetChanged();
//        }
//
//    }

//    private class CaseHolder extends RecyclerView.ViewHolder implements View.OnClickListener
//    {
//        private TextView tvCaseTitle;
//        private TextView tvCaseStatus;
//
//
//
//
//        private Service service;
//
//
//
//        public CaseHolder(View view)
//        {
//            super(view);
//
//            view.setOnClickListener(this);
//
//            tvCaseTitle = (TextView)view.findViewById(R.id.tvCaseTitle);
//            tvCaseStatus = (TextView)view.findViewById(R.id.tvCaseStatus);
//
//
//        }
//
//        public void bindService(Service service)
//        {
//            this.service = service;
//
//
//
//            tvCaseTitle.setText(service.getServiceID());
//            tvCaseStatus.setText(service.getServiceStatus());
//
//        }
//
//
//
//
//        @Override
//        public void onClick(View v) {
//
//
//
//
//        }
//    }
//
//
//
//    private class CaseAdapter extends RecyclerView.Adapter<CaseHolder>
//    {
//
//
//        private List<Service> services;
//
//        public CaseAdapter(List<Service> services)
//        {
//            this.services = services;
//        }
//
//        @Override
//        public int getItemCount() {
//            return services.size();
//        }
//
//        @Override
//        public CaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflator = LayoutInflater.from(getActivity());
//
//            View v = inflator.inflate(R.layout.custom_list_cases,parent,false);
//
//            return new CaseHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(CaseHolder caseHolder, int i) {
//
//            Service service = services.get(i);
//            caseHolder.bindService(service);
//        }
//
//        public void setCases(List<Service> services)
//        {
//            this.services = services;
//        }
//    }

//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        updateUI();
//    }




}
