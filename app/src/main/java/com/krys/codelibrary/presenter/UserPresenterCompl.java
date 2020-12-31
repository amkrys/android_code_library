package com.krys.codelibrary.presenter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.krys.codelibrary.interfaces.UserView;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.networking.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.krys.codelibrary.datasource.UserDataSource.FIRST_PAGE;
import static com.krys.codelibrary.datasource.UserDataSource.PAGE_SIZE;
import static com.krys.codelibrary.datasource.UserDataSource.SITE_NAME;

public class UserPresenterCompl implements UsersPresenter {

    private UserView userView;
    private UserResponse userResponse;
    private Handler handler;

    public UserPresenterCompl(UserView userView) {
        this.userView = userView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void clear() {
        userView.onClearData();
    }

    @Override
    public void getUsers() {
        userView.onSetProgressBarVisibility(true);
        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            userView.onAPIResponse(response.body().items);
                            userView.onSetProgressBarVisibility(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        userView.onSetProgressBarVisibility(false);
                        Log.e("error", "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void setProgressBarVisiblity(boolean visibility){
        userView.onSetProgressBarVisibility(visibility);
    }

    private void initUser(){
        userResponse = new UserResponse();
    }
}