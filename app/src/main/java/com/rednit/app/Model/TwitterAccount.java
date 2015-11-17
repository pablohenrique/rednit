package com.rednit.app.Model;

/**
 * Created by leonardopimentelferreira on 11/16/15.
 */
public class TwitterAccount {
    private Long twitterId;
    private Tweet favorites[];
    private Long following[];

    public Tweet[] getFavorites() {
        return favorites;
    }

    public void setFavorites(Tweet[] favorites) {
        this.favorites = favorites;
    }

    public Long[] getFollowing() {
        return following;
    }

    public void setFollowing(Long[] following) {
        this.following = following;
    }

    public Long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Long twitterId) {
        this.twitterId = twitterId;
    }
}
