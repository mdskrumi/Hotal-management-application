package com.example.rumi.imanagehotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;
import com.example.rumi.imanagehotel.Database.RatingDatabaseHelper;
import com.example.rumi.imanagehotel.Database.RoomDatabaseSource;

import java.util.ArrayList;

public class RoomArrayAdapter extends ArrayAdapter<Room> {

    private Context context;
    private ArrayList<Room> rooms;
    private RoomDatabaseSource roomTBL;
    private CommentDatabaseHelper commentTBL;
    private RatingDatabaseHelper ratingTBL;


    public RoomArrayAdapter(@NonNull Context context,  ArrayList<Room> rooms) {
        super(context, R.layout.room_list_layout, rooms);
        this.context = context;
        this.rooms = rooms;
        roomTBL = new RoomDatabaseSource(context);
        commentTBL = new CommentDatabaseHelper(context);
        ratingTBL = new RatingDatabaseHelper(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.room_list_layout,parent,false);

        TextView roomType = convertView.findViewById(R.id.roomTypeTextView);
        TextView roomRent = convertView.findViewById(R.id.roomRentTextView);
        TextView roomRating = convertView.findViewById(R.id.roomRatingTextView);
        TextView roomdescription = convertView.findViewById(R.id.roomDescriptionTextView);
        TextView roomid = convertView.findViewById(R.id.roomIdTextView);

        roomType.setText(rooms.get(position).getRoomType());
        roomRent.setText("Cost : " + Integer.toString(rooms.get(position).getRoomRent()));
        roomRating.setText("Rating : " + Float.toString( rooms.get(position).getRating()));
        roomdescription.setText( "Description : " + rooms.get(position).getRoomDescription());
        roomid.setText("ID : "+Integer.toString(rooms.get(position).getRoomId()));

        final ImageView menuButton = convertView.findViewById(R.id.roomMenuBarButton);

            if(LoginPageActivity.getLoginStatus()== 1 || LoginPageActivity.getLoginStatus()== 3 ){
                menuButton.setVisibility(View.VISIBLE);
            }
            if(LoginPageActivity.getLoginStatus()== 2 ){
                menuButton.setVisibility(View.GONE);
            }


        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu roomMenu = new PopupMenu(context, menuButton);
                if (LoginPageActivity.getLoginStatus() == 1){
                    roomMenu.getMenuInflater().inflate(R.menu.room_menu_bar, roomMenu.getMenu());
                    roomMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.room_delete_menu) {
                            roomTBL.deleteRoomById(rooms.get(position).getRoomId());
                            commentTBL.deleteCommnentByRoomId(rooms.get(position).getRoomId());
                            ratingTBL.deleteRatingByRoomId(rooms.get(position).getRoomId());
                            rooms.remove(position);
                            notifyDataSetChanged();
                        }
                        if (item.getItemId() == R.id.room_update_menu) {
                            context.startActivity(new Intent(context, Create_New_Room.class)
                                    .putExtra("room", rooms.get(position)));
                        }
                        return false;
                    }
                });
            }
            else if(LoginPageActivity.getLoginStatus() == 3){
                    roomMenu.getMenuInflater().inflate(R.menu.room_menu_bar_for_cus, roomMenu.getMenu());
                    roomMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.give_rating_to_room_menu) {
                                context.startActivity(new Intent(context,Create_New_Rating_Page.class).putExtra("roomId",rooms.get(position).getRoomId()));
                            }
                            if (item.getItemId() == R.id.give_comment_to_room_menu) {
                                context.startActivity(new Intent(context,Create_New_Comment_Page.class).putExtra("roomId",rooms.get(position).getRoomId()));
                            }
                            return false;
                        }
                    });
                }
                roomMenu.show();
            }
        });
        return convertView;
    }


}
