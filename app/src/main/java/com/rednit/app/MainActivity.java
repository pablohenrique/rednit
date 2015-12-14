package com.rednit.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.rednit.app.Controller.FacebookController;
import com.rednit.app.Controller.MyLocation;
import com.rednit.app.Model.Favorites;
import com.rednit.app.Model.MyFacebook;
import com.rednit.app.Model.TwitterAccount;
import com.rednit.app.Util.MyTwitterApiClient;
import com.rednit.app.Util.Util;
import com.rednit.app.View.HomeFragment;
import com.rednit.app.View.ResultListFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.FavoriteService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultListFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    private Util utils;
    private TwitterLoginButton twitterLoginButton;
    private List<String> permissions = Arrays.asList("public_profile", "email", "user_likes");
    private CallbackManager callbackManager;
    private LoginButton loginButton;
   // private MyFacebook myFacebook;
    private FacebookController facebookController;
    //    private AccessTokenTracker accessTokenTracker;
//    private ProfileTracker profileTracker;
    MyLocation gps;

    private String likedPages;
    private static final String TWITTER_KEY = "MEhrB2Z8cdbUP0P97vnrbFjZy";
    private static final String TWITTER_SECRET = "ULgjeTVKOhAmpUh8zA9guMUU243kqTwj095TR2o6cZnNNeYGww";
    private static TwitterAccount twitterAccount = new TwitterAccount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(MainActivity.this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        FlowManager.init(this);

        callbackManager = CallbackManager.Factory.create();
        utils = new Util();
        //myFacebook = new MyFacebook();
        facebookController = new FacebookController();

        //this.twitterSetup();
        //twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        this.facebookSetup();
//        this.callLoginLoadingScreen();

    }

