package com.krys.codelibrary.ui.mvc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.UsersAdapter;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.networking.RetrofitClient;
import com.krys.codelibrary.utils.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.krys.codelibrary.datasource.UserDataSource.FIRST_PAGE;
import static com.krys.codelibrary.datasource.UserDataSource.PAGE_SIZE;
import static com.krys.codelibrary.datasource.UserDataSource.SITE_NAME;

public class MVCFragment extends Fragment {

    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;
    private CustomProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mvc, container, false);
        findViewById(root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void findViewById(View root) {
        rvUsers = root.findViewById(R.id.rvUsers);
    }

    private void init() {
        setUpProgress();
        setUpUserRv();
        getUsers();
    }

    private void setUpProgress() {
        progressDialog = new CustomProgressDialog(getActivity());
    }

    private void setUpUserRv() {
        usersAdapter = new UsersAdapter(getActivity());
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUsers.setHasFixedSize(true);
        rvUsers.setAdapter(usersAdapter);
    }

    private void getUsers() {
        progressDialog.show();
        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.body() != null) {
                            usersAdapter.addAll(response.body().items);
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.e("error", "onFailure: " + t.getLocalizedMessage());
                    }
                });
    }

}