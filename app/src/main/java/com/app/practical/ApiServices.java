package com.app.practical;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("contacts")
    Call<Root> getContacts();

}
