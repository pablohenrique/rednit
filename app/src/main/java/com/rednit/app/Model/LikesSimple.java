package com.rednit.app.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;


public class LikesSimple {

    private String _id = "";
    private JSONObject jsonObject;
    private String _idAttr = "_id";


    public LikesSimple(){}

    public LikesSimple(JSONObject jsonObject) throws JSONException, ParseException {
        set_id(jsonObject.getString(_idAttr));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            if(!get_id().equals("")){
                getJsonObject().put(_idAttr, get_id());
            }
        }
        return getJsonObject();
    }


    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }


}
