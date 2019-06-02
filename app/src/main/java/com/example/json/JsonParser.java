package com.example.json;

import com.example.json.Model.JsonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    // to grab the data
    public static JsonModel getJsonModel(String dataURL, int number) throws JSONException{

        //to access to top level json object
        JSONObject jsonObjectData = new JSONObject(dataURL);

        //to access the json array that contain all the json objects
        //"results" is the array name ..
        JSONArray jsonArrayData = jsonObjectData.getJSONArray("results");

        //to access the objects exists inside the json array 0 is the first record into the array
        JSONObject object = jsonArrayData.getJSONObject(number);

        String type = getString("wrapperType", object);
        String kind = getString("kind", object);
        String artistName = getString("artistName", object);
        String collectionName = getString("collectionName", object);
        String trackName = getString("trackName", object);
        String artistViewURL = getString("artworkUrl100", object);

        return new JsonModel(type, kind, artistName, collectionName, trackName, artistViewURL);

    }


    //to access the specific Json Object
    private  static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws JSONException{

        return jsonObject.getJSONObject(tagName);
    }

    private static String getString (String tagName, JSONObject jsonObject) throws JSONException {

        return jsonObject.getString(tagName);
    }

    private static int getInt (String tagName, JSONObject jsonObject) throws JSONException {

        return jsonObject.getInt(tagName);
    }

    private static boolean getbBolean (String tagName, JSONObject jsonObject) throws JSONException {

        return jsonObject.getBoolean(tagName);
    }

    private static float getFloat (String tagName, JSONObject jsonObject) throws JSONException {

        return (float) jsonObject.getDouble(tagName);
    }
}
