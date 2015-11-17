package com.rednit.app.Model;

/**
 * Created by leonardopimentelferreira on 11/16/15.
 */
public class Tweet {
    private String text;
    private String createdAt;
    private String idStr;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
