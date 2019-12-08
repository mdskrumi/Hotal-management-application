package com.example.rumi.imanagehotel;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee implements Serializable {

    private int empid , empSalary , empage ;
    private String empFullName , empPosition , empUsername , empPassword , empPhone , empEmail;


    private static ArrayList<Employee>employess;
    private static ArrayList<String>positions;

    private static Employee loginedEmployee;


    public Employee(int empid, String empFullName, String empUsername, String empPassword, String empPosition, int empSalary, int empage, String empPhone, String empEmail) {
        this.empid = empid;
        this.empSalary = empSalary;
        this.empage = empage;
        this.empFullName = empFullName;
        this.empPosition = empPosition;
        this.empUsername = empUsername;
        this.empPassword = empPassword;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
    }
    public Employee(String empFullName, String empUsername, String empPassword, String empPosition, int empSalary, int empage, String empPhone, String empEmail) {
        this.empSalary = empSalary;
        this.empage = empage;
        this.empFullName = empFullName;
        this.empPosition = empPosition;
        this.empUsername = empUsername;
        this.empPassword = empPassword;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
    }

    public int getEmpid() {
        return empid;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public int getEmpage() {
        return empage;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public String getEmpUsername() {
        return empUsername;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public String getEmpEmail() {
        return empEmail;
    }


    public static ArrayList<Employee> getEmployess() {
        return employess;
    }

    public static void setEmployess(ArrayList<Employee> employess) {
        Employee.employess = employess;
    }


    public static ArrayList<String> getPositions() {
        return positions;
    }

    public static void setPositions(ArrayList<String> positions) {
        Employee.positions = positions;
    }



}
