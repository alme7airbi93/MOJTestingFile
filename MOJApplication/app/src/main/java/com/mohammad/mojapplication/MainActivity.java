package com.mohammad.mojapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.mainActivityFragments.CaseTrackingFragment;
import com.mohammad.mojapplication.mainActivityFragments.NavigationDrawerFragment;
import com.mohammad.mojapplication.mainActivityFragments.NewsFragment;
import com.mohammad.mojapplication.mainActivityFragments.ServicesFragments;
import com.mohammad.mojapplication.mainActivityFragments.SittengsFragment;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements CommunicatorMain, NavigationDrawerFragment.NavigationDrawerCallbacks
{
    private MOJManager mojManager;
    private ProgressDialog progressDialog;
    private String userName;
    private TextView tvHeaderDrawerUserName;
    private PagerTabStrip pagerTabStrip;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    NewsFragment newsFragment = new NewsFragment();
    ServicesFragments servicesFragments = new ServicesFragments();
    CaseTrackingFragment caseTrackingFragment = new CaseTrackingFragment();
    SittengsFragment sittengsFragment = new SittengsFragment();
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
    }

    @Override
    public void sendStringToMain(String string) {
        this.userName = string;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.headerLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Preparing");
        progressDialog.setMessage("Getting things tidy...");
        progressDialog.show();
        new Thread() {

            public void run() {

                try{

                    sleep(5000);

                } catch (Exception e) {

                    Log.e("tag", e.getMessage());

                }

                // dismiss the progress dialog

                progressDialog.dismiss();

            }

        }.start();

        mojManager = MOJManager.getMOJManager(this);
        userName = this.getIntent().getStringExtra("userID");
        Toast.makeText(MainActivity.this, userName, Toast.LENGTH_SHORT).show();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        tvHeaderDrawerUserName = (TextView) findViewById(R.id.tv_drawer_header_useName_tv);
        if (!userName.equals("guest")) {
            User user = mojManager.findUserById(userName);
            String name = user.getName();
            tvHeaderDrawerUserName.setText("Welcome to Ministry of Justice App, "+name);
        }else if (userName.equals("guest")) {
            tvHeaderDrawerUserName.setText("Welcome to Ministry of Justice App, "+userName);
        }

        // Locate the viewpager in activity_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerTab);
        // Set the ViewPagerAdapter into ViewPager
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(0);
            }
        }, 100);




    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        final int PAGE_COUNT = 4;

        // Tab Titles
        private String tabtitles[] = new String[] { "News", "Services", "Cases","Settings" };
        ArrayList<Fragment> fragments = new ArrayList<>();
        Context context;



        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                // Open FragmentTab1.java
                case 0:
                    return newsFragment;

                // Open FragmentTab2.java
                case 1:
                    return servicesFragments;
                // Open FragmentTab3.java
                case 2:

                    return caseTrackingFragment;

                case 3:

                    return sittengsFragment;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabtitles[position];
        }
    }



    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main2, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void sendToStepOne() {


        Intent i = new Intent(MainActivity.this, ServicesActivity.class);
        Log.d("123", "miaiiiiin---------------------------------------------");

        i.putExtra("userID", getIntent().getStringExtra("userID"));
        startActivity(i);
        this.finish();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

       Intent i = null;
        switch (position)
        {
            case 0:

                break;
            case 1:
                i = new Intent(this, AboutUsActivity.class);
                break;
            case 2:
                i = new Intent(this, MapsActivity.class);
                break;
            case 3:
                i = new Intent(this, RegistrationActivity.class);
                break;
        }
        if(i !=null)
        {
            startActivity(i);

        }


        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();


    }

    public void onSectionAttached(int number) {
        switch (number) {

            case 1:
                mTitle = getString(R.string.title_section_home);
                break;
            case 2:
//                mTitle = getString(R.string.title_section2);
                break;
            case 3:
//                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
