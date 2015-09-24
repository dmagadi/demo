package com.sngastro.sngcontacts;

import android.content.Intent;
import android.os.Bundle;
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

        Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
        i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        i.putExtra(ContactsContract.Intents.Insert.NAME, contactInfo.getFirstName())
        .putExtra(ContactsContract.Intents.Insert.NAME, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
        i.putExtra(ContactsContract.Intents.Insert.NAME, contactInfo.getLastName())
        .putExtra(ContactsContract.Intents.Insert.NAME, ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
        for (PhoneNumber number: contactInfo.getPhoneNumbers()) {
            switch (number.getType()) {
                case "HOME":
                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
                    break;
                case "WORK":
                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
                    break;
                case "CELL":
                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    break;
                default:
                    i.putExtra(ContactsContract.Intents.Insert.PHONE, number.getNumber())
                            .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_OTHER);
                    break;
            }
        }
        for (Email email: contactInfo.getEmails()) {
            switch (email.getType()) {
                case "HOME":
                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
                    break;
                case "WORK":
                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                    break;
                case "CELL":
                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_MOBILE);
                    break;
                default:
                    i.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getEmail())
                            .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_OTHER);
                    break;
            }
        }
        startActivity(i);

    }


}