package com.krys.codelibrary.ui.arcitecture;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krys.codelibrary.R;
import com.krys.codelibrary.customviews.BoldButton;
import com.krys.codelibrary.utils.CommonUtils;
import com.krys.codelibrary.utils.NotificationUtils;

public class ArchitecturesFragment extends Fragment {

    private BoldButton btnSimpleMVVM;
    private BoldButton btnSimpleMVP;
    private BoldButton btnSimpleMVC;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_architectures, container, false);
        findViewById(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListners();
    }

    private void findViewById(View root) {
        btnSimpleMVVM = root.findViewById(R.id.btnSimpleMVVM);
        btnSimpleMVP = root.findViewById(R.id.btnSimpleMVP);
        btnSimpleMVC = root.findViewById(R.id.btnSimpleMVC);
    }

    private void setClickListners() {

        btnSimpleMVVM.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.fragment_mvvm);
        });

        btnSimpleMVP.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.fragment_mvp);
        });

        btnSimpleMVC.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(R.id.fragment_mvc);
        });
    }
}