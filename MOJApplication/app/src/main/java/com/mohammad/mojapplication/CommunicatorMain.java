package com.mohammad.mojapplication;

import com.mohammad.mojapplication.Objects.NIDCard;

/**
 * Created by user on 11/3/2015.
 */
public interface CommunicatorMain {



    public void sendToStepOne();

    public void sendStringToMain(String string);

    public void startSignature(String id,String serviceID);




}
