package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumi.imanagehotel.Customer;

import java.util.ArrayList;

public class CustomerDatabaseSource {

    private DatabaseHelper helper;
    private  SQLiteDatabase db;

    public CustomerDatabaseSource(Context context) {
        helper = new DatabaseHelper(context);
    }


    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }


    public boolean insertCustomer(Customer cus){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_CUS_FULLNAME,cus.getCusFullName());
        values.put(DatabaseHelper.COL_CUS_USERNAME,cus.getCusUserName());
        values.put(DatabaseHelper.COL_CUS_PASSWORD,cus.getCusPassword());
        values.put(DatabaseHelper.COL_CUS_PHONENUMBER,cus.getCusPhoneNumber());
        values.put(DatabaseHelper.COL_CUS_EMAIL,cus.getCusEmail());
        long id = db.insert(DatabaseHelper.CUS_TABLE_NAME,null,values);
        this.close();
        if(id > 0) return true;
        return false;
    }
    public boolean searchDuplicateUserName(String userName){
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.CUS_TABLE_NAME +" WHERE " + DatabaseHelper.COL_CUS_USERNAME + " = '" + userName  + "' ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            this.close();
            return true;
        }else{
            this.close();
            return false;
        }
    }
    public Customer getCustomerDetailForLogin(String userName){
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.CUS_TABLE_NAME +" WHERE " + DatabaseHelper.COL_CUS_USERNAME + " = '" + userName  + "' ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ) {
            cursor.moveToFirst();
            int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_CUS_ID));
            String cusFullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_FULLNAME));
            String cusUserName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_USERNAME));
            String cusPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PASSWORD));
            String cusPhoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PHONENUMBER));
            String cusEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_EMAIL));
            this.close();
            return new Customer( cusId,  cusFullName,  cusUserName,  cusPassword,  cusPhoneNumber,  cusEmail);
        }
        this.close();
        return null;
    }
    public ArrayList<Customer> getCustomerList(){
        this.open();
        ArrayList <Customer> customers = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.CUS_TABLE_NAME  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_CUS_ID));
                String cusFullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_FULLNAME));
                String cusUserName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_USERNAME));
                String cusPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PASSWORD));
                String cusPhoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PHONENUMBER));
                String cusEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_EMAIL));
                
                customers.add(new Customer( cusId,  cusFullName,  cusUserName,  cusPassword,  cusPhoneNumber,  cusEmail));

            }while(cursor.moveToNext());
        }
        this.close();
        return customers;
    }
    public boolean deleteCustomer(int id){
        this.open();
        int deletedrow = db.delete(DatabaseHelper.CUS_TABLE_NAME,DatabaseHelper.COL_CUS_ID + "=" + id,null);
        this.close();

        if(deletedrow>0){
            return true;
        }return false;
    }
    public boolean updateCustomerByID(Customer cus , int id){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_CUS_FULLNAME,cus.getCusFullName());
        values.put(DatabaseHelper.COL_CUS_USERNAME,cus.getCusUserName());
        values.put(DatabaseHelper.COL_CUS_PASSWORD,cus.getCusPassword());
        values.put(DatabaseHelper.COL_CUS_PHONENUMBER,cus.getCusPhoneNumber());
        values.put(DatabaseHelper.COL_CUS_EMAIL,cus.getCusEmail());
        int rowUpdate = db.update(DatabaseHelper.CUS_TABLE_NAME , values ,DatabaseHelper.COL_CUS_ID + " = " + id , null);
        this.close();
        if(rowUpdate > 0) return true;
        return false;
    }
    public Customer getCustomerDetailById(int id){
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.CUS_TABLE_NAME +" WHERE " + DatabaseHelper.COL_CUS_ID + " = " + id  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ) {
            cursor.moveToFirst();
            int cusId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_CUS_ID));
            String cusFullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_FULLNAME));
            String cusUserName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_USERNAME));
            String cusPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PASSWORD));
            String cusPhoneNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_PHONENUMBER));
            String cusEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CUS_EMAIL));
            this.close();
            return new Customer( cusId,  cusFullName,  cusUserName,  cusPassword,  cusPhoneNumber,  cusEmail);
        }
        this.close();
        return null;
    }



    public int getNumberofCustomers(){
        this.open();
        Cursor cursor = db.rawQuery("SELECT count(*) as TOTAL FROM " + DatabaseHelper.CUS_TABLE_NAME + " ;" , null);
        cursor.moveToFirst();
        int rows = cursor.getInt(cursor.getColumnIndex("TOTAL"));
        db.close();
        return rows;
    }

}
