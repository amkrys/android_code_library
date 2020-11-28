package com.krys.codelibrary.ui.welcome;

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

public class WelcomeFragment extends Fragment {

    private WelcomeViewModel welcomeViewModel;
    private TextView txtHome,txtWelcome;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_welcome, container, false);
        findViewById(root);
        setTextObservers();
        return root;
    }

    private void findViewById(View root) {
        txtHome = root.findViewById(R.id.txtHome);
        txtWelcome = root.findViewById(R.id.txtWelcome);
    }

    private void setTextObservers() {
        welcomeViewModel.getTextWelcome().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                txtWelcome.setText(s);
            }
        });
        welcomeViewModel.getTextHome().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                txtHome.setText(s);
            }
        });
    }


}