package com.krys.codelibrary.ui.exoplayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExoPlayerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExoPlayerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}