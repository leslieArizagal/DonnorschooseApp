package com.donorschoose;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Leslie Arizaga on 5/23/2017.
 */

public class RestClient {

    private static API REST_CLIENT;
    private static String ROOT = "http://api.donorschoose.org";


    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static API get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ROOT);

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);


        builder.setClient(new OkClient(okHttpClient));
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.build();

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(API.class);

    }
}
