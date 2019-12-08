package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.RatingDatabaseHelper;

import java.util.ArrayList;

public class Rating_ListView_Page extends AppCompatActivity {


    private TextView dataHave;
    private ListView ratinglist;
    private RatingDatabaseHelper ratingTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating__list_view__page);

        ratinglist = findViewById(R.id.ratingListView);
        ratingTBL = new RatingDatabaseHelper(this);
        dataHave = findViewById(R.id.ratingHave);

        int cusid = getIntent().getIntExtra("id",0);

        ArrayList<Rating>ratings = ratingTBL.getAllRatingsByCusId(cusid);

        if(ratings.isEmpty()){
            dataHave.setVisibility(View.VISIBLE);
        }
        else{
            dataHave.setVisibility(View.GONE);
        }

        RatingArrayAdapter ratingArrayAdapter = new RatingArrayAdapter(this,ratings);
        ratinglist.setAdapter(ratingArrayAdapter);

    }
}
