package com.rednit.app.Controller;

import android.hardware.camera2.params.Face;
import android.os.Bundle;
import com.facebook.*;
import com.rednit.app.Model.*;
import com.rednit.app.Util.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;


public class FacebookController {
    private String likedPages;

    //Yuri's server
    //String urlPostLikes = "http://54.207.112.184:3000/api/likes/";
    //String urlGetAccount = "http://54.207.112.184:3000/api/accounts/";
    //String urlPostAccount = "http://54.207.112.184:3000/api/accounts/";

    //Use for localhost
    //String urlPostLikes = "http://10.0.1.61:3000/api/likes/";
    //String urlGetAccount = "http://10.0.1.61:3000/api/accounts/";
    //String urlPostAccount = "http://10.0.1.61:3000/api/accounts/";

    //Pablo's server
    String urlPostLikes = "http://54.88.31.160:3000/api/likes/";
    String urlGetAccount = "http://54.88.31.160:3000/api/accounts/";
    String urlPostAccount = "http://54.88.31.160:3000/api/accounts/";


    ArrayList<Likes> likesList = new ArrayList<Likes>();
    ArrayList<LikesSimple> likesSimpleList = new ArrayList<LikesSimple>();
    Account account = new Account();
    MyLocation gps;

    public void listLikes(final String profile, String after) {
        Bundle params = new Bundle();
        params.putString("after", after);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + profile + "/likes",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse) {
                        JSONObject jsonObject = graphResponse.getJSONObject();
                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            setLikedPages(jsonArray.toString());
                            if (!jsonObject.isNull("paging")) {
                                JSONObject paging = jsonObject.getJSONObject("paging");
                                JSONObject aux;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Likes likes = new Likes();
                                    likes.set_id(jsonArray.getJSONObject(i).getString("id"));
                                    likes.setName(jsonArray.getJSONObject(i).getString("name"));
                                    likesList.add(likes);
                                }

                                JSONObject cursors = paging.getJSONObject("cursors");
                                if (!cursors.isNull("after"))
                                    listLikes(profile, cursors.getString("after"));
                                else {
                                    System.out.println(getLikedPages());
                                    return;
                                }
                            } else {
                                System.out.println(getLikedPages());
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
        ).executeAndWait();

    }

        public void postLikes(){
            JSONParser jp = new JSONParser();
        for(int i = 0 ; i < likesList.size() ; i++){
            try {
                jp.postToServer(urlPostLikes, likesList.get(i).toJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public void setupAccount(Profile profile, double[] location) throws JSONException, ParseException {
        account.setName(profile.getName());
        account.setPhotoUrl(profile.getProfilePictureUri(20,20).toString());
        account.setLoc(location);
        FacebookAccount fba = new FacebookAccount();
        fba.setFacebookId(profile.getId());
        simplifyLikes();
        fba.setLikes(likesSimpleList);
        Accounts accounts = new Accounts();
        accounts.setFacebookAccount(fba);
        account.setAccounts(accounts);
    }

    public void postAccount(){
        JSONParser jp = new JSONParser();

        try {
            jp.POST(urlPostAccount, account.toJSON());
            System.out.println("POST: json: "+account.toJSON().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void simplifyLikes(){
        for(int i = 0 ; i < likesList.size() ; i++){
            LikesSimple ls = new LikesSimple();
            ls.set_id(likesList.get(i).get_id());
            likesSimpleList.add(ls);
        }

    }



    private JSONArray attributes = new JSONArray();

    public JSONArray getAttributes() {
        return attributes;
    }

    public void setLikedPages(String t) {
        this.likedPages += t;
    }

    public String getLikedPages() {
        return this.likedPages;
    }


}
