package com.krys.codelibrary.ui.pagination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.UsersPagingAdapter;
import com.krys.codelibrary.models.UserResponse;

public class PaginationFragment extends Fragment {

    private PaginationViewModel paginationViewModel;
    private RecyclerView rvUsers;
    private UsersPagingAdapter usersPagingAdapter;

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
        usersPagingAdapter = new UsersPagingAdapter(getActivity());
        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUsers.setHasFixedSize(true);
    }

    private void observeApiCall() {
        paginationViewModel.getItemPagedList().observe(requireActivity(), new Observer<PagedList<UserResponse.Item>>() {
            @Override
            public void onChanged(PagedList<UserResponse.Item> items) {
                usersPagingAdapter.submitList(items);
            }
        });
        rvUsers.setAdapter(usersPagingAdapter);
    }
}