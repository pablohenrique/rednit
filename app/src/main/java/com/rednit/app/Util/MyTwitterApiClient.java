package com.rednit.app.Util;

/**
 * Created by leonardopimentelferreira on 9/20/15.
 */

import com.google.gson.annotations.SerializedName;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

import retrofit.http.GET;
import retrofit.http.Query;

public class MyTwitterApiClient extends TwitterApiClient {
    public MyTwitterApiClient(Session session) {
        super(session);
    }

    /**
     * Provide FriendsService with ids
     */
    public FriendsService getFriendsService() {
        return getService(FriendsService.class);
    }

    public interface FriendsService {
        @GET("/1.1/friends/ids.json")
        void ids(@Query("user_id") Long userId,
                 @Query("screen_name") String screenName,
                 @Query("cursor") Long cursor,
                 @Query("stringify_ids") Boolean stringifyIds,
                 @Query("count") Integer count,
                 Callback<Ids> cb);

        @GET("/1.1/friends/ids.json")
        void idsByUserId(@Query("user_id") Long userId,
                         Callback<Ids> cb);
    }

    public class Ids {
        @SerializedName("previous_cursor")
        public final Long previousCursor;

        @SerializedName("ids")
        public final Long[] ids;

        @SerializedName("next_cursor")
        public final Long nextCursor;


        public Ids(Long previousCursor, Long[] ids, Long nextCursor) {
            this.previousCursor = previousCursor;
            this.ids = ids;
            this.nextCursor = nextCursor;
        }

        public void printIds(){
            System.out.println("PreviousCursor: "+previousCursor);
            System.out.println("NextCursor: "+nextCursor);
            System.out.println("Ids: ");
            for (Long id : ids) {
                System.out.println(id);
            }


        }
    }
}
