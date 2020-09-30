package com.appdav.chuckjokes.jokegetter;

import java.util.regex.Pattern;

public class Joke {

    private String id;
    private String value;

    Joke() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    void setValue(String value) {
        Pattern doubleQuotePattern = Pattern.compile("&quot;");
        String temp = doubleQuotePattern.matcher(value).replaceAll("\"");
        Pattern apostrophePattern = Pattern.compile("\\w+\\?s\\W");
        this.value = apostrophePattern.matcher(temp).replaceAll("'");
    }

    public String getTitle() {
        return "Joke #" + id;
    }
}
