package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.RatingDatabaseHelper;
import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

public class Create_New_Rating_Page extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button ratingSaveButton;
    private RatingDatabaseHelper ratingTBL;
    private RoomDatabaseSource roomTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__rating__page);

        ratingBar = findViewById(R.id.new_Rating_RatingBar);
        ratingSaveButton = findViewById(R.id.newRatingSaveButton);

        ratingTBL = new RatingDatabaseHelper(this);
        roomTBL = new RoomDatabaseSource(this);

        final int roomId = getIntent().getIntExtra("roomId",0);

        ratingSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Rating rating = new Rating(roomId,Customer.getLoggedInCustomer().getCusId(),ratingBar.getRating());

                boolean insert = ratingTBL.insertRating(rating);

                if(insert){
                    roomTBL.updateRoomRating(roomId,ratingTBL.getRoomRatingsByRatingTBL(roomId));
                    Toast.makeText(Create_New_Rating_Page.this,"Thanks",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Create_New_Rating_Page.this,"Failed",Toast.LENGTH_LONG).show();

                onBackPressed();
            }
        });


    }
}
