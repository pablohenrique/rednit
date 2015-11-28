package com.rednit.app.Model;

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
    private Long twitterId;
//    @Column
    private List<Favorites> favorites = new ArrayList<Favorites>();
//    @Column
    private List<Long> following;
    private JSONObject jsonObject;

    private String twitterAttr = "twitterId";
    private String favoritesAttr = "favorites";
    private String followingAttr = "following";

    public TwitterAccount(){}

    public TwitterAccount(JSONObject jsonObject) throws JSONException, ParseException {
        setTwitterId(jsonObject.getLong(twitterAttr));
        setFavorites(jsonObject.getJSONArray(favoritesAttr));
        setFollowing(jsonObject.getJSONArray(followingAttr));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            getJsonObject().put(twitterAttr, getTwitterId());
            getJsonObject().put(favoritesAttr, getFavoritesJSONArray());
            getJsonObject().put(followingAttr, getFollowingJSONArray());
        }
        return getJsonObject();
    }

    public Long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public JSONArray getFavoritesJSONArray() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Favorites favorites : getFavorites()){
            jsonArray.put(favorites.toJSON());
        }
        return jsonArray;
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

    public List<Long> getFollowing() {
        return following;
    }

    public JSONArray getFollowingJSONArray() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Long following : getFollowing()){
            jsonArray.put(following);
        }
        return jsonArray;
    }

    public void setFollowing(ArrayList<Long> following) {
        this.following = following;
    }

    public void setFollowing(JSONArray jsonArray) throws JSONException, ParseException {
        ArrayList<Long> following = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            favorites.add( new Favorites(jsonArray.getJSONObject(i)) );
        }
        setFollowing(following);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
