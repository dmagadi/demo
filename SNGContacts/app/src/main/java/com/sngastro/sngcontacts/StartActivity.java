package com.sngastro.sngcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sngastro.sngcontacts.contact.SelfCertUtils;
import com.squareup.okhttp.OkHttpClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String password = null;
    String user = null;
    boolean loginSuccessful;
    EditText passField;
    EditText userField;

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String createHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        passField = (EditText) findViewById(R.id.passwordField);
        userField = (EditText) findViewById(R.id.userField);
        Log.i(TAG, "onCreate");
    }

    public void onClick(View v) {

        MessageDigest md = null;
        password = passField.getText().toString();
        user = userField.getText().toString();
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Log.e(TAG, "Error while creating HASH", ex);
        }

        byte[] thedigest = md.digest(password.getBytes());
        String passwordHash = createHexString(thedigest);


        login(user, passwordHash);
        Log.i(TAG, "onClick");
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
    }

    private void login(String user, String password) {

        OkHttpClient okHttpClient = SelfCertUtils.configureClient(new OkHttpClient());

        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).setEndpoint(ContactListActivity.ENDPOINT).build();

        ContactHandler handler = restAdapter.create(ContactHandler.class);


        Map<String, String> params = new HashMap<String, String>();

        params.put("user", user);
        params.put("password", password);

        handler.doLogin(params, new Callback<Result>() {


            @Override
            public void success(Result result, Response response) {

                if (result.value.equals("SUCCESS")) {
                    loginSuccessful = true;
                    Intent i = new Intent(getApplicationContext(), ContactListActivity.class);
                    startActivity(i);
                } else {
                    showErrorMessage();
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "Failure");
            }
        });

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
