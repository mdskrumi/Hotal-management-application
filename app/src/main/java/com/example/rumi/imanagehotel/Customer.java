package com.example.rumi.imanagehotel;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private int cusId;
    private String cusFullName;
    private String cusUserName;
    private String cusPassword;
    private String cusPhoneNumber;
    private String cusEmail;

    public static ArrayList<Customer> customers;

    public static Customer LoggedInCustomer;

    public static Customer getLoggedInCustomer() {
        return LoggedInCustomer;
    }

    public static void setLoggedInCustomer(Customer loggedInCustomer) {
        LoggedInCustomer = loggedInCustomer;
    }

    public Customer(int cusId, String cusFullName, String cusUserName, String cusPassword, String cusPhoneNumber, String cusEmail) {
        this.cusId = cusId;
        this.cusFullName = cusFullName;
        this.cusUserName = cusUserName;
        this.cusPassword = cusPassword;
        this.cusPhoneNumber = cusPhoneNumber;
        this.cusEmail = cusEmail;

    }

    public Customer(String cusFullName, String cusUserName, String cusPassword, String cusPhoneNumber, String cusEmail) {
        this.cusFullName = cusFullName;
        this.cusUserName = cusUserName;
        this.cusPassword = cusPassword;
        this.cusPhoneNumber = cusPhoneNumber;
        this.cusEmail = cusEmail;

    }

    public int getCusId() {
        return cusId;
    }

    public String getCusFullName() {
        return cusFullName;
    }

    public String getCusUserName() {
        return cusUserName;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public String getCusPhoneNumber() {
        return cusPhoneNumber;
    }

    public String getCusEmail() {
        return cusEmail;
    }


    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Customer.customers = customers;
    }
}
