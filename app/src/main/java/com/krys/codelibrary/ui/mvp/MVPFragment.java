package com.krys.codelibrary.ui.mvp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.UsersAdapter;
import com.krys.codelibrary.interfaces.UserView;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.presenter.UserPresenterCompl;
import com.krys.codelibrary.presenter.UsersPresenter;
import com.krys.codelibrary.utils.CustomProgressDialog;

import java.util.List;

public class MVPFragment extends Fragment implements UserView {

    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;
    private CustomProgressDialog progressDialog;
    private UsersPresenter usersPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mvp, container, false);
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
        setPresenter();
    }

    private void setPresenter() {
        usersPresenter = new UserPresenterCompl(this);
        usersPresenter.setProgressBarVisiblity(true);
        usersPresenter.getUsers();
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


    @Override
    public void onClearData() {
        usersAdapter.clear();
    }

    @Override
    public void onAPIResponse(List<UserResponse.Item> items) {
        usersAdapter.addAll(items);
        usersPresenter.setProgressBarVisiblity(false);
    }

    @Override
    public void onSetProgressBarVisibility(boolean value) {
        if(value){
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}