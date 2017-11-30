package com.beaconyx.career.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-11-29.
 */

public class NoticeModel {
    private String title;
    private String time;
    private String content;
    private boolean checkSign;

    private ArrayList<NoticeModel> noticeModels = new ArrayList<>();

    public NoticeModel() {
    }

//    public NoticeModel(Parcel in) {
//        title = in.readString();
//        time = in.readString();
//        content = in.readString();
//        checkSign = in.readByte() != 0;
//    }

//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(title);
//        parcel.writeString(time);
//        parcel.writeString(content);
//        parcel.writeByte((byte) (checkSign ? 1 : 0));
//    }
//
//    public static final Creator<NoticeModel> CREATOR = new Creator<NoticeModel>() {
//        @Override
//        public NoticeModel createFromParcel(Parcel in) {
//            return new NoticeModel(in);
//        }
//
//        @Override
//        public NoticeModel[] newArray(int size) {
//            return new NoticeModel[size];
//        }
//    };

    public boolean getCheckSign() {
        return checkSign;
    }

    public void setCheckSign(boolean checkSign) {
        this.checkSign = checkSign;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

//    @Override
//    public int describeContents() {
//        return this.hashCode();
//    }

}
