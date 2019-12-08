package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumi.imanagehotel.Hotel;

import java.util.ArrayList;

public class HotelDatabaseSource {

    DatabaseHelper helper;
    SQLiteDatabase db;

    public HotelDatabaseSource(Context context) {
        this.helper = new DatabaseHelper(context);
    }
    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }


    public boolean insertHotel(Hotel hotel){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_HOTEL_NAME , hotel.getName());
        values.put(DatabaseHelper.COL_HOTEL_LOCATION , hotel.getLocation());
        values.put(DatabaseHelper.COL_HOTEL_EMAIL , hotel.getEmail());
        values.put(DatabaseHelper.COL_HOTEL_PHONE_NUMBER , hotel.getNumber());
        long rowInsert = db.insert(DatabaseHelper.Hotel_INFO_TABLE_NAME,null,values);
        this.close();
        if(rowInsert>0)return true;
        return false;
    }
    public int getNumberofHotel(){
        this.open();
        Cursor cursor = db.rawQuery("SELECT count(*) as TOTAL FROM " + DatabaseHelper.Hotel_INFO_TABLE_NAME + " ;" , null);
        cursor.moveToFirst();
        int rows = cursor.getInt(cursor.getColumnIndex("TOTAL"));
        this.close();
        return rows;
    }

    public boolean updateHotel(Hotel hotel , int id){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_HOTEL_NAME , hotel.getName());
        values.put(DatabaseHelper.COL_HOTEL_LOCATION , hotel.getLocation());
        values.put(DatabaseHelper.COL_HOTEL_EMAIL , hotel.getEmail());
        values.put(DatabaseHelper.COL_HOTEL_PHONE_NUMBER , hotel.getNumber());
        long rowInsert = db.update(DatabaseHelper.Hotel_INFO_TABLE_NAME,values,DatabaseHelper.COL_HOTEL_ID + " = " + id + " ;" ,null);
        this.close();
        if(rowInsert>0)return true;
        return false;
    }

    public ArrayList<Hotel> getHotel(){
        this.open();
        ArrayList <Hotel> hotels = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.Hotel_INFO_TABLE_NAME  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{

                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_HOTEL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOTEL_NAME));
                String location = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOTEL_LOCATION));
                String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOTEL_EMAIL));
                String number = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_HOTEL_PHONE_NUMBER));

                hotels.add(new Hotel(id,name,location,email,number));

            }while(cursor.moveToNext());
        }
        this.close();
        return hotels;
    }
}
