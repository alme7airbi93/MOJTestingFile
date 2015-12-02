package com.mohammad.mojapplication.MOJdatabase;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;

import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

/**
 * Created by user on 10/28/2015.
 */
public class MOJCursorWraper extends CursorWrapper {




    public MOJCursorWraper(Cursor cursor)
    {
        super(cursor);
    }

    public User getUser()
    {
        String id = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.ID));
        String name = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.NAME));
        String mobile = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.MOBILE));
        String address = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.ADDRESS));
        String username = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.USER_NAME));
        String pass = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.PASS));
        String servicePass = getString(getColumnIndex(MOJDbSchema.UserTable.Cols.SERVICEPASS));


        User user = new User(id,name,mobile,address,username,pass,servicePass);


        return user;

    }

    public NIDCard getNID()
    {
        String id = getString(getColumnIndex(MOJDbSchema.EmiratesIDTable.Cols.ID));
        String name = getString(getColumnIndex(MOJDbSchema.EmiratesIDTable.Cols.NAME));
        String mobile = getString(getColumnIndex(MOJDbSchema.EmiratesIDTable.Cols.MOBILE));
        String address = getString(getColumnIndex(MOJDbSchema.EmiratesIDTable.Cols.ADDRESS));
        long dob = getLong(getColumnIndex(MOJDbSchema.EmiratesIDTable.Cols.DOB));



        NIDCard nidCard = new NIDCard(id,name,mobile,address,new Date(dob));


        return nidCard;

    }

    public Service getService()
    {
        String userID = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.USERID));
        String serviceType = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.TYPE));
        String serviceID = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.SERVICEID));
        long date = getLong(getColumnIndex(MOJDbSchema.ServiceTable.Cols.DATE));
        String serviceStatus = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.SERVICESTATUS));
        String partyid1 = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.PARTYID1));
        String partyid2 = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.PARTYID2));
        String loc = getString(getColumnIndex(MOJDbSchema.ServiceTable.Cols.LOCATION));


        Service service = new Service(userID, serviceType, serviceID, new Date(date), serviceStatus,partyid1,partyid2,loc);


        return service;

    }

    public Party getParty()
    {
        String partyID = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.PARTYID));
        String fName = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.FNAME));
        String type = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.TYPE));
        String mobile = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.MOBILE));
        String address = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.ADDRESS));
        String image1 = getString(getColumnIndex(MOJDbSchema.PartyTable.Cols.IMAGE1));







        Party party = new Party(partyID,fName,type,mobile,address,image1);


        return party;

    }
}
