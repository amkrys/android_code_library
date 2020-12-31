package com.krys.codelibrary.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.krys.codelibrary.R;

public class CustomProgressDialog {

    private Context context;
    private android.app.ProgressDialog progressDialog;

    public CustomProgressDialog(Context context) {

        this.context = context;
        progressDialog = android.app.ProgressDialog.show(context, null, null, true);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_progress, null);
        LottieAnimationView lav_main = view.findViewById(R.id.lav_main);
        progressDialog.setContentView(view);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public void show() {
        progressDialog.show();
    }

    public void dismiss() {
        progressDialog.dismiss();
    }

    public void setProgress(int progress) {
        progressDialog.setProgress(progress);
    }

    public boolean isShowing(){
        return progressDialog.isShowing();
    }
}
