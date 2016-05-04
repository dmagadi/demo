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
    String TAG = "**********************************************************************************************";
    SQLLiteDBHandler dbhandler = null;

    public DatabaseService(Context context) {

        dbhandler = new SQLLiteDBHandler(context);

    }


    public void createSetting(String name, String value) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("value", value);
        // check duplicate and update
        dbhandler.getWritableDatabase().insert("SETTING", "", values);

    }

    public String getSetting(String name) {
        Cursor cursor = dbhandler.getReadableDatabase().rawQuery("select * from SETTING where name = ?", new String[]{name});
        String value = "";

        while (cursor.moveToNext()) {

            value = cursor.getString(cursor.getColumnIndex("value"));

            Log.d(TAG, value);


        }

        return value;

    }

    public void updateSetting(String name, String value) {
        dbhandler.getWritableDatabase().rawQuery("UPDATE SETTING SET value = ? WHERE name = ?", new String[]{value, name});
    }

}
