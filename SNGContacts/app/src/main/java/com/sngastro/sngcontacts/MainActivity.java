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

import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.adapter.ContactArrayAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tag";

    public static final String ENDPOINT = "https://192.168.1.253:8888";

    ArrayList<ContactInfo> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create a class ContactData with name, phone number

        // add some dummy contact data objects to array

        // display custom view and display name number and a call button

        final ListView listView = (ListView) findViewById(R.id.contactListView);
        contactList = new ArrayList<>();

//        contactList.add(new ContactInfo("Aamir Godil", "(916)783-5816", "cellNumber", "godil.aamir1@gmail.com"));
//        contactList.add(new ContactInfo("Aslam Godil", "(916)783-5816", "(530)263-2478", "aslamgodilmd@yahoo.com"));
//        contactList.add(new ContactInfo("Faraaz Godil", "(916)783-5816", "cellNumber", "emailAddress"));
        
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(ENDPOINT).build();
        ContactHandler handler = restAdapter.create(ContactHandler.class);
        handler.readContacts(new Callback<ArrayList<ContactInfo>>() {
            @Override
            public void onResponse(Response<ArrayList<ContactInfo>> response, Retrofit retrofit) {
                contactList.addAll(response.body());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        ListAdapter adapter = new ContactArrayAdapter(this, contactList);
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
        Log.i(TAG, "onCreate");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);

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

}
