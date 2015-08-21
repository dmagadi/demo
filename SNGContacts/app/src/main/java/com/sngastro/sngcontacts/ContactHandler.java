package com.sngastro.sngcontacts;

import java.util.ArrayList;
import java.util.Map;

import retrofit.http.GET;
import retrofit.Callback;
import retrofit.http.QueryMap;

/**
 * Created by Aamir on 7/29/2015.
 */
public interface ContactHandler {

    @GET("/contacts")
    public void readContacts(Callback<ArrayList<ContactInfo>> response);

    @GET("/login")
    public void doLogin(@QueryMap Map<String, String> options,Callback<StartActivity.Result> response);
}
