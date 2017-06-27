package com.donorschoose;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Leslie Arizaga on 5/23/2017.
 */

public interface API {

    @GET("/common/json_feed.html")
    void getInfo(@Query("state") String state,@Query("costToCompleteRange") String costToCompleteRange,@Query("max") String max,@Query("subject1") String subject1, @Query("keyword") String  keyword,@Query("APIKey") String APIKey, Callback<Donor> callback);
}
