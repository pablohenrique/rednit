package com.rednit.app.Model;

/**
 * Created by pablohenrique on 12/16/15.
 */
public class RednitUserSingleton {

    public static RednitUserSingleton intance = new RednitUserSingleton();
    private String name = "Isea Weaver";
    private String photoUrl = "https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/12227650_10205587203534851_6504001002331463838_n.jpg?oh=25879438ca96cb696538117703ad719a&oe=56EFDA46&__gda__=1458285392_15704eb827c39039edcecd7f0821ac94";

    private RednitUserSingleton(){}

    public static RednitUserSingleton getInstance(){
        return intance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
