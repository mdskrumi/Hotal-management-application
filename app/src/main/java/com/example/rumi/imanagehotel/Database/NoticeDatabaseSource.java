package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.rumi.imanagehotel.Notice;

import java.util.ArrayList;

public class NoticeDatabaseSource {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public NoticeDatabaseSource(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }



    public ArrayList<Notice> getAllNotice(){
        this.open();
        ArrayList <Notice> notices = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.NOTICE_TABLE_NAME  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                int noticeId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_ID));
                String noticeText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_TEXT));
                String noticePrivacy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_PRIVACY));
                String noticeDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_DATE));
                notices.add(new Notice( noticeId,  noticeText,  noticePrivacy,  noticeDate));
            }while(cursor.moveToNext());
        }
        this.close();
        return notices;
    }
    public ArrayList<Notice> getAllNoticePublic(){
        this.open();
        ArrayList <Notice> notices = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.NOTICE_TABLE_NAME  + " WHERE " + DatabaseHelper.COL_NOTICE_PRIVACY +
                " = 'Public'  ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                int noticeId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_ID));
                String noticeText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_TEXT));
                String noticePrivacy = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_PRIVACY));
                String noticeDate = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOTICE_DATE));
                notices.add(new Notice( noticeId,  noticeText,  noticePrivacy,  noticeDate));
            }while(cursor.moveToNext());
        }
        this.close();
        return notices;
    }



    public boolean insertNotice(Notice notice){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NOTICE_TEXT,notice.getNoticeText());
        values.put(DatabaseHelper.COL_NOTICE_PRIVACY,notice.getNoticePrivacy());
        values.put(DatabaseHelper.COL_NOTICE_DATE,notice.getNoticeDate());
        long id = db.insert(DatabaseHelper.NOTICE_TABLE_NAME,null,values);
        this.close();
        if(id>0)return true;
        return false;
    }

    public boolean deleteNoticeById(int id){
        this.open();
        int deletedrow = db.delete(DatabaseHelper.NOTICE_TABLE_NAME,DatabaseHelper.COL_NOTICE_ID + "=" + id,null);
        this.close();
        if(deletedrow>0){
            return true;
        }return false;
    }

    public boolean updateNotice(Notice notice , int id){
        this.open();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_NOTICE_TEXT , notice.getNoticeText());
        values.put(DatabaseHelper.COL_NOTICE_DATE , notice.getNoticeDate());
        values.put(DatabaseHelper.COL_NOTICE_PRIVACY , notice.getNoticePrivacy());


        int rowUpdate = db.update(DatabaseHelper.NOTICE_TABLE_NAME , values ,DatabaseHelper.COL_NOTICE_ID + " = " + id , null);
        db.close();
        if(rowUpdate>0){
            return true;
        }
        return false;
    }

}
