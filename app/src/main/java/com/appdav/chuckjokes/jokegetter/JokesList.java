package com.appdav.chuckjokes.jokegetter;

import java.util.ArrayList;
import java.util.List;

public class JokesList {

    private List<Joke> jokes;

    public List<Joke> getJokes() {
        return jokes;
    }

    void add(Joke joke) {
        if (jokes == null) jokes = new ArrayList<>();
        jokes.add(joke);
    }

    boolean isEmpty() {
        return !(jokes != null && jokes.size() > 0);
    }
}
