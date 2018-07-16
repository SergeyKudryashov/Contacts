package com.ss.contacts.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ss.contacts.R;
import com.ss.contacts.manager.Contacts;
import com.ss.contacts.model.Contact;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView mNameTextView;
    private TextView mPhoneNumberTextView;
    private CheckBox mFavoriteCheckBox;

    private Contact contact;

    public ContactViewHolder(View itemView) {
        super(itemView);
        mNameTextView = itemView.findViewById(R.id.contact_name_label);
        mPhoneNumberTextView = itemView.findViewById(R.id.contact_phone_number_label);
        mFavoriteCheckBox = itemView.findViewById(R.id.contact_favorite_check_box);
        mFavoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("-------", "contact: " + contact.getName() + " isChecked = " + isChecked);
                Contacts.getInstance().changeFavorite(contact, isChecked);
            }
        });
    }

    public void bind(final Contact pContact) {
        contact = pContact;
        mNameTextView.setText(contact.getName());
        mPhoneNumberTextView.setText(contact.getPhoneNumber());
        mFavoriteCheckBox.setChecked(Contacts.getInstance().getFavorites().contains(contact));
    }
}
