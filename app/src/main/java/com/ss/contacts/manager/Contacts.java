package com.ss.contacts.manager;

import com.ss.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

    private List<Contact> mContacts;
    private static Contacts mInstance;

    public static Contacts getInstance() {
        if (mInstance == null) {
            mInstance = new Contacts();
        }
        return mInstance;
    }

    private Contacts() {
        mContacts = new ArrayList<>();
    }

    public void add(Contact contact) {
        mContacts.add(contact);
    }

    public List<Contact> getContacts() {
        return mContacts;
    }
}
