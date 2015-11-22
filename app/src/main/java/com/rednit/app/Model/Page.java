package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Page extends BaseModel {

//    facebookId: { type: String, index: true },
//    name: { type: String, index: true },
//    about: String

//    @Column
//    @PrimaryKey
    String facebookId;
//    @Column
    private String name;
//    @Column
    private String about;
    private JSONObject jsonObject;

    private String facebookAttr = "facebookId";
    private String nameAttr = "name";
    private String aboutAttr = "about";

    public Page(){}

    public Page(JSONObject jsonObject) throws JSONException {
        setFacebookId(jsonObject.getString(facebookAttr));
        setName(jsonObject.getString(nameAttr));
        setAbout(jsonObject.getString(aboutAttr));
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            getJsonObject().put(facebookAttr, getFacebookId());
            getJsonObject().put(nameAttr, getName());
            getJsonObject().put(aboutAttr, getAbout());
        }
        return getJsonObject();
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
