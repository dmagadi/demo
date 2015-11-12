package com.sngastro.sngcontacts;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sngastro.sngcontacts.adapter.EmailAdapter;
import com.sngastro.sngcontacts.adapter.PhoneNumberAdapter;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.Email;
import com.sngastro.sngcontacts.contact.PhoneNumber;

import java.util.ArrayList;
import java.util.List;


public class ContactViewActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    ContactInfo contactInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);
        List<ContactInfo> contactInfos = (List) getIntent().getSerializableExtra("ContactInfo");

         contactInfo = contactInfos.get(0);


        TextView nameView = (TextView) findViewById(R.id.nameText);
        nameView.setText(contactInfo.getFirstName() + " " + contactInfo.getLastName());
        ArrayAdapter<PhoneNumber> phoneAdapter = new PhoneNumberAdapter(this, contactInfo.getPhoneNumbers());
        ListView phoneView = (ListView) findViewById(R.id.phoneNumberListView);
        phoneView.setAdapter(phoneAdapter);
        ArrayAdapter<Email> emailAdapter = new EmailAdapter(this, contactInfo.getEmails());
        ListView emailView = (ListView) findViewById(R.id.emailListView);
        emailView.setAdapter(emailAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                finish();
                return true;
            default:
                return true;
        }
    }


    public void addContact(View v) {

//        Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
//        i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
//        i.putExtra(ContactsContract.Intents.Insert.NAME, contactInfo.getFirstName())
//        .putExtra(ContactsContract.Intents.Insert.NAME, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
//        i.putExtra(ContactsContract.Intents.Insert.NAME, contactInfo.getLastName())
//        .putExtra(ContactsContract.Intents.Insert.NAME, ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
//        for (PhoneNumber number: contactInfo.getPhoneNumbers()) {
//            switch (number.getType()) {
//                case "HOME":
//                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
//                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
//                    break;
//                case "WORK":
//                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
//                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
//                    break;
//                case "CELL":
//                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
//                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
//                    break;
//                default:
//                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
//                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
//                    break;
//            }
//        }
//        for (Email email: contactInfo.getEmails()) {
//            switch (email.getType()) {
//                case "HOME":
//                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
//                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
//                    break;
//                case "WORK":
//                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
//                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
//                    break;
//                case "CELL":
//                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
//                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_MOBILE);
//                    break;
//                default:
//                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
//                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_OTHER);
//                    break;
//            }
//        }
//        startActivity(i);

        ArrayList<ContentValues> data = new ArrayList<ContentValues>();


//        ContentValues row0 = new ContentValues();
//        row0.put(ContactsContract.Data.MIMETYPE, ContactsContract.Contacts.CONTENT_TYPE);
//
//        row0.put(ContactsContract.Intents.Insert.NAME ,"Godil,Aamir");
//        row0.put(ContactsContract.CommonDataKinds.Nickname.NAME, "Aamir,Godil");
//        row0.put(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_NAME, "Aamir");
//
//        data.add(row0);

        /*ContentValues row1 = new ContentValues();
        row1.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
        row1.put(ContactsContract.CommonDataKinds.Organization.COMPANY, "Android2");
        data.add(row1);*/
        String home = null;
        String work = null;
        String cell = null;
        String other = null;
        for (Email email: contactInfo.getEmails()) {
            if (email.getType().equalsIgnoreCase("HOME")) {
                home = email.getEmail();
            } else
            if (email.getType().equalsIgnoreCase("CELL")) {
                cell = email.getEmail();
            } else
            if (email.getType().equalsIgnoreCase("WORK")) {
                work = email.getEmail();
            } else {
                other = email.getEmail();
            }
        }
        ContentValues row2 = new ContentValues();
        row2.put(android.provider.ContactsContract.Data.MIMETYPE, android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.TYPE, android.provider.ContactsContract.CommonDataKinds.Email.TYPE_HOME);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.LABEL, home);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.TYPE, android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.LABEL, cell);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.TYPE, android.provider.ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.LABEL, work);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.TYPE, android.provider.ContactsContract.CommonDataKinds.Email.TYPE_OTHER);
        row2.put(android.provider.ContactsContract.CommonDataKinds.Email.LABEL, other);
        data.add(row2);

        home = null;
        work = null;
        cell = null;
        other = null;
        for (PhoneNumber number: contactInfo.getPhoneNumbers()) {
            if (number.getType().equalsIgnoreCase("HOME")) {
                home = number.getNumber();
            } else
            if (number.getType().equalsIgnoreCase("CELL")) {
                cell = number.getNumber();
            } else
            if (number.getType().equalsIgnoreCase("WORK")) {
                work = number.getNumber();
            } else {
                other = number.getNumber();
            }
        }
        ContentValues row1 = new ContentValues();
        row1.put(android.provider.ContactsContract.Data.MIMETYPE, android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.TYPE, android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.LABEL, home);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.TYPE, android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.LABEL, cell);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.TYPE, android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.LABEL, work);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.TYPE, android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
        row1.put(android.provider.ContactsContract.CommonDataKinds.Phone.LABEL, other);
        data.add(row1);

        Intent intent = new Intent(android.provider.ContactsContract.Intents.Insert.ACTION);
        intent.setType(android.provider.ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putParcelableArrayListExtra(android.provider.ContactsContract.Intents.Insert.DATA, data);
        intent.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, contactInfo.getFirstName() + " " + contactInfo.getLastName());
        startActivity(intent);

    }


}