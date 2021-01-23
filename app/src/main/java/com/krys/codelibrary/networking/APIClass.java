package com.krys.codelibrary.networking;

import com.krys.codelibrary.models.MoviesResponse;
import com.krys.codelibrary.models.UserResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClass {

    @GET("answers")
    Call<UserResponse> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);

    @GET("3/movie/upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-US&page=1&region=IN|US&with_release_type=2|3")
    Call<MoviesResponse> getMovies();
}