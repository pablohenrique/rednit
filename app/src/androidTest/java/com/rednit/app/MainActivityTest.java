package com.rednit.app;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.rednit.app.Model.FiwareContextJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pablohenrique on 9/17/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mFirstTestActivity;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mFirstTestActivity = getActivity();
    }

    public void testFacebookProfile(){
        Profile profile = Profile.getCurrentProfile();
        assertNotNull(profile);
    }

    public void testFacebookDataExtraction(){
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
