package com.example.rumi.imanagehotel;

public class Rating {
    private int ratingId , ratingRoomId , ratingCusId ;
    private float ratingRating;

    public Rating(int ratingRoomId, int ratingCusId, float ratingRating) {
        this.ratingRoomId = ratingRoomId;
        this.ratingCusId = ratingCusId;
        this.ratingRating = ratingRating;
    }

    public Rating(int ratingId, int ratingRoomId, int ratingCusId, float ratingRating) {
        this.ratingId = ratingId;
        this.ratingRoomId = ratingRoomId;
        this.ratingCusId = ratingCusId;
        this.ratingRating = ratingRating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getRatingRoomId() {
        return ratingRoomId;
    }

    public int getRatingCusId() {
        return ratingCusId;
    }

    public float getRatingRating() {
        return ratingRating;
    }

}
