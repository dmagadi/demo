package com.sngastro.sngcontacts.httpclient;

/**
 * Created by Aamir on 5/15/2016.
 */
public interface ILogin {

    void onSuccessfulLoginAttempt(Boolean loginSuccessful);

    void onFailedLoginAttempt();

}
