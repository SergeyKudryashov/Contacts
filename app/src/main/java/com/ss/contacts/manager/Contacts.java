package com.ss.contacts.manager;

import com.ss.contacts.model.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contacts {

    private List<Contact> mContacts;
    private List<Contact> mFavorites;
    private static Contacts mInstance;

    public static Contacts getInstance() {
        if (mInstance == null) {
            mInstance = new Contacts();
        }
        return mInstance;
    }

    private Contacts() {
        mContacts = new ArrayList<>();
        mFavorites = new ArrayList<>();
    }

    public void add(Contact contact) {
        mContacts.add(contact);
    }

    public void changeFavorite(Contact contact, boolean isChecked) {
        if (isChecked) {
            if (!mFavorites.contains(contact))
                mFavorites.add(contact);
        }
        else {
            mFavorites.remove(contact);
        }
    }

    public Contact getItem(int position) {
        return mContacts.get(position);
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

    public List<Contact> getFavorites() {
        return mFavorites;
    }

    public void sort() {
        Collections.sort(mContacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}