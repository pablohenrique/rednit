package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.List;

/**
 * Created by pablohenrique on 11/19/15.
 */
//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Account extends BaseModel {

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
//                type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Account'}],
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
//                type: [{type: mongoose.Schema.Types.ObjectId, ref: 'Account'}],
//                index: true
//            }
//        }
//    }

//    @Column
//    @PrimaryKey
    private String _id;
//    @Column
    private String name;
//    @Column
    private String email;
//    @Column
    private String photoUrl;
//    @Column
    double[] loc;

    private Accounts accounts;

    public Account(){}

    public Account(JSONObject jsonObject) throws JSONException, ParseException {
        set_id(jsonObject.getString("_id"));
        setName(jsonObject.getString("name"));
        setEmail(jsonObject.getString("email"));
        setPhotoUrl(jsonObject.getString("photoUrl"));
        JSONArray jsonArray = jsonObject.getJSONArray("loc");
        double[] loc = {jsonArray.getDouble(0), jsonArray.getDouble(1)};
        setLoc(loc);
        setAccounts( new Accounts(jsonObject.getJSONObject("accounts")) );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

//    @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "loc")
    public double[] getLoc() {
//        if(this.loc == null){
//            loc = new Select().from(Location.class).where(Condition.column(Location$Table.ACCOUNTID).eq(this.get_id())).queryList();
//        }
        return this.loc;
    }

    public void setLoc(double[] loc) {
        this.loc = loc;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

//    public double[] getLoc() {
//        return loc;
//    }
//
//    public void setLoc(double[] loc) {
//        this.loc = loc;
//    }

//    public Account getAccounts() {
//        return accounts;
//    }
//
//    public void setAccounts(Account accounts) {
//        this.accounts = accounts;
//    }
}