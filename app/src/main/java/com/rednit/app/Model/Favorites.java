package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Favorites extends BaseModel {

//    twitterId: { type: Number, index: true },
//    text: String,
//    createdAt: Date

//    @Column
//    @PrimaryKey
    private Integer twitterId;
//    @Column
    private String text;
//    @Column
    private Date createdAt;

    public Favorites(){}

    public Favorites(JSONObject jsonObject) throws JSONException, ParseException {
        setTwitterId(jsonObject.getInt("twitterId"));
        setText(jsonObject.getString("text"));

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = (Date) format.parse(jsonObject.getString("createdAt"));

        setCreatedAt(date);
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
