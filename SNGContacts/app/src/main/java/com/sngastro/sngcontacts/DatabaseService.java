package com.sngastro.sngcontacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.Email;
import com.sngastro.sngcontacts.contact.PhoneNumber;

import java.util.ArrayList;

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
        cursor.close();

        return value;

    }

    public void updateSetting(String name, String value) {
        dbhandler.getWritableDatabase().rawQuery("UPDATE SETTING SET value = ? WHERE name = ?", new String[]{value, name});
    }

    public ArrayList<ContactInfo> getContactInfos() {

        ArrayList<ContactInfo> contactInfoArrayList = new ArrayList<>();

        Cursor cursor;

        cursor = dbhandler.getReadableDatabase().rawQuery("SELECT * FROM CONTACT_INFO", null);
        Cursor phoneNumberCursor;
        Cursor emailCursor;
        while (cursor.moveToNext()) {
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setFirstName(cursor.getString(cursor.getColumnIndex("FirstName")));
            contactInfo.setLastName(cursor.getString(cursor.getColumnIndex("LastName")));
            int id = cursor.getInt(cursor.getColumnIndex("contact_id"));
            ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
            phoneNumberCursor = dbhandler.getReadableDatabase().rawQuery("SELECT * FROM PHONE_NUMBERS WHERE contact_id = ?", new String[]{String.valueOf(id)});
            while (phoneNumberCursor.moveToNext()) {
                PhoneNumber number = new PhoneNumber();
                number.setNumber(phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex("phone_number")));
                number.setType(phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex("phone_type")));
                phoneNumbers.add(number);
            }
            phoneNumberCursor.close();
            contactInfo.setPhoneNumbers(phoneNumbers);
            ArrayList<Email> emails = new ArrayList<>();
            emailCursor = dbhandler.getReadableDatabase().rawQuery("SELECT * from", new String[]{String.valueOf(id)});
            while (emailCursor.moveToNext()) {
                Email email = new Email();
                email.setEmail(emailCursor.getString(emailCursor.getColumnIndex("email")));
                email.setType(emailCursor.getString(emailCursor.getColumnIndex("email_type")));
                emails.add(email);
            }
            emailCursor.close();
            contactInfo.setEmails(emails);
            contactInfoArrayList.add(contactInfo);
        }

        cursor.close();

        return contactInfoArrayList;

    }

}
