package com.krys.codelibrary.ui.mvvm;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.UsersAdapter;
import com.krys.codelibrary.adapters.UsersPagingAdapter;
import com.krys.codelibrary.models.UserResponse;
import com.krys.codelibrary.utils.CustomProgressDialog;

import java.util.List;

public class MVVMFragment extends Fragment {

    private MVVMViewModel mViewModel;
    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;
    private CustomProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MVVMViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mvvm, container, false);
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
        observeApiProgress();
        observeApiCall();
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

    private void observeApiProgress() {
        mViewModel.isLoading().observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void observeApiCall() {
        mViewModel.getUsers().observe(requireActivity(), new Observer<List<UserResponse.Item>>() {
            @Override
            public void onChanged(List<UserResponse.Item> userResponses) {
                usersAdapter.addAll(userResponses);
            }
        });
    }
}