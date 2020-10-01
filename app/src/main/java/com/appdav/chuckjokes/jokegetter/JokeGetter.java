package com.appdav.chuckjokes.jokegetter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;

/** Singleton Joke grabber, providing interface to grab a random joke with API and pushing it back to calling code with Callback
 *
 */
public class JokeGetter {

    private JokesApi api;

    private static JokeGetter instance = new JokeGetter();

    private JokeGetter() {
        Retrofit retrofit = RetrofitProvider.getRetrofitInstance();
        api = retrofit.create(JokesApi.class);
    }

    public static JokeGetter getInstance() {
        return instance;
    }

    public void getRandomJoke(int quantity, Callback<JokesList> callback) {
        Call<JokesList> call = api.getRandomJoke(quantity);
        call.enqueue(callback);
    }

    /** Static retrofit provider, which incapsulates retrofit and gson creation
     *
     */
    private static class RetrofitProvider {

        static Retrofit getRetrofitInstance() {
            return new Retrofit.Builder()
                    .baseUrl("https://api.icndb.com/jokes/")
                    .addConverterFactory(createConverterFactory())
                    .build();
        }

        private static Factory createConverterFactory() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Joke.class, new Deserializers.JokeDeserializer())
                    .registerTypeAdapter(JokesList.class, new Deserializers.JokesListDeserializer())
                    .create();
            return GsonConverterFactory.create(gson);
        }
    }

}
