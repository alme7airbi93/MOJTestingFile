package com.mohammad.mojapplication;

import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.User;

/**
 * Created by user on 11/3/2015.
 */
public interface Communicator {


    public void sendData(String string);
    public void sendNIDcardObject(NIDCard nidCard);
    public void startWelcomeFragment();
    public void stopWelcomeFragment();
    public void startStepOne();
    public void startLogin();
    public void StartStepFour(User user);
    public void sendUsertoMainActivity(User user);
}
