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

//https://twittercommunity.com/t/android-extension-example/27436
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
//    public FavoriteService getFavoriteService() { return getFavoriteService(); }

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
/*
    public interface FavoriteService {
        @GET("/1.1/favorites/list.json")
        void list(@Query("user_id") Long var1,
                  @Query("screen_name") String var2,
                  @Query("count") Integer var3,
                  @Query("since_id") String var4,
                  @Query("max_id") String var5,
                  @Query("include_entities") Boolean var6,
                  Callback<List<Tweet>> var7);
    }
*/
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
