package com.example.rumi.imanagehotel;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private int roomId ,numberOfBeds , roomRent;
    private float rating;
    private String roomDescription , roomType;
    static ArrayList<Room> rooms;

    public Room(int roomId,int numberOfBeds, int roomRent, float rating, String roomType , String roomDescription) {
        this.roomId = roomId;
        this.numberOfBeds = numberOfBeds;
        this.roomRent = roomRent;
        this.rating = rating;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
    }

    public Room( int numberOfBeds, int roomRent, float rating, String roomType , String roomDescription) {
        this.numberOfBeds = numberOfBeds;
        this.roomRent = roomRent;
        this.rating = rating;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomId() {
        return roomId;
    }


    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public int getRoomRent() {
        return roomRent;
    }

    public float getRating() {
        return rating;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(ArrayList<Room> rooms) {
        Room.rooms = rooms;
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", rating=" + rating +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
