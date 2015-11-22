package com.rednit.app.Model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rednit.app.DAO.RednitDatabase;

/**
 * Created by pablohenrique on 11/19/15.
 */
//@Table(databaseName = RednitDatabase.NAME)
//@ModelContainer
public class Location extends BaseModel {

//    @Column
//    @PrimaryKey
    private String accountId;
//    @Column
    private double position;

    public Location(){}

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
