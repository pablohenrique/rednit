package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

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

    public Accounts(JSONObject jsonObject) throws JSONException, ParseException {
        setFacebookAccount( new FacebookAccount(jsonObject.getJSONObject("facebookAccount")) );
        setTwitterAccount(new TwitterAccount(jsonObject.getJSONObject("twitterAccount")));
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
}
