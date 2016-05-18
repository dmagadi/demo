package com.sngastro.sngcontacts.httpclient;

import android.content.Context;

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
public class ClientHandler implements Serializable {

    private int fail = 0;
    private String INTERNAL_ENDPOINT = "https://192.168.3.114:61120";
    private String EXTERNAL_ENDPOINT = "https://sngcontactinfo.duckdns.org:61120";
    private OkHttpClient client;
    private RestAdapter restAdapter;
    private ContactHandler handler;

    public ClientHandler() {

        createClient(INTERNAL_ENDPOINT, 4);

    }

    private void createClient(String endpoint, int timeout) {

        client = SelfCertUtils.configureClient(new OkHttpClient(), timeout);
        restAdapter = new RestAdapter.Builder().setClient(new OkClient(client)).setEndpoint(INTERNAL_ENDPOINT).build();
        handler = restAdapter.create(ContactHandler.class);

    }

    public void login(final String user, final String passwordhash, final ILogin loginInterface) {

        Map<String, String> params = new HashMap<String, String>();

        params.put("user", user);
        params.put("password", passwordhash);

        handler.doLogin(params, new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                loginInterface.onSuccessfulLoginAttempt(result.value.equalsIgnoreCase("SUCCESS"));
            }

            @Override
            public void failure(RetrofitError error) {
                fail++;
                if (fail >= 2) {
                    loginInterface.onFailedLoginAttempt();
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
