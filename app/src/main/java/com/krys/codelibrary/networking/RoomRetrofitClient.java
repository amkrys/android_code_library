package com.krys.codelibrary.networking;

import com.krys.codelibrary.utils.ConstantStrings;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomRetrofitClient {

    private static RoomRetrofitClient mInstance;
    private Retrofit retrofit;


    private RoomRetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ConstantStrings.ROOM_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RoomRetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RoomRetrofitClient();
        }
        return mInstance;
    }

    public APIClass getApi() {
        return retrofit.create(APIClass .class);
    }
}