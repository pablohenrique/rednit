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
            aux.put("name", "like");
            aux.put("type", "string");
            aux.put("value", array.getJSONObject(i).getString("name"));
            getAttributes().put(aux);
        }
        return this;
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
        jsonObject.put("type", getType());
        jsonObject.put("isPattern", getIsPattern());
        jsonObject.put("id", getId());
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
