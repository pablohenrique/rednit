package com.rednit.app.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yuricampos on 12/16/15.
 */
public class SharedPrefs {

    //id = jsonObj.get("_id").toString();
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";

    public SharedPrefs(){

    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getId(Context context) {
        return getPrefs(context).getString("username_key", "");
    }

    public static void setId(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("username_key", input);
        editor.commit();
    }

}
