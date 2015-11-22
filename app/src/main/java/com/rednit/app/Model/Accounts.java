package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by pablohenrique on 11/22/15.
 */
//@Table(databaseName = RednitDatabase.NAME)
public class Accounts {

//    @Column
//    @PrimaryKey(autoincrement = true)
//    private int id;
//    @Column
    private FacebookAccount facebookAccount;
//    @Column
    private TwitterAccount twitterAccount;
    private JSONObject jsonObject;

    private String facebookAttr = "facebookAccount";
    private String twitterAttr = "twitterAccount";

    public Accounts(JSONObject jsonObject) throws JSONException, ParseException {
        setFacebookAccount( new FacebookAccount(jsonObject.getJSONObject(facebookAttr)) );
        setTwitterAccount(new TwitterAccount(jsonObject.getJSONObject(twitterAttr)));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            getJsonObject().put(facebookAttr, getFacebookAccount().toJSON());
            getJsonObject().put(twitterAttr, getTwitterAccount().toJSON());
        }
        return getJsonObject();
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public FacebookAccount getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(FacebookAccount facebookAccount) {
        this.facebookAccount = facebookAccount;
    }

    public TwitterAccount getTwitterAccount() {
        return twitterAccount;
    }

    public void setTwitterAccount(TwitterAccount twitterAccount) {
        this.twitterAccount = twitterAccount;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
