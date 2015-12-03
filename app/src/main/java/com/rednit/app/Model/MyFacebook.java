package com.rednit.app.Model;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.Iterator;

/**
 * Created by pablohenrique on 9/17/15.
 */
public class MyFacebook {

    private String likedPages;

    public void extractLikes(final String profile, String after){
        Bundle params = new Bundle();
        params.putString("after", after);
//        params.putString("limit", "1000");
//        params.putInt("limit", 1000);
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
                            if(!jsonObject.isNull("paging")) {
                                JSONObject paging = jsonObject.getJSONObject("paging");

//                        putDataToServer(paging);

                                putDataToServer(new FiwareContextJson(profile).extractPages(jsonArray).toJSON());

                                JSONObject cursors = paging.getJSONObject("cursors");
                                if (!cursors.isNull("after"))
                                    extractLikes(profile, cursors.getString("after"));
                                    //                                    afterString[0] = cursors.getString("after");
                                else {
                                    System.out.println(getLikedPages());
                                    return;
                                }
                                //                                    noData[0] = true;
                            }
                            else {
                                System.out.println(getLikedPages());
                                return;
                            }
                            //                                noData[0] = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
        ).executeAndWait();
    }

    public String putDataToServer(JSONObject returnedJObject) throws Throwable {
        System.out.println("|");
        System.out.println(returnedJObject.toString());
        System.out.println("|");
        String url = "http://45.55.148.217:1026/ngsi10/updateContext";
        HttpPost request = new HttpPost(url);
        JSONStringer json = new JSONStringer();
        StringBuilder sb=new StringBuilder();


        if (returnedJObject!=null)
        {
            Iterator<String> itKeys = returnedJObject.keys();
            if(itKeys.hasNext())
                json.object();
            while (itKeys.hasNext())
            {
                String k=itKeys.next();
                json.key(k).value(returnedJObject.get(k));
                Log.e("keys " + k, "value " + returnedJObject.get(k).toString());
            }
        }
        json.endObject();


        StringEntity entity = new StringEntity(json.toString());
        entity.setContentType("application/json;charset=UTF-8");
        entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
        request.setHeader("Accept", "application/json");
        request.setEntity(entity);

        HttpResponse response =null;
        DefaultHttpClient httpClient = new DefaultHttpClient();

//        HttpConnectionParams.setSoTimeout(httpClient.getParams(), Constants.ANDROID_CONNECTION_TIMEOUT * 1000);
//        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),Constants.ANDROID_CONNECTION_TIMEOUT*1000);
        try{

            response = httpClient.execute(request);
        }
        catch(SocketException se)
        {
            Log.e("SocketException", se+"");
            throw se;
        }




        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while((line = reader.readLine()) != null){
            sb.append(line);

        }

        return sb.toString();
    }

    public void setLikedPages(String t){
        this.likedPages += t;
    }

    public String getLikedPages(){
        return this.likedPages;
    }

}
