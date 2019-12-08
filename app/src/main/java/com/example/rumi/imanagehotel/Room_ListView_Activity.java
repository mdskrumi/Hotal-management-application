package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

public class Room_ListView_Activity extends AppCompatActivity {

    private ListView roomListView;
    private RoomDatabaseSource roomTBL;
    private ImageView roomAddButton;

    private TextView datahave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room__list_view_);

        roomTBL = new RoomDatabaseSource(this);
        roomListView = findViewById(R.id.roomListView);
        roomAddButton = findViewById(R.id.addRoomButton);
        datahave = findViewById(R.id.roomHave);

        getSecured();
        UpdateRoomList();

        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Room_ListView_Activity.this,Room_View_Page.class).putExtra("room",Room.getRooms().get(position)));
            }
        });

        roomAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room_ListView_Activity.this,Create_New_Room.class));
            }
        });


    }
    @Override
    protected void onRestart() {
        super.onRestart();
        UpdateRoomList();
    }
    private void UpdateRoomList(){
        Room.setRooms(roomTBL.getAllRooms());
        if(Room.getRooms().isEmpty()){
            datahave.setVisibility(View.VISIBLE);
        }else{
            datahave.setVisibility(View.GONE);
        }
        RoomArrayAdapter roomArrayAdapter = new RoomArrayAdapter(this,Room.getRooms());
        roomListView.setAdapter(roomArrayAdapter);
    }
    private void getSecured(){
        if(LoginPageActivity.getLoginStatus()== 1){
            roomAddButton.setVisibility(View.VISIBLE);
        }
        if(LoginPageActivity.getLoginStatus()== 2 || LoginPageActivity.getLoginStatus()== 3 ){
            roomAddButton.setVisibility(View.GONE);
        }
    }
}
