package com.beaconyx.career.Model;

/**
 * Created by pdg on 2017-11-29.
 */

public class NoticeModel {
    private String title;
    private String time;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
