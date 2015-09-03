package com.rednit.app.DAO;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by pablohenrique on 9/3/15.
 */
@Database(name = RednitDatabase.NAME, version = RednitDatabase.VERSION)
public class RednitDatabase {
    public final static String NAME = "rednit_database";
    public final static int VERSION = 1;
}
