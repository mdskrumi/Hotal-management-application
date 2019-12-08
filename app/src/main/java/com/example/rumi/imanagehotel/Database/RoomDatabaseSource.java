package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.rumi.imanagehotel.Rating;
import com.example.rumi.imanagehotel.Room;

import java.util.ArrayList;

public class RoomDatabaseSource {

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private RatingDatabaseHelper ratingTBL;

    public RoomDatabaseSource(Context context) {
        this.helper = new DatabaseHelper(context);
        ratingTBL = new RatingDatabaseHelper(context);
    }

    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }

    public boolean insertRoom(Room room){
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_ROOM_TYPE , room.getRoomType());
        values.put(DatabaseHelper.COL_ROOM_DESCRIPTION , room.getRoomDescription());
        values.put(DatabaseHelper.COL_ROOM_RENT , room.getRoomRent());
        values.put(DatabaseHelper.COL_ROOM_RATING , room.getRating());
        values.put(DatabaseHelper.COL_ROOM_NUMBEROFBED , room.getNumberOfBeds());

        long rowInsert = db.insert(DatabaseHelper.ROOM_TABLE_NAME,null,values);

        db.close();

        if(rowInsert>0)return true;
            return false;
    }

    public boolean updateRoomRating(int roomid , float value){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_ROOM_RATING , value);
        long rowUpdate = db.update(DatabaseHelper.ROOM_TABLE_NAME,values,DatabaseHelper.COL_ROOM_ID + " = " + roomid , null);
        this.close();
        if(rowUpdate>0)return true;
        return false;
    }

    public ArrayList<Room> getAllRooms(){
        this.open();
        ArrayList <Room> rooms = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.ROOM_TABLE_NAME  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                int roomId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_ID));

                this.updateRoomRating(roomId,ratingTBL.getRoomRatingsByRatingTBL(roomId));

                int numberOfBeds = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_NUMBEROFBED));
                int roomRent = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_RENT));
                float rating = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_RATING));
                String roomType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_TYPE));
                String roomDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_ROOM_DESCRIPTION));

                rooms.add(new Room( roomId,  numberOfBeds,  roomRent,  rating,  roomType, roomDescription));

            }while(cursor.moveToNext());
        }
        this.close();
        return rooms;
    }

    public boolean deleteRoomById(int id){
        this.open();
        int deletedrow = db.delete(DatabaseHelper.ROOM_TABLE_NAME,DatabaseHelper.COL_ROOM_ID + "=" + id,null);
        this.close();
        if(deletedrow>0){
            return true;
        }return false;
    }

    public boolean updateRoom(Room room , int id){
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_ROOM_TYPE , room.getRoomType());
        values.put(DatabaseHelper.COL_ROOM_DESCRIPTION , room.getRoomDescription());
        values.put(DatabaseHelper.COL_ROOM_RENT , room.getRoomRent());
        values.put(DatabaseHelper.COL_ROOM_RATING , room.getRating());
        values.put(DatabaseHelper.COL_ROOM_NUMBEROFBED , room.getNumberOfBeds());

        int rowUpdate = db.update(DatabaseHelper.ROOM_TABLE_NAME , values ,DatabaseHelper.COL_ROOM_ID + " = " + id , null);
        this.close();
        if(rowUpdate>0){
            return true;
        }
        return false;
    }

    public int getNumberofRooms(){
        this.open();
        Cursor cursor = db.rawQuery("SELECT count(*) as TOTAL FROM " + DatabaseHelper.ROOM_TABLE_NAME + " ;" , null);
        cursor.moveToFirst();
        int rows = cursor.getInt(cursor.getColumnIndex("TOTAL"));
        this.close();
        return rows;
    }

}
