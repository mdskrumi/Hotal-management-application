package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;
import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;
import com.example.rumi.imanagehotel.Database.HotelDatabaseSource;
import com.example.rumi.imanagehotel.Database.RatingDatabaseHelper;
import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

public class Hotel_Info_Page extends AppCompatActivity {

    private TextView hotelNameTextView,
            hotellocationTextView,
            hotelNOETextView,
            hotelNORTextView,
            hotelNOCTextView,
            hotelRatingTextView,
            hotelEmailTextView,
            hotelPhoneTextView;
    private Button UpdateHotelInfoButton;


    private CustomerDatabaseSource cusTBL;
    private EmployeeDatabaseSource empTBL;
    private RoomDatabaseSource roomTBL;
    private RatingDatabaseHelper ratingTBL;
    private HotelDatabaseSource hotelTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel__info__page);

        hotelNameTextView = findViewById(R.id.hotelNameTextView);
        hotellocationTextView = findViewById(R.id.hotellocationTextView);
        hotelNOETextView = findViewById(R.id.hotelNOETextView);
        hotelNORTextView = findViewById(R.id.hotelNORTextView);
        hotelNOCTextView = findViewById(R.id.hotelNOCTextView);
        hotelRatingTextView = findViewById(R.id.hotelRatingTextView);
        hotelEmailTextView = findViewById(R.id.hotelEmailTextView);
        hotelPhoneTextView = findViewById(R.id.hotelPhoneTextView);

        UpdateHotelInfoButton = findViewById(R.id.UpdateHotelInfoButton);


        cusTBL = new CustomerDatabaseSource(this);
        empTBL = new EmployeeDatabaseSource(this);
        roomTBL = new RoomDatabaseSource(this);
        ratingTBL = new RatingDatabaseHelper(this);
        hotelTBL = new HotelDatabaseSource(this);

        if(hotelTBL.getHotel().size()==0){
            Hotel.setHotel(new Hotel(0," " , " " , " " , " "));
        }else{
            Hotel.setHotel(hotelTBL.getHotel().get(0));
        }

        hotelNameTextView.setText(Hotel.getHotel().getName());
        hotellocationTextView.setText(Hotel.getHotel().getLocation());
        hotelEmailTextView.setText(Hotel.getHotel().getEmail());
        hotelPhoneTextView.setText(Hotel.getHotel().getNumber());

        hotelNOETextView.setText(Integer.toString(empTBL.getNumberofEmployees()));
        hotelNORTextView.setText(Integer.toString(roomTBL.getNumberofRooms()));
        hotelNOCTextView.setText(Integer.toString(cusTBL.getNumberofCustomers()));
        hotelRatingTextView.setText(Float.toString(ratingTBL.getOverAllRating()));

        if(LoginPageActivity.getLoginStatus()==1){
            UpdateHotelInfoButton.setVisibility(View.VISIBLE);
        }else{
            UpdateHotelInfoButton.setVisibility(View.GONE);
        }


        UpdateHotelInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hotel_Info_Page.this,Create_Hotel_Page.class));
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(hotelTBL.getHotel().size()==0){
            Hotel.setHotel(new Hotel(" " , " " , " " , " "));
        }else{
            Hotel.setHotel(hotelTBL.getHotel().get(0));
        }

        hotelNameTextView.setText(Hotel.getHotel().getName());
        hotellocationTextView.setText(Hotel.getHotel().getLocation());
        hotelEmailTextView.setText(Hotel.getHotel().getEmail());
        hotelPhoneTextView.setText(Hotel.getHotel().getNumber());

        hotelNOETextView.setText(Integer.toString(empTBL.getNumberofEmployees()));
        hotelNORTextView.setText(Integer.toString(roomTBL.getNumberofRooms()));
        hotelNOCTextView.setText(Integer.toString(cusTBL.getNumberofCustomers()));
        hotelRatingTextView.setText(Float.toString(ratingTBL.getOverAllRating()));

    }
}
