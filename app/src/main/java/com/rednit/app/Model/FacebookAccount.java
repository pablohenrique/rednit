package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

import java.util.ArrayList;

/**
 * Created by pablohenrique on 11/19/15.
 */
@Table(databaseName = RednitDatabase.NAME)
@ModelContainer
public class FacebookAccount {

//    facebookAccount: {
//        facebookId: { type: String, index: true },
//        likes: [
//        {
//            facebookId: { type: String, index: true },
//            page: {
//                type: mongoose.Schema.Types.ObjectId,
//                        ref: 'Pages',
//                        index: true
//            },
//            instant: Date
//        }
//        ],
//        friends : {
//            type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Accounts'}],
//            index: true
//        }
//    }

    @Column
    private String facebookId;
    @Column
    private ArrayList<Likes> likes;
    @Column
    private ArrayList<Accounts> friends;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public ArrayList<Likes> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Likes> likes) {
        this.likes = likes;
    }

    public ArrayList<Accounts> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Accounts> friends) {
        this.friends = friends;
    }
}
