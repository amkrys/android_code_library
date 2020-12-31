package com.krys.codelibrary.interfaces;

import com.krys.codelibrary.models.UserResponse;

import java.util.List;

public interface UserView {
    public void onClearData();
    public void onAPIResponse(List<UserResponse.Item> items);
    public void onSetProgressBarVisibility(boolean visibility);
}
