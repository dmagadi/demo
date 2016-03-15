package com.sngastro.sngcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sngastro.sngcontacts.adapter.ContactArrayAdapter;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.contact.SelfCertUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class ContactListActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    public static final String ENDPOINT = "https://sngcontactinfo.duckdns.org:61120";

    ArrayList<ContactInfo> contactList;


    private ListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        // create a class ContactData with46 name, phone number

        // add some dummy contact data objects to array

        // display custom view and display name number and a call button

        final ListView listView = (ListView) findViewById(R.id.contactListView);
        contactList = new ArrayList<>();

//        contactList.add(new ContactInfo("Aamir Godil", "(916)783-5816", "cellNumber", "godil.aamir1@gmail.com"));
//        contactList.add(new ContactInfo("Aslam Godil", "(916)783-5816", "(530)263-2478", "aslamgodilmd@yahoo.com"));
//        contactList.add(new ContactInfo("Faraaz Godil", "(916)783-5816", "cellNumber", "emailAddress"));

        OkHttpClient okHttpClient = SelfCertUtils.configureClient(new OkHttpClient());

        adapter = new ContactArrayAdapter(this, contactList);




        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).setEndpoint(ENDPOINT).build();
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

                        ArrayList<ContactInfo> list = new ArrayList<ContactInfo>();
                        list.add(contactInfo);
                        i.putExtra("ContactInfo", list);
                        startActivity(i);
                    }
                });



            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "failure");
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

}
