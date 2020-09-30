package com.appdav.chuckjokes.jokegetter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class Deserializers {

    public static class JokesListDeserializer implements JsonDeserializer<JokesList> {

        @Override
        public JokesList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JokesList list = new JokesList();
            JsonObject object = json.getAsJsonObject();
            JsonArray array = object.get("value").getAsJsonArray();
            for (JsonElement entry : array) {
                Joke joke = context.deserialize(entry, Joke.class);
                list.add(joke);
            }
            if (list.isEmpty()) return null;
            else return list;
        }
    }

    public static class JokeDeserializer implements JsonDeserializer<Joke> {

        @Override
        public Joke deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            if (object != null) {
                Joke result = new Joke();
                result.setId(object.get("id").getAsString());
                result.setValue(object.get("joke").getAsString());
                return result;
            } else return null;
        }
    }

}
