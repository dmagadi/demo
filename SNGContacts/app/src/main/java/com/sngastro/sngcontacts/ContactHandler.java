package com.sngastro.sngcontacts;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.Callback;
/**
 * Created by Aamir on 7/29/2015.
 */
public interface ContactHandler {

    @GET("/contacts")
    public void readContacts(Callback<ArrayList<ContactInfo>> response);

}
