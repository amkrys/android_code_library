package com.krys.codelibrary.ui.room;

import androidx.annotation.LayoutRes;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krys.codelibrary.R;
import com.krys.codelibrary.adapters.MoviesAdapter;
import com.krys.codelibrary.models.MoviesRoomModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomFragment extends Fragment {

    private RoomViewModel mViewModel;

    private RecyclerView rvMovies;
    private RecyclerView rvNewReleaseMovies;
    private RecyclerView rvBestRatedMovies;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_room, container, false);
        findViewById(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void findViewById(View root) {
        rvMovies = root.findViewById(R.id.rvMovies);
        rvNewReleaseMovies = root.findViewById(R.id.rvNewReleaseMovies);
        rvBestRatedMovies = root.findViewById(R.id.rvBestRatedMovies);
    }

    private void init() {
        observeApiCall();
    }

    private void observeApiCall() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            getActivity().runOnUiThread(() -> mViewModel.callMoviesApi(requireActivity()));
            handler.post(this::observeDBData);
        });
    }

    private void observeDBData() {
        mViewModel.getListRoomData().observe(this, new Observer<List<MoviesRoomModel>>() {
            @Override
            public void onChanged(List<MoviesRoomModel> movies) {
                setUpData(movies);
            }
        });
    }

    private void setUpData(List<MoviesRoomModel> movies) {
        MoviesAdapter moviesAdapter = getAdapter(movies, R.layout.list_item_movie);
        setUpRv(moviesAdapter, rvMovies);

        MoviesAdapter newReleaseAdapter = getAdapter(movies, R.layout.list_item_movie_categories);
        setUpRv(newReleaseAdapter, rvNewReleaseMovies);

        MoviesAdapter bestRatedAdapter = getAdapter(movies, R.layout.list_item_movie_categories);
        setUpRv(bestRatedAdapter, rvBestRatedMovies);
    }

    private void setUpRv(MoviesAdapter moviesAdapter, RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(moviesAdapter);
    }

    private MoviesAdapter getAdapter(List<MoviesRoomModel> movies, @LayoutRes int layout){
        return new MoviesAdapter(getActivity(), movies, layout);
    }

}