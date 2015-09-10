package com.sngastro.sngcontacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sngastro.sngcontacts.adapter.PhoneNumberAdapter;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.PhoneNumber;


public class ContactViewActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    ContactInfo contactInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);
        contactInfo = (ContactInfo) getIntent().getSerializableExtra("ContactInfo");
        TextView nameView = (TextView) findViewById(R.id.nameText);
        nameView.setText(contactInfo.getFirstName() + " " + contactInfo.getLastName());
        ArrayAdapter<PhoneNumber> adapter = new PhoneNumberAdapter(this, contactInfo.getPhoneNumbers());
        ListView phoneView = (ListView) findViewById(R.id.phoneNumberListView);
        phoneView.setAdapter(adapter);

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

/*
    public void addContact(View v) {

        Intent i = new Intent(ContactsContract.Intents.Insert.ACTION);
        i.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        i.putExtra(ContactsContract.Intents.Insert.NAME, contactInfo.getName());
        i.putExtra(ContactsContract.Intents.Insert.PHONE, contactInfo.getCell())
        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        i.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, contactInfo.getHome())
        .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
        i.putExtra(ContactsContract.Intents.Insert.EMAIL, contactInfo.getEmail())
        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
        startActivity(i);

}
*/

}