package com.appdav.chuckjokes.ui.jokes;

import com.appdav.chuckjokes.jokegetter.Joke;

import java.util.List;

/** This interface provides interaction between presenter and fragment
 * Current implementation is not inherited by anything so it consists only of this pair of interfaces,
 * due to KISS principle
 */
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
