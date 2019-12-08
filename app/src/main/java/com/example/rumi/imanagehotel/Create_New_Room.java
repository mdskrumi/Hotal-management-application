package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

public class Create_New_Room extends AppCompatActivity {

    private EditText newroomRent , newroomNumberOfBed , newroomDescription;
    private Spinner newroomType;
    private Button newroomSaveButton;

    private  RoomDatabaseSource roomTBL;

    private boolean wantToUpdate;

    private float roomPreRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__room);

        newroomRent = findViewById(R.id.newroomrentEditText);
        newroomNumberOfBed = findViewById(R.id.newroomnumbeofbedEditText);
        newroomDescription = findViewById(R.id.newroomDescriptionEditText);
        newroomType = findViewById(R.id.newroomtype);
        newroomSaveButton = findViewById(R.id.newroomsaveButton);

        roomTBL = new RoomDatabaseSource(this);

        final Room inroom = (Room) getIntent().getSerializableExtra("room");

        roomPreRating = 0;

        if(inroom != null){
            wantToUpdate = true;
            newroomRent.setText(Integer.toString(inroom.getRoomRent()));
            newroomNumberOfBed.setText(Integer.toString(inroom.getNumberOfBeds()));
            newroomDescription.setText(inroom.getRoomDescription());
            roomPreRating = inroom.getRating();
            newroomSaveButton.setText("update");
        }
        else{
            wantToUpdate = false;
            newroomSaveButton.setText("Save");
        }

        newroomSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sroomRent = newroomRent.getText().toString();
                String snumberOfBeds = newroomNumberOfBed.getText().toString();
                String roomDescription = newroomDescription.getText().toString();
                String roomType =  newroomType.getSelectedItem().toString();

                if(sroomRent.isEmpty()){
                    newroomRent.setError(getString(R.string.blankET));return;
                }
                if(snumberOfBeds.isEmpty()){
                    newroomNumberOfBed.setError(getString(R.string.blankET));return;
                }
                if(roomDescription.isEmpty()){
                    newroomDescription.setError(getString(R.string.blankET));return;
                }

                int roomRent = Integer.parseInt(sroomRent);
                int numberOfBeds = Integer.parseInt(snumberOfBeds);

                Room room = new Room(  numberOfBeds,  roomRent,  roomPreRating ,  roomType ,  roomDescription);

                if(wantToUpdate){
                    boolean updateRoom = roomTBL.updateRoom(room,inroom.getRoomId());
                    if(updateRoom ){
                        Toast.makeText(Create_New_Room.this,"Update Successful",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Create_New_Room.this,"Failed",Toast.LENGTH_SHORT).show();
                }else{
                    boolean insertRoom = roomTBL.insertRoom(room);
                    if(insertRoom ){
                        Toast.makeText(Create_New_Room.this,"Successful",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Create_New_Room.this,"Failed",Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });



    }
}
