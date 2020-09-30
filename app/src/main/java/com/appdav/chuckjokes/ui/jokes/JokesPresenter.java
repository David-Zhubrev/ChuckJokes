package com.appdav.chuckjokes.ui.jokes;

import com.appdav.chuckjokes.jokegetter.Joke;
import com.appdav.chuckjokes.jokegetter.JokeGetter;
import com.appdav.chuckjokes.jokegetter.JokesList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokesPresenter implements JokesFragmentInterface.Presenter {

    private JokesFragmentInterface.View view;

    private JokeGetter jokeGetter;

    private List<Joke> jokes;


    public JokesPresenter() {
        jokeGetter = JokeGetter.getInstance();
        jokes = new ArrayList<>();
    }

    @Override
    public void onButtonClicked(String input) {
        if (view == null) return;
        int quantity;
        try {
            quantity = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            view.showWrongFormatErrorMessage();
            view.setActive(jokes);
            return;
        }
        if (quantity == 0) {
            jokes.clear();
            view.setActive(jokes);
            return;
        }
        view.setRefreshing();
        jokeGetter.getRandomJoke(quantity, new Callback<JokesList>() {
            @Override
            public void onResponse(Call<JokesList> call, Response<JokesList> response) {
                if (response.body() != null) {
                    jokes = response.body().getJokes();
                } else {
                    view.showRequestErrorMessage();
                }
                view.setActive(jokes);
            }

            @Override
            public void onFailure(Call<JokesList> call, Throwable t) {
                view.showRequestErrorMessage();
                view.setActive(jokes);
            }
        });
    }


    @Override
    public void attachView(JokesFragmentInterface.View v) {
        this.view = v;
    }

    @Override
    public void viewIsReady() {
        view.setActive(jokes);
    }

    @Override
    public void detachView() {
        this.view = null;
    }

}
