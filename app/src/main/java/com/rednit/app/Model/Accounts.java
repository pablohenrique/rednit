package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablohenrique on 11/19/15.
 */
@Table(databaseName = RednitDatabase.NAME)
@ModelContainer
public class Accounts {

//    name: String,
//    loc: { type: [Number], index: '2d' },
//    email: { type: String, index: true },
//    photoUrl: String,
//    accounts: {
//        facebookAccount: {
//            facebookId: { type: String, index: true },
//            likes: [
//            {
//                facebookId: { type: String, index: true },
//                page: {
//                    type: mongoose.Schema.Types.ObjectId,
//                            ref: 'Pages',
//                            index: true
//                },
//                instant: Date
//            }
//            ],
//            friends : {
//                type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Accounts'}],
//                index: true
//            }
//        },
//        twitterAccount: {
//            twitterId: { type: Number, index: true },
//            favorites: {
//                type: [{ type: mongoose.Schema.Types.ObjectId, ref: 'Tweets' }],
//                index: true
//            },
//            following: {
//                type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Accounts'}],
//                index: true
//            }
//        }
//    }

    @Column
    private String name;
    @Column
    private List<Location> loc;
    @Column
    private String email;
    @Column
    private String photoUrl;
    @Column
    private Accounts accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLoc() {
        return loc;
    }

    public void setLoc(List<Location> loc) {
        this.loc = loc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }
}
