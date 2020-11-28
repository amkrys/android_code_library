package com.krys.codelibrary.ui.welcome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WelcomeViewModel extends ViewModel {

    private MutableLiveData<String> mTextHome;
    private MutableLiveData<String> mTextWelcome;
    private int unicode = 0x1F60A;

    public WelcomeViewModel() {
        setVariables();
        setValues();

    }

    private void setVariables() {
        mTextHome = new MutableLiveData<>();
        mTextWelcome = new MutableLiveData<>();
    }

    private void setValues() {
        mTextHome.setValue("Swipe the menu and start exploring "+getEmojiByUnicode(unicode));
        mTextWelcome.setValue("Welcome user ! ");
    }

    public LiveData<String> getTextHome() {
        return mTextHome;
    }

    public LiveData<String> getTextWelcome() {
        return mTextWelcome;
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}