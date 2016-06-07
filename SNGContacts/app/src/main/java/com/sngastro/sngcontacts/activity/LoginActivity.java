package com.sngastro.sngcontacts.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sngastro.sngcontacts.DatabaseService;
import com.sngastro.sngcontacts.R;
import com.sngastro.sngcontacts.SNGContactInfoApplication;
import com.sngastro.sngcontacts.contact.ContactInfo;
import com.sngastro.sngcontacts.httpclient.ClientHandler;
import com.sngastro.sngcontacts.httpclient.IGetContacts;
import com.sngastro.sngcontacts.httpclient.ILogin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
    DatabaseService service;
    ClientHandler handler;

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
        SNGContactInfoApplication application = (SNGContactInfoApplication) getApplication();
        service = application.getDatabaseService();
        handler = application.getClientHandler();
        setContentView(R.layout.activity_login);
        userField = (EditText) findViewById(R.id.userField);
        passField = (EditText) findViewById(R.id.passwordField);
        userField.requestFocus();
        btn = (Button) findViewById(R.id.button);
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


        handler.login(user, passwordHash, new ILogin() {
            @Override
            public void onLoginAttempt(Boolean loginSuccessful) {
                passField.setText("");
                btn.setEnabled(true);
                if (loginSuccessful == null) {
                    startListActivity(service.getContactInfos());
                } else {
                    if (loginSuccessful) {
                        handler.getContactsFromServer(new IGetContacts() {
                            @Override
                            public void successfulGetContacts(ArrayList<ContactInfo> contactInfoArrayList) {
                                service.setContactInfos(contactInfoArrayList);
                                startListActivity(contactInfoArrayList);
                            }

                            @Override
                            public void failedToGetContacts() {
                                Log.d(TAG, "failedToGetContacts()");
                                startListActivity(service.getContactInfos());
                            }
                        });
                    } else {
                        LoginActivity.this.showIncorrectLoginMessage();
                    }
                }

            }
        });

        Log.i(TAG, "onClick");
    }

    private void startListActivity(ArrayList<ContactInfo> contactInfoArrayList) {
        Intent i = new Intent(getApplicationContext(), ContactListActivity.class);

        i.putExtra("ContactInfoList", contactInfoArrayList);

        startActivity(i);
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
