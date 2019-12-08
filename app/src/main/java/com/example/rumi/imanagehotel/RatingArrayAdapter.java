package com.example.rumi.imanagehotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;

import java.util.ArrayList;

public class RatingArrayAdapter extends ArrayAdapter<Rating> {

    private Context context;
    private ArrayList<Rating>ratings;
    private CustomerDatabaseSource cusTBL;

    public RatingArrayAdapter( Context context,  ArrayList<Rating> ratings) {
        super(context, R.layout.rating_list_layout, ratings);
        this.context = context;
        this.ratings = ratings;
        cusTBL = new CustomerDatabaseSource(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.rating_list_layout,parent,false);

        TextView id = convertView.findViewById(R.id.ratingLayoutRatingID);
        TextView roomid = convertView.findViewById(R.id.ratingLayoutroomID);
        TextView cusid = convertView.findViewById(R.id.ratingLayoutcusID);
        TextView ratingf = convertView.findViewById(R.id.ratingLayoutRating);

        id.setText("Id : " + Integer.toString(ratings.get(position).getRatingId()));
        roomid.setText("Room Id : " + Integer.toString(ratings.get(position).getRatingRoomId()));
        cusid.setText("Customer UserName : " + cusTBL.getCustomerDetailById(ratings.get(position).getRatingCusId()).getCusUserName());
        ratingf.setText("Rating : " + Float.toString(ratings.get(position).getRatingRating()));

        return convertView;
    }
}
