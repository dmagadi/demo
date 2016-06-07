package com.sngastro.sngcontacts;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

public class ApplicationTest extends ApplicationTestCase<Application> {
    String TAG = "ApplicationTest";
    public ApplicationTest() {
        super(Application.class);
    }

    public void testAddRecord() {
        SQLLiteDBHandler handler = new SQLLiteDBHandler(getContext());
        ContentValues values = new ContentValues();
        values.put("FirstName", "Aamir");
        values.put("LastName", "Godil");
        handler.getWritableDatabase().insert("CONTACT_INFO", "", values);
    }

    public void testCreateSqlLite() {

        Log.d(TAG, "**********************************************************************************************");

        try {
            SQLLiteDBHandler handler = new SQLLiteDBHandler(getContext());
            Log.d(TAG, "********************************************************************************************** " + handler.getReadableDatabase().getVersion());
            ContentValues values = new ContentValues();
            values.put("name", "Aslam");
            values.put("value", "val1");

            ContentValues values2 = new ContentValues();
            values2.put("name", "Aamir");
            values2.put("value", "val2");

            handler.getWritableDatabase().insert("SETTING", "", values);
            handler.getWritableDatabase().insert("SETTING", "", values2);

            Cursor cursor = handler.getReadableDatabase().rawQuery("select * from SETTING where name = ?", new String[] {"Aamir"});


            while(cursor.moveToNext()) {


                String name = cursor.getString(1);

                Log.d(TAG, "********************************************************************************************** " + name);
            }

        }catch (Throwable e){
            e.printStackTrace();
        }




    }

    public void testDropDatabase() {
        getContext().deleteDatabase("SNGContactsDB");
    }

}