package com.ss.contacts.fragment;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ss.contacts.adapter.HasContactAdapter;
import com.ss.contacts.manager.Contacts;
import com.ss.contacts.R;
import com.ss.contacts.adapter.ContactsAdapter;
import com.ss.contacts.model.Contact;

import java.io.Serializable;

import static android.Manifest.permission.READ_CONTACTS;

public class ContactsFragment extends Fragment implements HasContactAdapter, Serializable {
    public static final String TITLE = "CONTACTS";
    private static final int REQUEST_READ_CONTACTS = 444;

    private ContactsAdapter mAdapter;

    public ContactsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view) {
        mAdapter = new ContactsAdapter(getContext(), Contacts.getInstance().getContacts());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        getContacts();
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (getActivity().checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            }
        }
    }

    private void getContacts() {
        if (!mayRequestContacts()) {
            return;
        }

        Cursor phones = getActivity().getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            Contact contact = new Contact();
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            if (Contacts.getInstance().getContacts().contains(contact))
                continue;
            Contacts.getInstance().add(contact);
        }
        phones.close();
        Contacts.getInstance().sort();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
    }
}