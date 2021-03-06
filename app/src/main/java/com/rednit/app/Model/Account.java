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


    private String _id;
    private String name;
    //private String email;
    private String photoUrl;
    private double[] loc;
    private Accounts accounts;
    private JSONObject jsonObject;

    private String _idAttr = "_id";
    private String nameAttr = "name";
    //private String emailAttr = "email";
    private String photoUrlAttr = "photoUrl";
    private String locAttr = "loc";
    private String accountsAttr = "accounts";

    public Account(){}

    public Account(JSONObject jsonObject) throws JSONException, ParseException {
        set_id(jsonObject.getString(_idAttr));
        setName(jsonObject.getString(nameAttr));
        //setEmail(jsonObject.getString(emailAttr));
        setPhotoUrl(jsonObject.getString(photoUrlAttr));
        JSONArray jsonArray = jsonObject.getJSONArray(locAttr);
        double[] loc = {jsonArray.getDouble(0), jsonArray.getDouble(1)};
        setLoc(loc);
        setAccounts(new Accounts(jsonObject.getJSONObject(accountsAttr)));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            getJsonObject().put(_idAttr, get_id());
            getJsonObject().put(nameAttr, getName());
            //getJsonObject().put(emailAttr, getEmail());
            getJsonObject().put(photoUrlAttr, getPhotoUrl());

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(getLoc()[0]);
            jsonArray.put(getLoc()[1]);
            getJsonObject().put(locAttr, jsonArray);
            getJsonObject().put(accountsAttr, getAccounts().toJSON());
        }
        return getJsonObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public String getEmail() {
     //   return email;
    //}

    //public void setEmail(String email) {
    //    this.email = email;
    //}

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

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
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