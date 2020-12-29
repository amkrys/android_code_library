package com.krys.codelibrary.networking;

import com.krys.codelibrary.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClass {

    @GET("answers")
    Call<UserResponse> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);
}