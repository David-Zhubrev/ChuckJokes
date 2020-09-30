package com.appdav.chuckjokes.ui.jokes;

import com.appdav.chuckjokes.jokegetter.Joke;

import java.util.ArrayList;
import java.util.List;

public interface JokesFragmentInterface {

    interface View {

        void setActive(List<Joke> jokes);

        void setRefreshing();

        void showWrongFormatErrorMessage();

        void showRequestErrorMessage();

    }

    interface Presenter {

        void onButtonClicked(String input);

        void attachView(View v);

        void viewIsReady();

        void detachView();

    }
}
