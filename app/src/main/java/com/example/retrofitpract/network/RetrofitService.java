package com.example.retrofitpract.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final static String BASE_URL = "https://simplifiedcoding.net/demos/";
    private static volatile Retrofit instance = null;

    public static RetrofitClient getInstance() {
        /*****
         * Android concept - Double checked locking
         *          Safe threading
         *          Only created once it is needed
         *          Once the instance is created, the instance won't be changed and the next thread will use the same instance rather to create a new instance.
         */
        //

        if(instance == null){
            synchronized (RetrofitService.class){
                if(instance == null){
                    instance = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BASE_URL)
                            .build();
                }
            }
        }
        return instance.create(RetrofitClient.class);
    }
}
