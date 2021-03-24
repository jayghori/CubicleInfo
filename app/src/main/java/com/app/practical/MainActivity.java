package com.app.practical;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                Dao dao = RoomDatabase.getDatabase(MainActivity.this).getDao();
                List<Contacts> contactsList = dao.getContacts();
                if (contactsList.size() == 0) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Loading from API", Toast.LENGTH_SHORT).show();
                        }
                    });
                    getContacts();
                } else {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Loading from Room DB", Toast.LENGTH_SHORT).show();
                                }
                            });
                            initRecyclerView(contactsList);
                        }
                    });
                }
            }
        }.start();

    }

    void getContacts() {
        new ContactTask(this, new OnContactAvailableListener() {
            @Override
            public void onContactsAvailable(List<Contacts> contactsList) {


                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addContactToDb(contactsList);
                        initRecyclerView(contactsList);
                    }
                });

            }
        }).execute();
    }

    private void initRecyclerView(List<Contacts> contactsList) {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setAdapter(new ContactAdapter(contactsList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addContactToDb(List<Contacts> contacts){
        Dao dao = RoomDatabase.getDatabase(MainActivity.this).getDao();
        new Thread() {
            @Override
            public void run() {
                for (Contacts contacts : contacts) {
                    dao.addContact(contacts);
                }
            }
        }.start();
    }
}