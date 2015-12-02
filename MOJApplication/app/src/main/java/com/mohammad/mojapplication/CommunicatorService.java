package com.mohammad.mojapplication;

import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;

/**
 * Created by user on 11/3/2015.
 */
public interface CommunicatorService {




    public void sendToStepTwo(Service service,User user,Party party,Party party2);
    public void sendToStepThree(Service service,User user,Party party,Party party2);

    public void sendToAdd(int one,int two);
    public void sendToAddTwo(Party party,int one,int two);

    public void backFromAdd(Party party,int one, int two);
    public void backFromAddTwo(Party party,Party party2,int one, int two);

    public void stopStepOne();
    public void stopStepTwo();
    public void stopStepAdd();


    public void backtoMain(String userid);

}
