package com.appdav.chuckjokes.jokegetter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JokesApi {

    @GET("random/{number}")
    Call<JokesList> getRandomJoke(@Path("number") int quantity);

}
