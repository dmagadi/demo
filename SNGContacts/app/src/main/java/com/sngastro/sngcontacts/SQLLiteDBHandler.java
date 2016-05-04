package com.sngastro.sngcontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by dmagadi on 3/29/16.
 */
public class SQLLiteDBHandler extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "SNGContactsDB";

    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_SETTING = "create table if not exists SETTING " +
            "(name text primary key not null, value text not null);";
    private static final String CREATE_TABLE_CONTACT_INFO = "CREATE TABLE IF NOT EXISTS CONTACT_INFO " +
            "(contact_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, FirstName text NOT NULL, LastName text NOT NULL);";
    public static final String CREATE_TABLE_PHONE_NUMBERS = "CREATE TABLE IF NOT EXISTS PHONE_NUMBERS " +
            "(id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, phone_number text NOT NULL, phone_type text NOT NULL, contact_id int(11) NOT NULL, FOREIGN KEY (contact_id) REFERENCES CONTACT_INFO(contact_id));";
    public static final String CREATE_TABLE_EMAIL = "CREATE TABLE IF NOT EXISTS EMAIL " +
            "(id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, email text NOT NULL, email_type text NOT NULL, contact_id int(11) NOT NULL, FOREIGN KEY (contact_id) REFERENCES CONTACT_INFO(contact_id));";

    public SQLLiteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_SETTING);
        database.execSQL(CREATE_TABLE_CONTACT_INFO);
        database.execSQL(CREATE_TABLE_PHONE_NUMBERS);
        database.execSQL(CREATE_TABLE_EMAIL);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(SQLLiteDBHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS EMAIL;");
        database.execSQL("DROP TABLE IF EXISTS PHONE_NUMBERS;");
        database.execSQL("DROP TABLE IF EXISTS CONTACT_INFO;");
        onCreate(database);
    }


}
