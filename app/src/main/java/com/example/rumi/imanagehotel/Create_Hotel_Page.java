package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rumi.imanagehotel.Database.HotelDatabaseSource;

public class Create_Hotel_Page extends AppCompatActivity {

    private EditText name , location , email , number;
    private Button saveHotelButton;

    private HotelDatabaseSource hotelTBL;

    private boolean update ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__hotel__page);

        name = findViewById(R.id.hotelNameEditTExt);
        location = findViewById(R.id.hotelLocationEditTExt);
        email = findViewById(R.id.hotelemailEditTExt);
        number = findViewById(R.id.hotelNumberEditTExt);
        saveHotelButton = findViewById(R.id.donehotel);
        hotelTBL = new HotelDatabaseSource(this);

        final Hotel inhotel = Hotel.getHotel();

        if(inhotel.getHotelid() > 0){
            name.setText(inhotel.getName());
            location.setText(inhotel.getLocation());
            email.setText(inhotel.getEmail());
            number.setText(inhotel.getNumber());
            update = true;
        }
        else{
            update = false;
        }


        saveHotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name.getText().toString();
                String l = location.getText().toString();
                String e = email.getText().toString();
                String num = number.getText().toString();

                Hotel hotel = new Hotel(n,l,e,num);

                if(!update){
                    hotelTBL.insertHotel(hotel);
                }
                else{
                    hotelTBL.updateHotel(hotel,inhotel.getHotelid());
                }
                onBackPressed();

            }
        });

    }
}
