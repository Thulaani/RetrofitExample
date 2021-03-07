package com.example.retrofitpract.network;

import com.example.retrofitpract.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {
    @GET("marvel")
    Call<List<User>> getUsers();
}
