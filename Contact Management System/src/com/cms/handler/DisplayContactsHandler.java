/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cms.handler;

import com.cms.data.Contact;
import com.cms.main.IHandle;
import java.util.ArrayList;

/**
 *
 * @author dmagadi
 */
public class DisplayContactsHandler implements IHandle {

    public DisplayContactsHandler() {
    }

    @Override
    public void handle(ArrayList<Contact> contacts) {
        for(Contact contact: contacts) {
            contact.printContact();
        }
    }
    
}
