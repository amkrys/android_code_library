package com.krys.codelibrary.ui.exoplayer;

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

import com.krys.codelibrary.R;

public class ExoPlayerFragment extends Fragment {

    private ExoPlayerViewModel exoPlayerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        exoPlayerViewModel =
                new ViewModelProvider(this).get(ExoPlayerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exoplayer, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        exoPlayerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}