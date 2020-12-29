package com.krys.codelibrary.ui.pagination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.UsersAdapter;
import com.krys.codelibrary.models.UserResponse;

public class PaginationFragment extends Fragment {

    private PaginationViewModel paginationViewModel;
    private RecyclerView rvUsers;
    private UsersAdapter usersAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        paginationViewModel = ViewModelProviders.of(this).get(PaginationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pagination, container, false);
        findViewById(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void findViewById(View root) {
        rvUsers = root.findViewById(R.id.rvUsers);
    }

    private void init() {
        setUpUserRv();
        observeApiCall();
    }

    private void setUpUserRv() {
        usersAdapter = new UsersAdapter(getActivity());
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUsers.setHasFixedSize(true);
    }

    private void observeApiCall() {
        paginationViewModel.itemPagedList.observe(requireActivity(), new Observer<PagedList<UserResponse.Item>>() {
            @Override
            public void onChanged(PagedList<UserResponse.Item> items) {
                usersAdapter.submitList(items);
            }
        });
        rvUsers.setAdapter(usersAdapter);
    }
}