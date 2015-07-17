package com.sngastro.sngcontacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ContactViewActivity extends ActionBarActivity {

    private static final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);
        final ContactInfo contactInfo = (ContactInfo) getIntent().getSerializableExtra("ContactInfo");
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView cellView = (TextView) findViewById(R.id.cell);
        TextView homeView = (TextView) findViewById(R.id.home);
        TextView emailView = (TextView) findViewById(R.id.email);
        nameView.setText(contactInfo.getName());
        cellView.setText(contactInfo.getCell());
        homeView.setText(contactInfo.getHome());
        emailView.setText(contactInfo.getEmail());
        Button cellCallButton = (Button) findViewById(R.id.cellCallButton);
        cellCallButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactInfo.getCell()));
                startActivity(i);
                //finish();
            }

        });
        Button homeCallButton = (Button) findViewById(R.id.homeCallButton);
        homeCallButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contactInfo.getHome()));
                startActivity(i);
            }

        });


        Log.i(TAG, "onCreate");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_home:
                finish();
                return true;

            default:
                return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_view, menu);
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
