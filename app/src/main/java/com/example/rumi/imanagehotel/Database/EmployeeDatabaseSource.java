package com.example.rumi.imanagehotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rumi.imanagehotel.Employee;

import java.util.ArrayList;

public class EmployeeDatabaseSource {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public EmployeeDatabaseSource(Context context) {
        this.helper = new DatabaseHelper(context);
    }

    private void open(){
        db = helper.getReadableDatabase();
    }
    private void close(){
        db.close();
    }


    public ArrayList<Employee> getAllEmployeeList(){
        this.open();
        ArrayList <Employee> employees = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.EMP_TABLE_NAME  + " ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                int empID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_ID));
                String empFullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_FULLNAME));
                String empUserName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_USERNAME));
                String emppass = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_PASSWORD));
                int empSalary = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_SALARY));
                int empAge =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_AGE)); ;
                String empPosition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_POSITION));
                String empPhone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_PHONENUMBER));
                String empEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_EMAIL));
                employees.add(new Employee( empID,  empFullName,  empUserName,  emppass, empPosition,  empSalary,  empAge,empPhone,empEmail));
            }while(cursor.moveToNext());
        }
        this.close();
        return employees;
    }
    public boolean insertEmployee(Employee emp){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EMP_FULLNAME,emp.getEmpFullName());
        values.put(DatabaseHelper.COL_EMP_USERNAME,emp.getEmpUsername());
        values.put(DatabaseHelper.COL_EMP_PASSWORD,emp.getEmpPassword());
        values.put(DatabaseHelper.COL_EMP_POSITION,emp.getEmpPosition());
        values.put(DatabaseHelper.COL_EMP_SALARY,emp.getEmpSalary());
        values.put(DatabaseHelper.COL_EMP_AGE,emp.getEmpage());
        values.put(DatabaseHelper.COL_EMP_PHONENUMBER,emp.getEmpPhone());
        values.put(DatabaseHelper.COL_EMP_EMAIL,emp.getEmpEmail());

        long id = db.insert(DatabaseHelper.EMP_TABLE_NAME,null,values);
        this.close();
        if(id > 0) return true;
        return false;
    }
    public boolean updateEmployee(Employee emp , int id){
        this.open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_EMP_FULLNAME,emp.getEmpFullName());
        values.put(DatabaseHelper.COL_EMP_USERNAME,emp.getEmpUsername());
        values.put(DatabaseHelper.COL_EMP_PASSWORD,emp.getEmpPassword());
        values.put(DatabaseHelper.COL_EMP_POSITION,emp.getEmpPosition());
        values.put(DatabaseHelper.COL_EMP_SALARY,emp.getEmpSalary());
        values.put(DatabaseHelper.COL_EMP_AGE,emp.getEmpage());
        values.put(DatabaseHelper.COL_EMP_PHONENUMBER,emp.getEmpPhone());
        values.put(DatabaseHelper.COL_EMP_EMAIL,emp.getEmpEmail());
        int rowUpdate = db.update(DatabaseHelper.EMP_TABLE_NAME , values ,DatabaseHelper.COL_EMP_ID + " = " + id , null);
        db.close();
        if(rowUpdate>0){
            return true;
        }
        return false;
    }
    public boolean deleteEmployeeById(int id){
        this.open();
        int deletedrow = db.delete(DatabaseHelper.EMP_TABLE_NAME,DatabaseHelper.COL_EMP_ID + "=" + id,null);
        this.close();
        if(deletedrow>0){
            return true;
        }return false;
    }
    public Employee getEmployeeDetailForLogin(String usname){
        this.open();

        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.EMP_TABLE_NAME  + " WHERE " + DatabaseHelper.COL_EMP_USERNAME + " = '" + usname + "' ;"    ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();

            int empID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_ID));
            String empFullName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_FULLNAME));
            String empUserName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_USERNAME));
            String emppass = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_PASSWORD));
            int empSalary = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_SALARY));
            int empAge =cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_EMP_AGE)); ;
            String empPosition = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_POSITION));
            String empPhone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_PHONENUMBER));
            String empEmail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMP_EMAIL));
            this.close();
            return  new Employee( empID,  empFullName,  empUserName,  emppass, empPosition,  empSalary,  empAge,empPhone,empEmail);
        }
        else{
            this.close();
            return null;
        }

    }
    public boolean searchEmployeeByUserName(String userName){
        this.open();
        Cursor cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.EMP_TABLE_NAME +" WHERE " + DatabaseHelper.COL_EMP_USERNAME + " = '" + userName  + "' ;" ,null);
        if(cursor != null && cursor.getCount() > 0 ){
            this.close();
            return true;
        }else{
            this.close();
            return false;
        }
    }

    public int getNumberofEmployees(){
        this.open();
        Cursor cursor = db.rawQuery("SELECT count(*) as TOTAL FROM " + DatabaseHelper.EMP_TABLE_NAME + " ;" , null);
        cursor.moveToFirst();
        int rows = cursor.getInt(cursor.getColumnIndex("TOTAL"));
        db.close();
        return rows;
    }

}
