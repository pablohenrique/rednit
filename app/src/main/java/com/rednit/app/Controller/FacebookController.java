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

    String urlPostLikes = "http://54.88.31.160:3000/api/likes/";
    String urlGetAccount = "http://54.88.31.160:3000/api/accounts/";
    String urlPostAccount = "http://54.88.31.160:3000/api/accounts/";
    ArrayList<Likes> likesList = new ArrayList<Likes>();
    Account account = new Account();

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

    public void setupAccount(Profile profile) throws JSONException, ParseException {
        account.setName(profile.getName());
        account.setPhotoUrl(profile.getProfilePictureUri(20,20).toString());
        FacebookAccount fba = new FacebookAccount();
        fba.setFacebookId(profile.getId());
        fba.setLikes(likesList);
        Accounts accounts = null;
        accounts.setFacebookAccount(fba);
        account.setAccounts(accounts);
    }

    public void postAccount(){
        JSONParser jp = new JSONParser();
        jp.postToServer(urlPostAccount, account.getJsonObject());
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
