package com.krys.codelibrary.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.krys.codelibrary.dao.MoviesDao;
import com.krys.codelibrary.models.MoviesRoomModel;

@Database(entities = {MoviesRoomModel.class}, version = 1)
public abstract class MoviesDatabse extends RoomDatabase {

    public static final String DATABASE_NAME = "movieDatabase";

    public abstract MoviesDao moviesDao();

    private static volatile MoviesDatabse INSTANCE;

    public static MoviesDatabse getInstance(Context context){
        if(INSTANCE == null){
            synchronized (MoviesDatabse.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, MoviesDatabse.class, DATABASE_NAME)
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDB(INSTANCE);
        }
    };

    public static class PopulateDB extends AsyncTask<Void, Void, Void>{

        private MoviesDao moviesDao;

        PopulateDB(MoviesDatabse moviesDatabse){
            this.moviesDao = moviesDatabse.moviesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            moviesDao.deleteAll();
            return null;
        }
    }

}
