package com.krys.codelibrary.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.krys.codelibrary.dao.MoviesDao;
import com.krys.codelibrary.database.MoviesDatabse;
import com.krys.codelibrary.models.MoviesResponse;
import com.krys.codelibrary.models.MoviesRoomModel;
import com.krys.codelibrary.networking.RetrofitClient;
import com.krys.codelibrary.networking.RoomRetrofitClient;
import com.krys.codelibrary.utils.ConstantStrings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private MoviesDatabse moviesDatabse;
    private LiveData<List<MoviesRoomModel>> responseLiveData;

    public MoviesRepository(Context context) {
        moviesDatabse = MoviesDatabse.getInstance(context);
        responseLiveData = moviesDatabse.moviesDao().getMovies();
    }

    public void insert(MoviesRoomModel movie) {
        new InsertTask(moviesDatabse).execute(movie);
    }

    public LiveData<List<MoviesRoomModel>> getListRoomData() {
        return responseLiveData;
    }

    public void callMoviesApi(Context context) {
        new CallApiTask().execute();
    }

    private class CallApiTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            RoomRetrofitClient.getInstance()
                    .getApi()
                    .getMovies()
                    .enqueue(new Callback<MoviesResponse>() {
                        @Override
                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                            if (response.body() != null) {
                                Log.e("TAG", "onResponse: " + new Gson().toJson(response.body()));
                                saveDataInDB(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
                            Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
                        }
                    });
            return null;
        }
    }

    private void saveDataInDB(MoviesResponse response) {
        String thumbnail = "";
        String movieYear = "";

        for (int j = 0; j < response.movies.size(); j++) {
            thumbnail = response.movies.get(j).backdropPath != null ? ConstantStrings.BACK_DROP_BASE +
                    response.movies.get(j).backdropPath : (response.movies.get(j).posterPath != null ? ConstantStrings.POSTER_BASE +
                    response.movies.get(j).posterPath : "");

            movieYear = response.movies.get(j).releaseDate.split("-")[0];

            insert(new MoviesRoomModel(response.movies.get(j).id, thumbnail, movieYear,
                    response.movies.get(j).title,
                    response.movies.get(j).rating > 5,
                    response.movies.get(j).backdropPath != null));
        }
    }

    private class InsertTask extends AsyncTask<MoviesRoomModel, Void, Void> {

        private MoviesDao moviesDao;

        InsertTask(MoviesDatabse moviesDatabse) {
            moviesDao = moviesDatabse.moviesDao();
        }

        @Override
        protected Void doInBackground(MoviesRoomModel... movies) {
            moviesDao.insert(movies[0]);
            return null;
        }
    }
}
