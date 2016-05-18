package com.sngastro.sngcontacts.httpclient;

import com.sngastro.sngcontacts.contact.ContactInfo;

import java.util.ArrayList;

/**
 * Created by Aamir on 5/15/2016.
 */
public interface IGetContacts {

    void successfulGetContacts(ArrayList<ContactInfo> contactInfoArrayList);
    void failedToGetContacts();

}
