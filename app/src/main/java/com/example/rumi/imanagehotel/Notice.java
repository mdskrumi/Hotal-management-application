package com.example.rumi.imanagehotel;

import java.io.Serializable;
import java.util.ArrayList;

public class Notice implements Serializable {

    private int noticeId;
    private String noticeText , noticePrivacy , noticeDate ;
    public static ArrayList<Notice>notices ;


    public Notice(int noticeId, String noticeText, String noticePrivacy, String noticeDate) {
        this.noticeId = noticeId;
        this.noticeText = noticeText;
        this.noticePrivacy = noticePrivacy;
        this.noticeDate = noticeDate;
    }

    public Notice(String noticeText, String noticePrivacy, String noticeDate) {
        this.noticeText = noticeText;
        this.noticePrivacy = noticePrivacy;
        this.noticeDate = noticeDate;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public String getNoticeText() {
        return noticeText;
    }

    public String getNoticePrivacy() {
        return noticePrivacy;
    }

    public static ArrayList<Notice> getNotices() {
        return notices;
    }

    public static void setNotices(ArrayList<Notice> notices) {
        Notice.notices = notices;
    }
}
