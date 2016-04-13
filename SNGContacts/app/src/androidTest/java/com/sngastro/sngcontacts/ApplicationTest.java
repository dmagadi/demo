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
    public void testCreateSqlLite() {

        Log.d(TAG, "**********************************************************************************************" );

        try {
            SQLLiteDBHandler handler = new SQLLiteDBHandler(getContext());
            Log.d(TAG, "********************************************************************************************** " + handler.getReadableDatabase().getVersion());
            ContentValues values = new ContentValues();
            values.put("name", "Aslam");
            values.put("value", "Aamir - Value");

            handler.getWritableDatabase().insert("SETTING", "", values);

            Cursor cursor = handler.getReadableDatabase().rawQuery("select * from SETTING",null,null);


            while(!cursor.isLast()) {

                cursor.moveToNext();

                String name = cursor.getString(0);

                Log.d(TAG, "********************************************************************************************** " + name);
            }

        }catch (Throwable e){
            e.printStackTrace();
        }




    }

}