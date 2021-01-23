package com.krys.codelibrary.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.krys.codelibrary.models.MoviesRoomModel;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MoviesRoomModel movie);

    @Query("SELECT * FROM movies")
    LiveData<List<MoviesRoomModel>> getMovies();

    @Query("SELECT EXISTS(SELECT * FROM movies)")
    Boolean isExists();

    @Update
    void update(MoviesRoomModel movie);

    @Query("DELETE FROM movies")
    void deleteAll();

}
