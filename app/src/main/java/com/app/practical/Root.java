package com.app.practical;

import java.util.List;

public class Root {
    List<Contacts> contacts;

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Root(List<Contacts> contacts) {
        this.contacts = contacts;
    }
}
