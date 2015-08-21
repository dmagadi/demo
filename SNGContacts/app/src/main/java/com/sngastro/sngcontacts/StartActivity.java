package com.sngastro.sngcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import retrofit.Callback;
import retrofit.RestAdapter;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String password = null;
    String user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        EditText passField = (EditText) findViewById(R.id.passwordField);
        password = passField.getText().toString();
        EditText userField = (EditText) findViewById(R.id.userField);
        user = userField.getText().toString();
        Log.i(TAG, "onCreate");
    }

    public void onClick(View v) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Log.e(TAG,"Error while creating HASH",ex);
        }

        byte[] thedigest = md.digest(password.getBytes());
        String passwordHash = createHexString(thedigest);


        if (doLogin(user, passwordHash)) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT);
        }
        Log.i(TAG, "onClick");
    }

    private boolean doLogin(String user ,String password) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(MainActivity.ENDPOINT).build();
        ContactHandler handler = restAdapter.create(ContactHandler.class);

        Map<String,String> params = new HashMap<String, String>() ;

        params.put("user",user);
        params.put("password",password);


        handler.doLogin(new Callback<Result>() {

        }) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        // add sqllite driver to project before
        // open sqllite database
        // if the db is empty add dummy contacts ...


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    class Result {
        String value;
    }

}
