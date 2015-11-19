package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.Table;
import com.rednit.app.DAO.RednitDatabase;

/**
 * Created by pablohenrique on 11/19/15.
 */
@Table(databaseName = RednitDatabase.NAME)
@ModelContainer
public class Page {

//    facebookId: { type: String, index: true },
//    name: { type: String, index: true },
//    about: String

    @Column
    private String facebookId;
    @Column
    private String name;
    @Column
    private String about;

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
}
