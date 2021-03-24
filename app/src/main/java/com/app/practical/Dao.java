package com.app.practical;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM Contacts")
    List<Contacts> getContacts();

    @Insert
    void addContact(Contacts contacts);
}
