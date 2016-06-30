package com.sngastro.sngcontacts;

import android.app.Activity;
import android.app.Application;

import com.sngastro.sngcontacts.httpclient.ClientHandler;

/**
 * Created by Aamir on 6/6/2016.
 */
public class SNGContactInfoApplication extends Application {

    private ClientHandler handler;
    private DatabaseService service;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new ClientHandler();
        service = new DatabaseService(getApplicationContext());
    }

    public ClientHandler getClientHandler() {
        return handler;
    }

    public DatabaseService getDatabaseService() {
        return service;
    }
}
