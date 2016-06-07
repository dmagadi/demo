package com.sngastro.sngcontacts.httpclient;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.sngastro.sngcontacts.contact.ContactInfo;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by Aamir on 5/15/2016.
 */
public class ClientHandler {

    private int fail = 0;
    private static String INTERNAL_ENDPOINT = "https://192.168.3.114:61120";
    private static String EXTERNAL_ENDPOINT = "https://sngcontactinfo.duckdns.org:61120";
    private OkHttpClient client;
    private RestAdapter restAdapter;
    private ContactHandler handler;

    public ClientHandler() {

        createClient(INTERNAL_ENDPOINT, 4);

    }

    private void createClient(String endpoint, int timeout) {

        client = SelfCertUtils.configureClient(new OkHttpClient(), timeout);
        restAdapter = new RestAdapter.Builder().setClient(new OkClient(client)).setEndpoint(endpoint).build();
        handler = restAdapter.create(ContactHandler.class);

    }

    public void login(final String user, final String passwordhash, final ILogin loginInterface) {

        Map<String, String> params = new HashMap<String, String>();

        params.put("user", user);
        params.put("password", passwordhash);

        handler.doLogin(params, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                loginInterface.onLoginAttempt(Boolean.valueOf(result.value.equalsIgnoreCase("SUCCESS")));
            }

            @Override
            public void failure(RetrofitError error) {
                fail++;
                if (fail >= 2) {
                    loginInterface.onLoginAttempt(null);
                } else {
                    createClient(EXTERNAL_ENDPOINT, 15);
                    login(user, passwordhash, loginInterface);
                }
            }
        });

    }

    public void getContactsFromServer(final IGetContacts contactsInterface) {

        handler.readContacts(new Callback<ArrayList<ContactInfo>>() {
            @Override
            public void success(ArrayList<ContactInfo> contactInfoArrayList, Response response) {
                contactsInterface.successfulGetContacts(contactInfoArrayList);
            }

            @Override
            public void failure(RetrofitError error) {
                createClient(INTERNAL_ENDPOINT, 4);
                contactsInterface.failedToGetContacts();
            }
        });

    }

}
