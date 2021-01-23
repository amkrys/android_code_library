package com.krys.codelibrary.ui.room;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.krys.codelibrary.models.MoviesRoomModel;
import com.krys.codelibrary.repository.MoviesRepository;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        moviesRepository =new MoviesRepository(application);
    }

    public void callMoviesApi(Context context){
        moviesRepository.callMoviesApi(context);
    }

    public LiveData<List<MoviesRoomModel>> getListRoomData(){
        return moviesRepository.getListRoomData();
    }

}