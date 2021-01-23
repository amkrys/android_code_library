package com.krys.codelibrary.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movies")
public class MoviesRoomModel {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;

    @ColumnInfo(name = "release_date")
    public String releaseDate;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "isBestRated")
    public Boolean isBestRated;

    @ColumnInfo(name = "isNewVideo")
    public Boolean isNewVideo;

    public MoviesRoomModel(String id, String thumbnail, String releaseDate, String title, Boolean isBestRated, Boolean isNewVideo) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.releaseDate = releaseDate;
        this.title = title;
        this.isBestRated = isBestRated;
        this.isNewVideo = isNewVideo;
    }
}
