package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

import java.sql.Date;

/**
 * Created by pablohenrique on 11/19/15.
 */
@Table(databaseName = RednitDatabase.NAME)
@ModelContainer
public class Likes {

//    facebookId: { type: String, index: true },
//            page: {
//                type: mongoose.Schema.Types.ObjectId,
//                        ref: 'Pages',
//                        index: true
//            },
//            instant: Date
    @Column
    private String facebookId;
    @Column
    private Page page;
    @Column
    private Date instant;

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
