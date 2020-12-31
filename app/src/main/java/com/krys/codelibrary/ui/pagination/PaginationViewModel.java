package com.krys.codelibrary.ui.pagination;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.krys.codelibrary.datasource.UserDataSource;
import com.krys.codelibrary.datasource.UserDataSourceFactory;
import com.krys.codelibrary.models.UserResponse;

public class PaginationViewModel extends ViewModel {

    private LiveData<PagedList<UserResponse.Item>> itemPagedList;
    private LiveData<PageKeyedDataSource<Integer, UserResponse.Item>> liveDataSource;

    public PaginationViewModel() {
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = userDataSourceFactory.getItemLiveDataSource();
        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setPageSize(UserDataSource.PAGE_SIZE).build();
        itemPagedList = new LivePagedListBuilder(userDataSourceFactory, pagedListConfig).build();
    }

    public LiveData<PagedList<UserResponse.Item>> getItemPagedList(){
        return itemPagedList;
    }
}