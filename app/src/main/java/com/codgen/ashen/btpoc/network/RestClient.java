package com.codgen.ashen.btpoc.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AsheN on 7/29/2017.
 */

public class RestClient {
    private Retrofit mClient;

    public RestClient(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .retryOnConnectionFailure(true)
                .readTimeout(5,TimeUnit.MINUTES)
                .writeTimeout(5,TimeUnit.MINUTES)
                .build();

        mClient = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public StudentAPI buildApi (){
        return mClient.create(StudentAPI.class);
    }

    public Retrofit retrofit() { return mClient; }
}
