package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;
import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

public class Room_View_Page extends AppCompatActivity {

    private TextView RoomViewPageRoomType,
            RoomViewPageDescription,
            RoomViewPageNumberOfBeds,
            RoomViewPageCost,
            RoomViewPageRating;
    private Button viewCommentButton;

    private CommentDatabaseHelper commentTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room__view__page);

        commentTBL = new CommentDatabaseHelper(this);

        RoomViewPageRoomType = findViewById(R.id.RoomViewPageRoomType);
        RoomViewPageDescription = findViewById(R.id.RoomViewPageDescription);
        RoomViewPageNumberOfBeds = findViewById(R.id.RoomViewPageNumberOfBeds);
        RoomViewPageCost = findViewById(R.id.RoomViewPageCost);
        RoomViewPageRating = findViewById(R.id.RoomViewPageRating);
        viewCommentButton = findViewById(R.id.RoomViewPageViewComment);

        final Room inroom = (Room) getIntent().getSerializableExtra("room");

        RoomViewPageRoomType.setText(inroom.getRoomType());
        RoomViewPageDescription.setText(inroom.getRoomDescription());
        RoomViewPageNumberOfBeds.setText(Integer.toString(inroom.getNumberOfBeds()));
        RoomViewPageCost.setText(Integer.toString(inroom.getRoomRent()));
        RoomViewPageRating.setText(Float.toString(inroom.getRating()));


        viewCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment.setComments(commentTBL.getCommentsByRoomId(inroom.getRoomId()));
                startActivity(new Intent(Room_View_Page.this,Comment_List_VIew_Page.class));
            }
        });


    }
}
