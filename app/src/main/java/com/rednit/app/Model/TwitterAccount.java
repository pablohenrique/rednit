package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablohenrique on 11/19/15.
 */
//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class TwitterAccount extends BaseModel {

//    twitterId: { type: Number, index: true },
//    favorites: {
//        type: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Tweets' }],
//        index: true
//    },
//    following: {
//        type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Account'}],
//        index: true
//    }

//    @Column
//    @PrimaryKey
    private Integer twitterId;
//    @Column
    private List<Favorites> favorites;
//    @Column
    private List<Account> following;

    public TwitterAccount(){}

    public TwitterAccount(JSONObject jsonObject) throws JSONException, ParseException {
        setTwitterId(jsonObject.getInt("twitterId"));
        setFavorites(jsonObject.getJSONArray("favorites"));
        setFollowing(jsonObject.getJSONArray("following"));
    }

    public Integer getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Integer twitterId) {
        this.twitterId = twitterId;
    }

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorites> favorites) {
        this.favorites = favorites;
    }

    public void setFavorites(JSONArray jsonArray) throws JSONException, ParseException {
        ArrayList<Favorites> favorites = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            favorites.add( new Favorites(jsonArray.getJSONObject(i)) );
        }
        setFavorites(favorites);
    }

    public List<Account> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<Account> following) {
        this.following = following;
    }

    public void setFollowing(JSONArray jsonArray) throws JSONException, ParseException {
        ArrayList<Account> following = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            favorites.add( new Favorites(jsonArray.getJSONObject(i)) );
        }
        setFollowing(following);
    }
}
