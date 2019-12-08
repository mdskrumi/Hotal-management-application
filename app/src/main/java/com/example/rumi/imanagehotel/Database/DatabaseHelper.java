package com.example.rumi.imanagehotel.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "hotel_DB";
    private static final int DATABASE_VERSION = 1 ;



    // ***** CUSTOMER TABLE
    public static final String CUS_TABLE_NAME = "customer_Table";
    
    public static final String COL_CUS_ID = "cus_id";
    public static final String COL_CUS_FULLNAME = "cus_fullname";
    public static final String COL_CUS_USERNAME = "cus_username";
    public static final String COL_CUS_PASSWORD = "cus_password";
    public static final String COL_CUS_PHONENUMBER = "cus_phonenumber";
    public static final String COL_CUS_EMAIL = "cus_email";

    private static final String CREATE_CUS_TBL =
            "CREATE TABLE " + CUS_TABLE_NAME + " ( " +
                    COL_CUS_ID + " integer primary key, " +
                    COL_CUS_FULLNAME + " text ," +
                    COL_CUS_USERNAME + " text ," +
                    COL_CUS_PASSWORD + " text ," +
                    COL_CUS_PHONENUMBER + " text ," +
                    COL_CUS_EMAIL + " text  ) ; ";

    // ***** EMPLOYEE TABLE
    public static final String EMP_TABLE_NAME = "employee_Table";

    public static final String COL_EMP_ID = "emp_id";
    public static final String COL_EMP_FULLNAME = "emp_fullname";
    public static final String COL_EMP_USERNAME = "emp_username";
    public static final String COL_EMP_PASSWORD = "emp_password";
    public static final String COL_EMP_POSITION = "emp_position";
    public static final String COL_EMP_SALARY = "emp_salary";
    public static final String COL_EMP_AGE = "emp_age";
    public static final String COL_EMP_PHONENUMBER = "emp_phonenumber";
    public static final String COL_EMP_EMAIL = "emp_email";

    private static final String CREATE_EMP_TBL =
            "CREATE TABLE " + EMP_TABLE_NAME + " ( " +
                    COL_EMP_ID + " integer primary key, " +
                    COL_EMP_FULLNAME + " text ," +
                    COL_EMP_USERNAME + " text ," +
                    COL_EMP_PASSWORD + " text ," +
                    COL_EMP_POSITION + " text ," +
                    COL_EMP_SALARY + " integer  ," +
                    COL_EMP_AGE + " integer ," +
                    COL_EMP_PHONENUMBER + " text ," +
                    COL_EMP_EMAIL + " text ) ; ";



    // ***** ROOM TABLE

    public static final String ROOM_TABLE_NAME = "room_Table";

    public static final String COL_ROOM_ID = "room_id";
    public static final String COL_ROOM_TYPE = "room_type";
    public static final String COL_ROOM_DESCRIPTION = "room_description";
    public static final String COL_ROOM_NUMBEROFBED = "room_numberOfBed";
    public static final String COL_ROOM_RENT = "room_rent";
    public static final String COL_ROOM_RATING = "room_rating";


    private static final String CREATE_ROOM_TBL =
            "CREATE TABLE " + ROOM_TABLE_NAME + " ( " +
                    COL_ROOM_ID + " integer primary key, " +
                    COL_ROOM_TYPE + " text ," +
                    COL_ROOM_DESCRIPTION + " text ," +
                    COL_ROOM_NUMBEROFBED + " integer ," +
                    COL_ROOM_RENT + " integer ," +
                    COL_ROOM_RATING + " real  ) ; ";



    // ***** Notice Table

    public static final String NOTICE_TABLE_NAME = "notice_Table";

    public static final String COL_NOTICE_ID = "notice_id";
    public static final String COL_NOTICE_TEXT = "notice_text";
    public static final String COL_NOTICE_PRIVACY = "notice_privacy";
    public static final String COL_NOTICE_DATE= "notice_date";


    private static final String CREATE_NOTICE_TBL =
            "CREATE TABLE " + NOTICE_TABLE_NAME + " ( " +
                    COL_NOTICE_ID + " integer primary key, " +
                    COL_NOTICE_TEXT + " text ," +
                    COL_NOTICE_PRIVACY + " text ," +
                    COL_NOTICE_DATE + " text ) ; ";



    // ***** Ratings Table

    public static final String RATING_TABLE_NAME = "rating_Table";

    public static final String COL_RATING_ID = "rating_id";
    public static final String COL_RATING_CUS_ID = "rating_cus";
    public static final String COL_RATING_ROOM_ID = "rating_room";
    public static final String COL_RATING_RATING= "rating_rating";


    private static final String CREATE_RATING_TBL =
            "CREATE TABLE " + RATING_TABLE_NAME + " ( " +
                    COL_RATING_ID + " integer primary key, " +
                    COL_RATING_CUS_ID + " integer ," +
                    COL_RATING_ROOM_ID + " integer ," +
                    COL_RATING_RATING + " float ) ; ";


    // ***** Comment Table

    public static final String COMMENT_TABLE_NAME = "comment_Table";

    public static final String COL_COMMENT_ID = "comment_id";
    public static final String COL_COMMENT_CUS_ID = "comment_cus";
    public static final String COL_COMMENT_ROOM_ID = "comment_room";
    public static final String COL_COMMENT_COMMENT= "comment_comment";


    private static final String CREATE_COMMENT_TBL =
            "CREATE TABLE " + COMMENT_TABLE_NAME + " ( " +
                    COL_COMMENT_ID + " integer primary key, " +
                    COL_COMMENT_CUS_ID + " integer ," +
                    COL_COMMENT_ROOM_ID + " integer ," +
                    COL_COMMENT_COMMENT + " text ) ; ";



    // ***** Hotel Info Table

    public static final String Hotel_INFO_TABLE_NAME = "hotel_info_Table";

    public static final String COL_HOTEL_ID = "hotel_id";
    public static final String COL_HOTEL_NAME = "hotel_name";
    public static final String COL_HOTEL_LOCATION= "hotel_location";
    public static final String COL_HOTEL_PHONE_NUMBER = "hotel_phonenumber";
    public static final String COL_HOTEL_EMAIL = "hotel_email";



    private static final String CREATE_HOTEL_INFO_TBL =
            "CREATE TABLE " + Hotel_INFO_TABLE_NAME + " ( " +
                    COL_HOTEL_ID + " integer primary key, " +
                    COL_HOTEL_NAME + " text ," +
                    COL_HOTEL_LOCATION + " text ," +
                    COL_HOTEL_PHONE_NUMBER + " text ," +
                    COL_HOTEL_EMAIL + " text ) ; ";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUS_TBL);
        db.execSQL(CREATE_EMP_TBL);
        db.execSQL(CREATE_ROOM_TBL);
        db.execSQL(CREATE_NOTICE_TBL);
        db.execSQL(CREATE_RATING_TBL);
        db.execSQL(CREATE_COMMENT_TBL);
        db.execSQL(CREATE_HOTEL_INFO_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
