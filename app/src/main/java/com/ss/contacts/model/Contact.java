package com.ss.contacts.model;

import java.util.UUID;

public class Contact {

    private UUID mId;
    private String mName;
    private String mPhoneNumber;

    public Contact() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((mId == null) ? 0 : mId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (mId == null) {
            return other.mId == null;
        } else {
            return mName.trim().equals(other.mName.trim())
                    && mPhoneNumber.trim().equals(other.mPhoneNumber.trim());
        }
    }

    @Override
    public String toString() {
        return "[" + mId + "]:"
                + mName + "|"
                + mPhoneNumber;
    }
}