//    private void twitterSetup() {
//        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
//        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
//            @Override
//            public void success(Result<TwitterSession> result) {
//                // Do something with result, which provides a TwitterSession for making API calls
//                callLoginLoadingScreen();
//
//                TwitterSession session = Twitter.getSessionManager().getActiveSession();
//                TwitterAuthToken authToken = session.getAuthToken();
//                String token = authToken.token;
//                String secret = authToken.secret;
//                Log.i("TwitterToken", token);    //211736597-Jkr7pjIVsjzvwT8hEZewTlXT4Sck1HvfvUYfbTXh
//                Log.i("TwitterSecret", secret);  //qWnD6KgqoVrQ0NbuqFZev79bkhTHrjaX5r5g09Zt8Hfbc
//                Log.i("Id", String.valueOf(session.getId()));
//                Log.i("UserId", String.valueOf(session.getUserId()));
//                Log.i("Username", session.getUserName());
//
//
//                final MyTwitterApiClient twitterApiClient = new MyTwitterApiClient(session);
////                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
//                // Can also use Twitter directly: Twitter.getApiClient()
//
//                final String TAG = "Favorite";
//                //Obtem os tweets favoritos
//                FavoriteService favoriteService = twitterApiClient.getFavoriteService();
//                favoriteService.list(session.getUserId(), null, null, null, null, null, new Callback<List<Tweet>>() {
//                    @Override
//                    public void success(final Result<List<Tweet>> result) {
//                        final List<Tweet> l = result.data;
//                        for (int i = 0; i < l.size(); i++) {
//                            Log.i(TAG, "Text:" + l.get(i).text);
//                            Log.i(TAG, "CreatedAt:" + l.get(i).createdAt);
//                            Log.i(TAG, "IdStr:" + l.get(i).idStr);
//
//
//                            JSONObject jsonObject = new JSONObject();
//                            try {
//                                jsonObject.put("twitterId", l.get(i).idStr);
//                                jsonObject.put("createdAt", l.get(i).createdAt);
//                                jsonObject.put("text", l.get(i).text);
//
//                                Favorites f = new Favorites(jsonObject);
//                                twitterAccount.getFavorites().add(f);
//
//                                String url = "http://54.88.31.160:3000/api/tweets";
//
//                                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
//                                        Request.Method.POST, url, jsonObject,
//                                        new Response.Listener<JSONObject>() {
//                                            @Override
//                                            public void onResponse(JSONObject response) {
//                                                Log.i(TAG+"ARC", response.toString());
//                                            }
//                                        }, new Response.ErrorListener() {
//
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        Log.i(TAG, "Error: " + error.toString());
//                                    }
//                                });
//
//                                Volley.newRequestQueue(MainActivity.this).add(jsonObjReq);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void failure(TwitterException e) {
//                        Log.i("Failure2", "");
//                    }
//                });
//
//                //Obtem a lista de amigos que o usuário segue
//                twitterApiClient.getFriendsService().idsByUserId(session.getUserId(), new Callback<MyTwitterApiClient.Ids>() {
//                    @Override
//                    public void success(Result<MyTwitterApiClient.Ids> result) {
//                        if(result.data.ids.length <= 0){
//                            return;
//                        }
//                        ArrayList<Long> following = new ArrayList<Long>();
//                        following.addAll(Arrays.asList(result.data.ids));
//                        twitterAccount.setFollowing(following);
//                    }
//
//                    @Override
//                    public void failure(TwitterException exception) {
//                        //failure
//                        Log.i("Failure3", exception.getMessage());
//
//                    }
//                });
//
//                //Obtem informações do perfil do usuário
//                twitterApiClient.getAccountService().verifyCredentials(true, false, new Callback<User>() {
//                    @Override
//                    public void success(Result<User> userResult) {
//                        Log.i("Result4", "");
////                        User u = userResult.data;
////                        Log.i("Name", u.name);
//                        twitterAccount.setTwitterId(userResult.data.id);
//                    }
//
//                    @Override
//                    public void failure(TwitterException e) {
//                        Log.i("Failure4", e.getMessage());
//                    }
//                });
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                // Do something on failure
//            }
//        });
//    }

    public void postToServer(final String urlParam, final JSONObject jdata) {
        new Thread(new Runnable() {
            public void run() {
                URL url = null;
                try {
                    url = new URL(urlParam);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    System.out.println("Dado: " + jdata.get("name"));

                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(jdata.toString().getBytes());

                    int serverResponseCode = conn.getResponseCode();
                    String serverResponseMessage = conn.getResponseMessage();

                    Log.i("HTTP Response is : ", serverResponseMessage + ": " + serverResponseCode);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //Twitter
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    //Google
    @Override
    public void onConnectionFailed(ConnectionResult result) {
    }

    //Google
    @Override
    public void onConnected(Bundle connectionHint) {
        callLoginLoadingScreen();
    }

    public void onDisconnected() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else {
            if (id == R.id.action_fb_logout) {
                facebookLogOut();
                Fragment fg = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (fg != null) {
                    getSupportFragmentManager().beginTransaction().
                            remove(fg).commit();
                }
            } else {
                if (id == R.id.action_twitter_logout) {
                    twitterLogOut();
                    Fragment fg = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    if (fg != null) {
                        getSupportFragmentManager().beginTransaction().
                                remove(fg).commit();
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void twitterLogOut() {
        //https://twittercommunity.com/t/fabric-logout/29947/6
        Twitter.getInstance();
        Twitter.logOut();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {
    }

    private void facebookSetup() {
        loginButton = (LoginButton) findViewById(R.id.main_btn_facebook);

        if (AccessToken.getCurrentAccessToken() == null) {
            if (!utils.checkConnection(MainActivity.this)) {
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Nao ha conexao", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                loginButton.setReadPermissions(permissions);
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("Facebook Success");
                        callLoginLoadingScreen();
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("Facebook Cancel");
                        AccessToken.refreshCurrentAccessTokenAsync();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("Facebook Error");
                        AccessToken.refreshCurrentAccessTokenAsync();
                    }
                });
            }
        } else {

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                //myFacebook.extractLikes(profile.getId(), "");
                facebookController.listLikes(profile.getId(),"");
                facebookController.postLikes();
                try {
                    facebookController.setupAccount(profile);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                facebookController.postAccount();

            }
//            gps = new MyLocation(MainActivity.this);
//
//            if (gps.canGetLocation()) {
//
//                double latitude = gps.getLatitude();
//                double longitude = gps.getLongitude();
//                try {
//                    myFacebook.putDataToServer(new FiwareContextJson(profile.getId()).locationJson(latitude, longitude));
//                } catch (Throwable throwable) {
//                    throwable.printStackTrace();
//                }
//            } else {
//                gps.showSettingsAlert();
//            }
            callLoginLoadingScreen();
        }
    }

    public static void facebookLogOut() {
        AccessToken.setCurrentAccessToken(null);
        Profile.setCurrentProfile(null);
    }

    public static boolean facebookIsConnected() {
        return (AccessToken.getCurrentAccessToken() != null);
    }

    public static boolean hasSocialConnection() {
        return (facebookIsConnected());
    }

    public static String whitchIsConnected() {
        return facebookIsConnected() ? "facebook" : "twitter";
    }

    private void callLoginLoadingScreen() {
        twitterLoginButton.setVisibility(View.INVISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}