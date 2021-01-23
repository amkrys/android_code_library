package com.krys.codelibrary.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesResponse {

    @SerializedName("results")
    @Expose
    public List<Movie> movies = new ArrayList<>();

    public class Movie {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;
        @SerializedName("popularity")
        @Expose
        public Double rating;
    }
}
