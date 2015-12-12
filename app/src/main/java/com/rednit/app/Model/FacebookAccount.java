package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class FacebookAccount extends BaseModel {



    private String _id = "";
    private String facebookId;
    private ArrayList<Likes> likes;
    private ArrayList<Account> friends;
    private JSONObject jsonObject;

//    private String _idAttr = "_id";
    private String facebookAttr = "facebookId";
    private String likesAttr = "likes";
    private String friendsAttr = "friends";


    public FacebookAccount(){}

    public FacebookAccount(JSONObject jsonObject) throws JSONException, ParseException {
        setFacebookId(jsonObject.getString(facebookAttr));
        setLikes(jsonObject.getJSONArray(likesAttr));
        setLikes(jsonObject.getJSONArray(friendsAttr));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            getJsonObject().put(facebookAttr, getFacebookId());
            getJsonObject().put(likesAttr, getLikesJSONArray());
            getJsonObject().put(friendsAttr, getFriendsJSONArray());
        }
        return getJsonObject();
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }


    public ArrayList<Likes> getLikes() {
        return likes;
    }

    public JSONArray getLikesJSONArray() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Likes likes : getLikes()){
            jsonArray.put(likes.toJSON());
        }
        return jsonArray;
    }

    public void setLikes(ArrayList<Likes> likes) {
        this.likes = likes;
    }

    public void setLikes(JSONArray likes) throws JSONException, ParseException {
        ArrayList<Likes> myLikes = new ArrayList<>();
        for(int i = 0; i < likes.length(); i++){
            myLikes.add(new Likes(likes.getJSONObject(i)));
        }
        setLikes(myLikes);
    }


    public ArrayList<Account> getFriends() {
        if(friends == null){
            friends = new ArrayList<Account>();
        }
        return friends;
    }

    public JSONArray getFriendsJSONArray() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for(Account account : getFriends()){
            jsonArray.put(account.toJSON());
        }
        return jsonArray;
    }

    public void setFriends(ArrayList<Account> friends) {
        this.friends = friends;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }


}
