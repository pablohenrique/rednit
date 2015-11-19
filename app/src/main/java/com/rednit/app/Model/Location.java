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
public class Location {

    @Column
    private double position;

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
