package com.sngastro.sngcontacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.Email;
import com.sngastro.sngcontacts.contact.PhoneNumber;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dmagadi on 4/12/16.
 */
public class DatabaseService {
    private String TAG = "**********************************************************************************************";
    private SQLLiteDBHandler dbhandler = null;

    public DatabaseService(Context context) {

        dbhandler = new SQLLiteDBHandler(context);

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

        cursor = dbhandler.getReadableDatabase().rawQuery("SELECT * FROM CONTACT_INFO ORDER BY LastName, FirstName", null);
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
            emailCursor = dbhandler.getReadableDatabase().rawQuery("SELECT * from EMAIL WHERE contact_id = ?", new String[]{String.valueOf(id)});
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

    public void setContactInfos(ArrayList<ContactInfo> contactInfos) {

        dbhandler.getWritableDatabase().execSQL("PRAGMA foreign_keys = OFF;");
        if (dbhandler.getReadableDatabase().rawQuery("SELECT * FROM PHONE_NUMBERS;", null).moveToNext()) {
            dbhandler.getWritableDatabase().execSQL("DELETE FROM PHONE_NUMBERS;");
        }
        if (dbhandler.getReadableDatabase().rawQuery("SELECT * FROM EMAIL;", null).moveToNext()) {
            dbhandler.getWritableDatabase().execSQL("DELETE FROM EMAIL;");
        }
        if (dbhandler.getReadableDatabase().rawQuery("SELECT * FROM CONTACT_INFO;", null).moveToNext()) {
            dbhandler.getWritableDatabase().execSQL("DELETE FROM CONTACT_INFO;");
        }
        dbhandler.getWritableDatabase().execSQL("PRAGMA foreign_keys = ON;");
        for (ContactInfo i: contactInfos) {
            ContentValues values = new ContentValues();
            values.put("FirstName", i.getFirstName());
            values.put("LastName", i.getLastName());
            long id = dbhandler.getWritableDatabase().insert("CONTACT_INFO", "", values);
            for (PhoneNumber n: i.getPhoneNumbers()) {
                ContentValues values1 = new ContentValues();
                values1.put("phone_number", n.getNumber());
                values1.put("phone_type", n.getType());
                values1.put("contact_id", id);
                dbhandler.getWritableDatabase().insert("PHONE_NUMBERS", "", values1);
            }
            for (Email e: i.getEmails()) {
                ContentValues values2 = new ContentValues();
                values2.put("email", e.getEmail());
                values2.put("email_type", e.getType());
                values2.put("contact_id", id);
                dbhandler.getWritableDatabase().insert("EMAIL", "", values2);
            }
        }

    }

}