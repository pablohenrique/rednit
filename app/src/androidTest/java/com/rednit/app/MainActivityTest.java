package com.rednit.app;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rednit.app.Controller.MyLocation;
import com.rednit.app.Model.FiwareContextJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pablohenrique on 9/17/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mFirstTestActivity;
    private CallbackManager callbackManager;
    private List<String> permissions = Arrays.asList("public_profile", "email", "user_likes");

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFirstTestActivity = getActivity();
        FacebookSdk.sdkInitialize(mFirstTestActivity);
        callbackManager = CallbackManager.Factory.create();
    }

    public void testFacebookSetup(){
        LoginButton loginButton = (LoginButton) mFirstTestActivity.findViewById(R.id.main_btn_facebook);
        AccessToken.refreshCurrentAccessTokenAsync();

        if(AccessToken.getCurrentAccessToken() == null) {
            loginButton.setReadPermissions(permissions);
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    System.out.println("Facebook Success");
                    assertTrue(true);
                    facebookProfile();
                }

                @Override
                public void onCancel() {
                    System.out.println("Facebook Cancel");
                    AccessToken.refreshCurrentAccessTokenAsync();
                    assertTrue(false);
                }

                @Override
                public void onError(FacebookException exception) {
                    System.out.println("Facebook Error");
                    assertTrue(false);
                }
            });
        }
    }

    public void facebookProfile(){
        Profile profile = Profile.getCurrentProfile();
        assertNotNull(profile);
        facebookDataExtraction();
    }

    public void facebookDataExtraction(){
        String after = "";
        String profile = Profile.getCurrentProfile().getId();
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

                            assertNotNull(jsonObject);
                            assertNotNull(jsonArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
        ).executeAndWait();
    }

}
