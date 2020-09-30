package com.appdav.chuckjokes;

import com.appdav.chuckjokes.ui.jokes.JokesPresenter;

public class PresenterProvider {

    private static JokesPresenter presenter;

    public static JokesPresenter getJokesPresenterInstance() {
        if (presenter == null) presenter = new JokesPresenter();
        return presenter;
    }
}
