package com.mohammad.mojapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.RegistrationFragments.Login;
import com.mohammad.mojapplication.RegistrationFragments.RegStepFour;
import com.mohammad.mojapplication.RegistrationFragments.RegStepOne;
import com.mohammad.mojapplication.RegistrationFragments.RegStepThree;
import com.mohammad.mojapplication.RegistrationFragments.RegStepTwo;
import com.mohammad.mojapplication.RegistrationFragments.Welcome;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

public class RegistrationActivity extends AppCompatActivity implements Communicator
{
    private MOJManager mojManager;
    private User user;
    private FragmentManager manager = getSupportFragmentManager();
    private Welcome welcome;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return (keyCode == KeyEvent.KEYCODE_BACK ? true : super.onKeyDown(keyCode, event));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        startWelcomeFragment();


    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        savedInstanceState.putSerializable("123", (Serializable) user);


    }

    @Override
    public void sendUsertoMainActivity(User user) {

        if(user != null)
        {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("userID", user.getId().toString());
            startActivity(i);
            this.finish();
        }else if (user == null) {

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("userID", "guest");
            startActivity(i);
            this.finish();
        }

    }


    @Override
    public void sendData(String string) {

        RegStepTwo regStepTwo = new RegStepTwo();
        regStepTwo.reciveIDNumber(string);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.registrationActivityLayout, regStepTwo, "FS2");
        transaction.commit();
        stopStepOne();




    }

    @Override
    public void sendNIDcardObject(NIDCard nidCard) {
        RegStepThree regStepThree = new RegStepThree();
        regStepThree.reciveNIDCardObject(nidCard);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.registrationActivityLayout, regStepThree, "FS3");
        transaction.commit();
        stopStepTwo();

    }


    @Override
    public void startWelcomeFragment()
    {
        Welcome welcome = new Welcome();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.registrationActivityLayout, welcome, "WLC");
        transaction.commit();

    }
    @Override
    public void stopWelcomeFragment() {
        Welcome welcome = (Welcome) manager.findFragmentByTag("WLC");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(welcome);
        transaction.commit();
    }


    @Override
    public void startStepOne()
    {
        RegStepOne regStepOne = new RegStepOne();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.registrationActivityLayout, regStepOne, "FS1");
        transaction.commit();
    }

    @Override
    public void startLogin()
    {
        Login login = new Login();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.registrationActivityLayout, login, "LOG");
        transaction.commit();

    }

    @Override
    public void StartStepFour(User user) {
    RegStepFour regStepFour = new RegStepFour();
        regStepFour.receiveUser(user);
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.add(R.id.registrationActivityLayout, regStepFour, "FS4");
    transaction.commit();
        stopStepThree();
}





    public void stopStepOne() {
        RegStepOne regStepOne = (RegStepOne) manager.findFragmentByTag("FS1");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(regStepOne);
        transaction.commit();
    }
    public void stopStepTwo() {
        RegStepTwo regStepTwo = (RegStepTwo)manager.findFragmentByTag("FS2");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(regStepTwo);
        transaction.commit();
    }
    public void stopStepThree() {
        RegStepThree regStepThree = (RegStepThree)manager.findFragmentByTag("FS3");
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(regStepThree);
        transaction.commit();
    }

}
