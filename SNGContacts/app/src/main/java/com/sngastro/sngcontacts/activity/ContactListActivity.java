package com.sngastro.sngcontacts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sngastro.sngcontacts.DatabaseService;
import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.SNGContactInfoApplication;
import com.sngastro.sngcontacts.adapter.ContactArrayAdapter;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.httpclient.ClientHandler;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    SNGContactInfoApplication application;

    ArrayList<ContactInfo> contactList;

    ClientHandler handler;

    DatabaseService service;

    private ListAdapter adapter = null;
    ListView listView;
    //int fail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        // create a class ContactData with46 name, phone number

        // add some dummy contact data objects to array

        // display custom view and display name number and a call button

        listView = (ListView) findViewById(R.id.contactListView);
        Bundle extras = getIntent().getExtras();
        application = (SNGContactInfoApplication) getApplication();
        handler = application.getClientHandler();
        service = application.getDatabaseService();
        contactList = (ArrayList<ContactInfo>) extras.get("ContactInfoList");
        adapter = new ContactArrayAdapter(this, contactList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactInfo contactInfo = (ContactInfo) listView.getItemAtPosition(position);
                Intent i = new Intent(ContactListActivity.this.getApplicationContext(), ContactViewActivity.class);

                ArrayList<ContactInfo> list = new ArrayList<>();
                list.add(contactInfo);
                i.putExtra("ContactInfo", list);
                ContactListActivity.this.startActivity(i);

            }
        });

        Log.i(TAG, "onListActivityCreate");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.i(TAG, "onCreateOptionsMenu");
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    public void onLogoutClick(View v) {
        finish();
    }

/*    private void displayList() {
        OkHttpClient okHttpClient = SelfCertUtils.configureClient(new OkHttpClient(), 6);
        if (fail == 1) {
            okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        }
        adapter = new ContactArrayAdapter(this, contactList);




        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).setEndpoint(LoginActivity.ENDPOINT).build();
        LoginActivity.ENDPOINT = "https://192.168.3.114:61120";
        ContactHandler handler = restAdapter.create(ContactHandler.class);
        handler.readContacts(new Callback<ArrayList<ContactInfo>>() {

            @Override
            public void success(ArrayList<ContactInfo> contactInfos, Response response) {
                contactList.addAll(contactInfos);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ContactInfo contactInfo = (ContactInfo) listView.getItemAtPosition(position);
                        Intent i = new Intent(getApplicationContext(), ContactViewActivity.class);

                        ArrayList<ContactInfo> list = new ArrayList<>();
                        list.add(contactInfo);
                        i.putExtra("ContactInfo", list);
                        startActivity(i);
                    }
                });



            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "failure");
                fail++;
                if (fail >= 2) {

                } else {
                    LoginActivity.ENDPOINT = "https://sngcontactinfo.duckdns.org:61120";
                    displayList();
                }

            }

        });
    }*/

}
