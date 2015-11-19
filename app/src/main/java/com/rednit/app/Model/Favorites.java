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
public class Favorites {

//    twitterId: { type: Number, index: true },
//    text: String,
//    createdAt: Date

    @Column
    private Integer twitterId;
    @Column
    private String text;
    @Column
    private Date createdAt;

}
