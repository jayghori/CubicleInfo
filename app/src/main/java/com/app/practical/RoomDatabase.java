package com.app.practical;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

@Database(entities = {Contacts.class}, exportSchema = false, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public static RoomDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "Contact")
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract Dao getDao();
}
