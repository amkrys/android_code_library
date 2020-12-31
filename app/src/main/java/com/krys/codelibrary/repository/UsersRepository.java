package com.krys.codelibrary.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.networking.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.krys.codelibrary.datasource.UserDataSource.FIRST_PAGE;
import static com.krys.codelibrary.datasource.UserDataSource.PAGE_SIZE;
import static com.krys.codelibrary.datasource.UserDataSource.SITE_NAME;

public class UsersRepository {

    private List<UserResponse.Item> dataList = new ArrayList<>();
    private MutableLiveData<List<UserResponse.Item>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<List<UserResponse.Item>> getUsers() {
        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            dataList.addAll(response.body().items);
                            mutableLiveData.setValue(dataList);
                            isLoading.setValue(false);
                            callFinished();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        Log.e("error", "onFailure: "+t.getLocalizedMessage());
                    }
                });
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> callFinished() {
        return isLoading;
    }
}
