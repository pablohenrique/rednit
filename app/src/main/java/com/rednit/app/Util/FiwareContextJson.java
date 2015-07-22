package com.rednit.app.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pablohenrique on 7/20/15.
 */
public class FiwareContextJson {

    private String context = "contextElements";
    private String type = "User";
    private boolean isPattern = false;
    private String id;
    private JSONArray attributes = new JSONArray();

    public FiwareContextJson(String userId){
        setId(userId);
    }

    public FiwareContextJson extractPages(JSONArray array) throws JSONException {
        for(int i = 0; i < array.length(); i++) {
            JSONObject aux = new JSONObject();
            aux.put("value", array.getJSONObject(i).getString("name"));
            aux.put("type", "string");
            aux.put("name", "like");
            getAttributes().put(aux);
        }
        return this;
    }

    public JSONObject locationJson(double lat, double lon) throws JSONException {
        JSONArray array = new JSONArray();
        JSONObject context = new JSONObject();
        JSONArray contextArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("isPattern", getIsPattern());
        jsonObject.put("type", getType());


        contextArray.put(jsonObject);
        jsonObject.put("attributes", getAttributes());
        context.put(getContext(), contextArray);
        context.put("updateAction", "APPEND");
        JSONObject latJson = new JSONObject();
        latJson.put("value", lat);
        latJson.put("type", "Number");
        latJson.put("name", "Latitude");
        array.put(latJson);
        //getAttributes().put(lat);
        JSONObject lonJson = new JSONObject();
        lonJson.put("value", lon);
        lonJson.put("type", "Number");
        lonJson.put("name", "Longitude");
        array.put(lonJson);
        getAttributes().put(array);
        return context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsPattern() {
        return isPattern;
    }

    public void setIsPattern(boolean isPattern) {
        this.isPattern = isPattern;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONArray getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONArray attributes) {
        this.attributes = attributes;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject context = new JSONObject();
        JSONArray contextArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("isPattern", getIsPattern());
        jsonObject.put("type", getType());
        jsonObject.put("attributes", getAttributes());

        contextArray.put(jsonObject);
        context.put(getContext(), contextArray);
        context.put("updateAction", "APPEND");
        return context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
