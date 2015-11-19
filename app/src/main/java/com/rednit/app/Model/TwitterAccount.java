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
public class TwitterAccount {

//    twitterId: { type: Number, index: true },
//    favorites: {
//        type: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Tweets' }],
//        index: true
//    },
//    following: {
//        type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Accounts'}],
//        index: true
//    }

    @Column
    private Integer twitterId;
    @Column
    private ArrayList<Favorites> favorites;
    @Column
    private ArrayList<Accounts> following;

}
