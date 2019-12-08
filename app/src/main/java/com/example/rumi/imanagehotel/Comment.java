package com.example.rumi.imanagehotel;

import java.util.ArrayList;

public class Comment {

    private int commentId , roomId , cusId ;
    private String comment;

    static ArrayList<Comment>comments ;

    public Comment(int roomId, int cusId, String comment) {
        this.roomId = roomId;
        this.cusId = cusId;
        this.comment = comment;
    }

    public Comment(int commentId, int roomId, int cusId, String comment) {
        this.commentId = commentId;
        this.roomId = roomId;
        this.cusId = cusId;
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getCusId() {
        return cusId;
    }

    public String getComment() {
        return comment;
    }

    public static ArrayList<Comment> getComments() {
        return comments;
    }

    public static void setComments(ArrayList<Comment> comments) {
        Comment.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", roomId=" + roomId +
                ", cusId=" + cusId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
