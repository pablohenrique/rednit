package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;
import com.rednit.app.DAO.RednitDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by pablohenrique on 11/19/15.
 */
//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Likes extends BaseModel {

//    facebookId: { type: String, index: true },
//            page: {
//                type: mongoose.Schema.Types.ObjectId,
//                        ref: 'Pages',
//                        index: true
//            },
//            instant: Date

//    @Column
//    @PrimaryKey
    private String facebookId;
//    @Column
//    @ForeignKey(
//            references = {@ForeignKeyReference(columnName = "page",
//                    columnType = String.class,
//                    foreignColumnName = "facebookId")},
//            saveForeignKeyModel = false)
    private Page page;
//    @Column
    private Date instant;

//    @ForeignKey(references = {@ForeignKeyReference(columnName = "page", columnType = Long.class, foreignColumnName = "facebookId")}, onDelete = ForeignKeyAction.CASCADE, saveForeignKeyModel = false)
//    private ForeignKeyContainer<Page> pageContainer;

    public Likes(){}

    public Likes(JSONObject jsonObject) throws JSONException, ParseException {
        setFacebookId(jsonObject.getString("facebookId"));
        setPage(new Page(jsonObject.getJSONObject("page")));

        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = (Date) format.parse(jsonObject.getString("instant"));

        setInstant(date);
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }
}
