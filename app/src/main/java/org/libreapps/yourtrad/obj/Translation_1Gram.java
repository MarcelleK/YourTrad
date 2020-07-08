package org.libreapps.yourtrad.obj;

import org.json.JSONObject;

public class Translation_1Gram {

    private final int id;
    private final String word_FR;
    private final String word_EN;
    private final String word_ES;
    private final String word_DE;

    public Translation_1Gram(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.word_FR = jObject.optString("word_FR");
        this.word_EN = jObject.optString("word_EN");
        this.word_ES = jObject.optString("word_ES");
        this.word_DE = jObject.optString("word_DE");
    }

    public int getId() {
        return id;
    }

    public String getWordFR() {
        return word_FR;
    }
    public String getWordEN() {
        return word_EN;
    }
    public String getWordES() {
        return word_ES;
    }
    public String getWordDE() {
        return word_DE;
    }
}
