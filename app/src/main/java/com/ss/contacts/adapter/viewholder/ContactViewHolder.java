package com.ss.contacts.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ss.contacts.R;
import com.ss.contacts.model.Contact;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView mNameTextView;
    private TextView mPhoneNumberTextView;
    private CheckBox mFavoriteCheckBox;

    public ContactViewHolder(View itemView) {
        super(itemView);
        mNameTextView = itemView.findViewById(R.id.contact_name_label);
        mPhoneNumberTextView = itemView.findViewById(R.id.contact_phone_number_label);
        mFavoriteCheckBox = itemView.findViewById(R.id.contact_favorite_check_box);
    }

    public void bind(Contact contact) {
        mNameTextView.setText(contact.getName());
        mPhoneNumberTextView.setText(contact.getPhoneNumber());
    }
}