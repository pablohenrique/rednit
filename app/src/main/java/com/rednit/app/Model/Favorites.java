package com.rednit.app.Model;

import com.raizlabs.android.dbflow.structure.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Favorites extends BaseModel {

//    twitterId: { type: Number, index: true },
//    text: String,
//    createdAt: Date

//    @Column
//    @PrimaryKey
    private String _id = "";
    private Integer twitterId;
//    @Column
    private String text;
//    @Column
    private java.util.Date createdAt;
    private JSONObject jsonObject;

    private String _idAttr = "_id";
    private String twitterAttr = "twitterId";
    private String textAttr = "text";
    private String createdAtAttr = "createdAt";

    public Favorites(){}

    public Favorites(JSONObject jsonObject) throws JSONException, ParseException {
        if(!jsonObject.isNull(_idAttr)) {
            set_id(jsonObject.getString(_idAttr));
        }
        setTwitterId(jsonObject.getInt(twitterAttr));
        setText(jsonObject.getString(textAttr));


        final String TWITTER="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(TWITTER);
        sf.setLenient(true);
        java.util.Date date = sf.parse(jsonObject.getString(createdAtAttr));
        setCreatedAt(date);
    }

    public JSONObject toJSON() throws JSONException {
        if(getJsonObject() == null) {
            setJsonObject(new JSONObject());
            if(!get_id().equals("")){
                getJsonObject().put(_idAttr,get_id());
            }
            getJsonObject().put(twitterAttr, getTwitterId());
            getJsonObject().put(textAttr, getText());
            getJsonObject().put(createdAtAttr, getCreatedAt().toString());
        }
        return getJsonObject();
    }

    public Integer getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(Integer twitterId) {
        this.twitterId = twitterId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
