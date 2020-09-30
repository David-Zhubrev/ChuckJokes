package com.appdav.chuckjokes;

public interface SnackbarInterface {

    void showFailureMessage(Callback callback);

    void showWrongFormatMessage();

    interface Callback {
        void onSnackbarButtonClicked();
    }
}


