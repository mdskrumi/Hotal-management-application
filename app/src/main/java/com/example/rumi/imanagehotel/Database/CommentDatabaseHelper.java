package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumi.imanagehotel.Comment;

import java.util.ArrayList;

public class CommentDatabaseHelper {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public CommentDatabaseHelper(Context context) {
        this.helper = new DatabaseHelper(context);
    }


    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }

    public boolean insertCommnent(Comment comment){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_COMMENT_CUS_ID,comment.getCusId());
        values.put(DatabaseHelper.COL_COMMENT_ROOM_ID,comment.getRoomId());
        values.put(DatabaseHelper.COL_COMMENT_COMMENT,comment.getComment());
        long row = db.insert(DatabaseHelper.COMMENT_TABLE_NAME,null,values);
        this.close();
        if(row > 0){
            return true;
        }
        return false;
    }

    public boolean deleteCommnentByCustomerId(int id){
        this.open();
        long row = db.delete(DatabaseHelper.COMMENT_TABLE_NAME, DatabaseHelper.COL_COMMENT_CUS_ID  + " = "+ id ,null);
        this.close();
        if(row > 0){
            return true;
        }
        return false;
    }
    public boolean deleteCommnentByRoomId(int id){
        this.open();
        long row = db.delete(DatabaseHelper.COMMENT_TABLE_NAME, DatabaseHelper.COL_COMMENT_ROOM_ID  + " = "+ id ,null);
        this.close();
        if(row > 0){
            return true;
        }
        return false;
    }
    public boolean deleteCommnentByCommentId(int id){
        this.open();
        long row = db.delete(DatabaseHelper.COMMENT_TABLE_NAME, DatabaseHelper.COL_COMMENT_ID  + " = "+ id ,null);
        this.close();
        if(row > 0){
            return true;
        }
        return false;
    }

    public ArrayList<Comment> getAllComments(){
        ArrayList<Comment>comments = new ArrayList<>();
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.COMMENT_TABLE_NAME + " ;" , null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                int commentId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ID));
                int roomId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ROOM_ID));
                int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_CUS_ID));
                String comment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_COMMENT));

                comments.add(new Comment( commentId,  roomId,  cusId,  comment));
            }while (cursor.moveToNext());
        }
        this.close();
        return comments;
    }
    public ArrayList<Comment> getCommentsByCusId(int id){
        ArrayList<Comment>comments = new ArrayList<>();
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.COMMENT_TABLE_NAME + " where " +  DatabaseHelper.COL_COMMENT_CUS_ID + " = " + id + " ;" , null);
        if(cursor != null && cursor.getCount() > 0 ) {
            cursor.moveToFirst();
            do {
                int commentId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ID));
                int roomId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ROOM_ID));
                int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_CUS_ID));
                String comment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_COMMENT));

                comments.add(new Comment(commentId, roomId, cusId, comment));
            } while (cursor.moveToNext());
        }
        this.close();
        return comments;
    }
    public ArrayList<Comment> getCommentsByRoomId(int id){
        ArrayList<Comment>comments = new ArrayList<>();
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.COMMENT_TABLE_NAME + " where " +  DatabaseHelper.COL_COMMENT_ROOM_ID + " = " + id + " ;" , null);
            if(cursor != null && cursor.getCount() > 0 ) {
                cursor.moveToFirst();
                do {
                    int commentId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ID));
                    int roomId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_ROOM_ID));
                    int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_CUS_ID));
                    String comment = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_COMMENT_COMMENT));

                    comments.add(new Comment(commentId, roomId, cusId, comment));
                } while (cursor.moveToNext());
            }
        this.close();
        return comments;
    }



}
