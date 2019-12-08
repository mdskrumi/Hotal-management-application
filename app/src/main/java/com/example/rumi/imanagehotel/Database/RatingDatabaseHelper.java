package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumi.imanagehotel.Rating;

import java.util.ArrayList;

public class RatingDatabaseHelper {

    DatabaseHelper helper;
    SQLiteDatabase db;

    public RatingDatabaseHelper(Context context) {
        this.helper = new DatabaseHelper(context);
    }
    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }

    public boolean insertRating(Rating rating){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_RATING_CUS_ID,rating.getRatingCusId());
        values.put(DatabaseHelper.COL_RATING_ROOM_ID,rating.getRatingRoomId());
        values.put(DatabaseHelper.COL_RATING_RATING,rating.getRatingRating());
        long row = db.insert(DatabaseHelper.RATING_TABLE_NAME,null,values);
        this.close();
        if(row > 0){
            return true;
        }
        return false;
    }

    public void deleteRatingByCustomerId(int id){
        this.open();
        long row = db.delete(DatabaseHelper.RATING_TABLE_NAME, DatabaseHelper.COL_RATING_CUS_ID  + " = "+ id ,null);
        this.close();

    }

    public void deleteRatingByRoomId(int id){
        this.open();
        long row = db.delete(DatabaseHelper.RATING_TABLE_NAME, DatabaseHelper.COL_RATING_ROOM_ID  + " = "+ id ,null);
        this.close();

    }

    public float getRoomRatingsByRatingTBL(int id){
        this.open();
        Cursor cursor = db.rawQuery("SELECT AVG("+DatabaseHelper.COL_RATING_RATING+") AS TOTAL FROM " + DatabaseHelper.RATING_TABLE_NAME + " WHERE " +
               DatabaseHelper.COL_RATING_ROOM_ID + " = " + id + " ;",null);
        cursor.moveToFirst();
        float ratings = cursor.getFloat(cursor.getColumnIndex("TOTAL"));
        db.close();
        return ratings;
    }

    public ArrayList<Rating> getAllRatingsByCusId(int id){
        ArrayList<Rating>ratings = new ArrayList<>();
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.RATING_TABLE_NAME  + " WHERE " +
                DatabaseHelper.COL_RATING_CUS_ID + " = " + id + " ;" , null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                int commentId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_RATING_ID));
                int roomId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_RATING_ROOM_ID));
                int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_RATING_CUS_ID));
                float ratingf = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COL_RATING_RATING));

                ratings.add(new Rating( commentId,  roomId,  cusId,  ratingf));
            }while (cursor.moveToNext());
        }
        this.close();
        return ratings;
    }

    public float getOverAllRating(){
        this.open();
        Cursor cursor = db.rawQuery("SELECT AVG("+DatabaseHelper.COL_RATING_RATING+") AS TOTAL FROM " + DatabaseHelper.RATING_TABLE_NAME  + " ;",null);
        cursor.moveToFirst();
        float ratings = cursor.getFloat(cursor.getColumnIndex("TOTAL"));
        db.close();
        return ratings;
    }

}
