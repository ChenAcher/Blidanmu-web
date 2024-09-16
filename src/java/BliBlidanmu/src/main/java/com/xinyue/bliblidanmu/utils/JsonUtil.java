package com.xinyue.bliblidanmu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtil {
    public static ArrayList<String> parseJsonToList(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> listText = gson.fromJson(jsonString, listType);
        return listText;
    }
}
