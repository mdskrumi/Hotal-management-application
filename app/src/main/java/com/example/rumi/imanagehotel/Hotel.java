package com.example.rumi.imanagehotel;

public class Hotel {

    private int hotelid;
    private String name,location, email , number;

    private static Hotel hotel;

    public static Hotel getHotel() {
        return hotel;
    }

    public static void setHotel(Hotel hotel) {
        Hotel.hotel = hotel;
    }

    public Hotel(int hotelid, String name, String location, String email, String number) {
        this.hotelid = hotelid;
        this.name = name;
        this.location = location;
        this.email = email;
        this.number = number;
    }

    public Hotel(String name, String location, String email, String number) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.number = number;
    }

    public int getHotelid() {
        return hotelid;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
}
