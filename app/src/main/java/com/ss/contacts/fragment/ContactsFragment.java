package com.ss.contacts.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ss.contacts.manager.Contacts;
import com.ss.contacts.R;
import com.ss.contacts.adapter.ContactsAdapter;
import com.ss.contacts.model.Contact;

public class ContactsFragment extends Fragment {
    public static final String TITLE = "CONTACTS";

    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
        askForContactPermission();
    }

    private void init(View view) {
        mAdapter = new ContactsAdapter(getContext(), Contacts.getInstance().getContacts());

        mRecyclerView = view.findViewById(R.id.recycler_view_contacts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getContacts() {
        Cursor phones = getActivity().getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            Contact contact = new Contact();
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            Contacts.getInstance().add(contact);
        }
        phones.close();
    }

    public void askForContactPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Contacts access needed");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage("please confirm Contacts access");//TODO put real question
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(
                                new String[]{Manifest.permission.READ_CONTACTS},
                                0);
                        getContacts();

                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        0);
                getContacts();

            }
        } else {
            getContacts();
        }

    }
}