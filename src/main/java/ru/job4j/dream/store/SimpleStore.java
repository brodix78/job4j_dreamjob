package ru.job4j.dream.store;

import org.json.JSONObject;

public class SimpleStore {

    private static JSONObject json;

    public static JSONObject getJson() {
        return json;
    }

    public static void setJson(JSONObject js) {
        json = js;
    }
}
