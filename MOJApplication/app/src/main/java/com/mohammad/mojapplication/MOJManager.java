package com.mohammad.mojapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.Telephony;

import com.mohammad.mojapplication.MOJdatabase.MOJCursorWraper;
import com.mohammad.mojapplication.MOJdatabase.MOJDbHelper;
import com.mohammad.mojapplication.MOJdatabase.MOJDbSchema;
import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.Service;
import com.mohammad.mojapplication.Objects.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.mohammad.mojapplication.MOJdatabase.MOJDbSchema.*;

/**
 * Created by user on 11/4/2015.
 */
public class MOJManager {


    private static MOJManager mojManager;
    private SQLiteDatabase database;
    private Context context;


    public MOJManager(Context context) {
        this.context = context.getApplicationContext();
        database = new MOJDbHelper(this.context).getWritableDatabase();
    }

    public static MOJManager getMOJManager(Context context) {

        if (mojManager == null) {
            mojManager = new MOJManager(context);

        }
        return mojManager;

    }

    // USER DATA TABLE ---------------------------------------------------------
    private MOJCursorWraper querryUserTable(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                UserTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new MOJCursorWraper(cursor);

    }

    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.ID, user.getId());
        values.put(UserTable.Cols.NAME, user.getName());
        values.put(UserTable.Cols.MOBILE, user.getAddress());
        values.put(UserTable.Cols.ADDRESS, user.getAddress());
        values.put(UserTable.Cols.USER_NAME, user.getUserName());
        values.put(UserTable.Cols.PASS, user.getPass());
        values.put(UserTable.Cols.SERVICEPASS, user.getServicePass());

        return values;
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserTable.NAME, null, values);
    }


    public void update(User user) {
        String id = user.getId();
        ContentValues values = getContentValues(user);
        database.update(UserTable.NAME, values, UserTable.Cols.ID +" = ?",new String[] {id.toString()});

    }

    public User findUserById(String  id) {

        MOJCursorWraper cursorWraper =
                querryUserTable(UserTable.Cols.ID + " = ?", new String[]{id.toString()});

        try {
            if (cursorWraper.getCount() == 0) {
                return null;
            }

            cursorWraper.moveToFirst();
            return cursorWraper.getUser();
        } finally {
            cursorWraper.close();
        }


    }

    public User findUserByUserName(String userName) {
        MOJCursorWraper cursorWraper =
                querryUserTable(UserTable.Cols.USER_NAME + " = ?", new String[]{userName});

        try {
            if (cursorWraper.getCount() == 0) {
                return null;
            }

            cursorWraper.moveToFirst();
            return cursorWraper.getUser();
        } finally {
            cursorWraper.close();
        }


    }


    //  NATIONAL ID DATABASE -----------------------------------------------------------------------

    private MOJCursorWraper querryEmiratesIDTable(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                EmiratesIDTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new MOJCursorWraper(cursor);

    }

    private static ContentValues getContentValuesNID(NIDCard nidCard) {
        ContentValues values = new ContentValues();
        values.put(EmiratesIDTable.Cols.ID, nidCard.getId());
        values.put(EmiratesIDTable.Cols.NAME, nidCard.getName());
        values.put(EmiratesIDTable.Cols.ADDRESS, nidCard.getAddress());
        values.put(EmiratesIDTable.Cols.DOB, nidCard.getDob().getTime());
        values.put(EmiratesIDTable.Cols.MOBILE, nidCard.getMobile());
        return values;
    }

    public void addNIDCard(NIDCard nidCard) {
        ContentValues values = getContentValuesNID(nidCard);
        database.insert(EmiratesIDTable.NAME, null, values);
    }

    public NIDCard findNIDCardById(String id) {
        MOJCursorWraper cursorWraper =
                querryEmiratesIDTable(EmiratesIDTable.Cols.ID + " = ?", new String[]{id.toString()});

        try {
            if (cursorWraper.getCount() == 0) {
                return null;
            }

            cursorWraper.moveToFirst();
            return cursorWraper.getNID();
        } finally {
            cursorWraper.close();
        }


    }

    //-------------------------------------Service Database---------------------------------------------------
    private MOJCursorWraper querryServiceTable(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                ServiceTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new MOJCursorWraper(cursor);

    }

    private static ContentValues getContentValuesService(Service service) {
        ContentValues values = new ContentValues();
        values.put(ServiceTable.Cols.USERID, service.getUserID());
        values.put(ServiceTable.Cols.TYPE, service.getType());
        values.put(ServiceTable.Cols.SERVICEID, service.getServiceID());
        values.put(ServiceTable.Cols.DATE, service.getDate().getTime());
        values.put(ServiceTable.Cols.SERVICESTATUS, service.getServiceStatus());
        values.put(ServiceTable.Cols.LOCATION, service.getServiceStatus());


        return values;
    }

    public void addService(Service service) {
        ContentValues values = getContentValuesService(service);
        database.insert(ServiceTable.NAME, null, values);
    }

    public User findServiceById(String id) {
        MOJCursorWraper cursorWraper =
                querryServiceTable(ServiceTable.Cols.SERVICEID + " = ?", new String[]{id.toString()});

        try {
            if (cursorWraper.getCount() == 0) {
                return null;
            }

            cursorWraper.moveToFirst();
            return cursorWraper.getUser();
        } finally {
            cursorWraper.close();
        }


    }

    public List<Service> findServiceByUserId(String id) {
        MOJCursorWraper cursorWraper = querryServiceTable(null,null);
        List<Service> services = new ArrayList<>();


        try {
            cursorWraper.moveToFirst();
            while (!cursorWraper.isAfterLast()) {
                if (cursorWraper.getService().getUserID().equals(id)) {
                    services.add(cursorWraper.getService());
                }

                cursorWraper.moveToNext();
            }

        } finally {
            cursorWraper.close();
        }

        return services;

    }



    //-----------------------------------PartyDb---------------------------------------------




    private MOJCursorWraper querryPartyTable(String whereClause,String[] whereArgs) {
        Cursor cursor = database.query(
                PartyTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new MOJCursorWraper(cursor);

    }

    private static ContentValues getContentValuesParty(Party party) {
        ContentValues values = new ContentValues();
        values.put(PartyTable.Cols.PARTYID,party.getPartyID());
        values.put(PartyTable.Cols.FNAME,party.getfName());
        values.put(PartyTable.Cols.TYPE,party.getType());
        values.put(PartyTable.Cols.MOBILE,party.getMobile());
        values.put(PartyTable.Cols.ADDRESS,party.getAddress());
        values.put(PartyTable.Cols.IMAGE1,party.getImage1());





        return values;
    }

    public void addParty(Party party) {

        ContentValues values = getContentValuesParty(party);
        database.insert(PartyTable.NAME, null, values);
    }

    public User findpartyById(String  id) {
        MOJCursorWraper cursorWraper =
                querryPartyTable(PartyTable.Cols.PARTYID + " = ?", new String[]{id.toString()});

        try
        {
            if(cursorWraper.getCount() == 0)
            {
                return null;
            }

            cursorWraper.moveToFirst();
            return cursorWraper.getUser();
        }
        finally {
            cursorWraper.close();
        }


    }






}
