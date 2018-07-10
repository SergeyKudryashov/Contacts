package com.ss.contacts.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ss.contacts.R;
import com.ss.contacts.adapter.ContactsAdapter;
import com.ss.contacts.adapter.HasContactAdapter;
import com.ss.contacts.manager.Contacts;

public class FavoritesFragment extends Fragment implements HasContactAdapter {
    public static final String TITLE = "Favorites";

    private ContactsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private void init(View view) {
        mAdapter = new ContactsAdapter(getContext(), Contacts.getInstance().getFavorites());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public ContactsAdapter getAdapter() {
        return mAdapter;
    }
}
