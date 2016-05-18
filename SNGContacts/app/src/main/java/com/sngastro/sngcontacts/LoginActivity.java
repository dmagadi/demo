package com.sngastro.sngcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.httpclient.ClientHandler;
import com.sngastro.sngcontacts.httpclient.IGetContacts;
import com.sngastro.sngcontacts.httpclient.ILogin;
import com.sngastro.sngcontacts.httpclient.SelfCertUtils;
import com.sngastro.sngcontacts.httpclient.ContactHandler;
import com.sngastro.sngcontacts.httpclient.Result;
import com.squareup.okhttp.OkHttpClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    String password = null;
    String user = null;
    EditText passField;
    EditText userField;
    //int fail = 0;
    String passwordHash;
    Button btn;
    //protected static String ENDPOINT = "https://192.168.3.114:61120";

    ClientHandler clientHandler;

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
        setContentView(R.layout.activity_login);
        userField = (EditText) findViewById(R.id.userField);
        passField = (EditText) findViewById(R.id.passwordField);
        btn = (Button) findViewById(R.id.button);
        clientHandler = new ClientHandler();
        Log.i(TAG, "onCreate");
    }

    public void onClick(View v) {
        btn.setEnabled(false);
        MessageDigest md = null;
        password = passField.getText().toString();
        user = userField.getText().toString();
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Log.e(TAG, "Error while creating HASH", ex);
        }

        byte[] thedigest = md.digest(password.getBytes());
        passwordHash = createHexString(thedigest);


        clientHandler.login(user, passwordHash, new ILogin() {
            @Override
            public void onSuccessfulLoginAttempt(Boolean loginSuccessful) {
                passField.setText("");
                btn.setEnabled(true);
                if (loginSuccessful) {
                    clientHandler.getContactsFromServer(new IGetContacts() {
                        @Override
                        public void successfulGetContacts(ArrayList<ContactInfo> contactInfoArrayList) {
                            Intent i = new Intent(getApplicationContext(), ContactListActivity.class);
                            i.putExtra("ContactInfoList", contactInfoArrayList);
                            i.putExtra("ClientHandler", clientHandler);
                            startActivity(i);
                        }

                        @Override
                        public void failedToGetContacts() {
                            showErrorMessage();
                        }
                    });
                } else {
                    showIncorrectLoginMessage();
                }
            }

            @Override
            public void onFailedLoginAttempt() {
                passField.setText("");
                btn.setEnabled(true);
                showErrorMessage();
            }
        });

        Log.i(TAG, "onClick");
    }

    private void showIncorrectLoginMessage() {
        Toast.makeText(this, "Incorrect username/password", Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage() {
        Toast.makeText(this, "Unable to connect", Toast.LENGTH_SHORT).show();
    }

/*    private void login(final String user, String password) {

        OkHttpClient okHttpClient = SelfCertUtils.configureClient(new OkHttpClient(), 6);
        if (fail == 1) {
            okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        }

        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(okHttpClient)).setEndpoint(ENDPOINT).build();
        ContactHandler handler = restAdapter.create(ContactHandler.class);
        ENDPOINT = "https://192.168.3.114:61120";

        Map<String, String> params = new HashMap<String, String>();

        params.put("user", user);
        params.put("password", password);

        handler.doLogin(params, new Callback<Result>() {


            @Override
            public void success(Result result, Response response) {
                btn.setEnabled(true);
                passField.setText("");
                if (result.value.equals("SUCCESS")) {
                    Intent i = new Intent(getApplicationContext(), ContactListActivity.class);
                    startActivity(i);
                } else {
                    showIncorrectLoginMessage();
                }

            }

            @Override
            public void failure(RetrofitError error) {

                Log.i(TAG, "Failure");
                fail++;
                if (fail >= 2) {
                    passField.setText("");
                    btn.setEnabled(true);
                } else {
                    ENDPOINT = "https://sngcontactinfo.duckdns.org:61120";
                    login(user, passwordHash);
                }
            }
        });

    }*/

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

}
