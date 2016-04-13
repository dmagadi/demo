package com.sngastro.sngcontacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

/**
 * Created by dmagadi on 4/12/16.
 */
public class DatabaseService {
    SQLLiteDBHandler dbhandler = null;

    public DatabaseService(Context context){

        dbhandler = new SQLLiteDBHandler(context);

    }


    public void createSetting(String name, String value){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("value", value);
        // check duplicate and update
        dbhandler.getWritableDatabase().insert("SETTING", "", values);

    }

    public String getSetting(String name){
        Cursor cursor = handler.getReadableDatabase().rawQuery("select * from SETTING where name = '" + name + "'",null,null);
        String value ="";

        while(!cursor.isLast()) {

            cursor.moveToNext();

            value = cursor.getString(1);

            Log.d(TAG, "********************************************************************************************** " + value);


        }

        return value;

    }
}
