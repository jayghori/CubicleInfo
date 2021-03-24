package com.app.practical;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactTask extends AsyncTask {

    Context context;
    OnContactAvailableListener listener;

    ContactTask(Context context, OnContactAvailableListener listener) {
        this.context = context;
        this.listener = listener;
    }

    private List<Contacts> contactsList = new ArrayList<>();

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d("TAGER", "doInBackground: ");

        RoomDatabase database = RoomDatabase.getDatabase(context);

        Log.d("TAGER", "contalist=null: ");
        ApiServices apiServices = ApiInstance.getRetrofitInstance().create(ApiServices.class);
        apiServices.getContacts().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Log.d("TAGER", "onResponse: " + response.body().toString());
                contactsList = response.body().getContacts();
                listener.onContactsAvailable(contactsList);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.d("TAGER", "onFailure: " + t.getMessage());
            }
        });


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
