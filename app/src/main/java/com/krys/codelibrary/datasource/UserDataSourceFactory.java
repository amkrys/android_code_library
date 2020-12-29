package com.krys.codelibrary.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.krys.codelibrary.models.UserResponse;

public class UserDataSourceFactory extends DataSource.Factory {

    private UserDataSource itemDataSource;
    private MutableLiveData<PageKeyedDataSource<Integer, UserResponse.Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, UserResponse.Item> create() {
        itemDataSource = new UserDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, UserResponse.Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

}