package com.krys.codelibrary.ui.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.repository.UsersRepository;

import java.util.List;

public class MVVMViewModel extends ViewModel {

    UsersRepository repository;

    public MVVMViewModel() {
        repository = new UsersRepository();
    }

    public MutableLiveData<List<UserResponse.Item>> getUsers() {
        return repository.getUsers();
    }

    public MutableLiveData<Boolean> isLoading(){
        return repository.callFinished();
    }
}