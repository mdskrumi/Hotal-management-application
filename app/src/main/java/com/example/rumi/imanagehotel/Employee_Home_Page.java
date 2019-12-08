package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;

public class Employee_Home_Page extends AppCompatActivity {

    private String userName;
    private CommentDatabaseHelper commentTBL;
    private int warningForLogOut ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__home__page);
        warningForLogOut = 0;
        userName = getIntent().getStringExtra("userName");
        commentTBL = new CommentDatabaseHelper(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        warningForLogOut = 0;
    }

    @Override
    public void onBackPressed() {
        if(warningForLogOut == 0 ) {
            Toast.makeText(this, "PRESS BACK BUTTON AGAIN TO LOG OUT", Toast.LENGTH_SHORT).show();
            warningForLogOut = 1;
            return;
        }
        super.onBackPressed();
    }

    public void anybuttonClicked(View view) {

        Button button = (Button) view;

        if(button.getId() == R.id.employeePageMyProfileButton){
            startActivity(new Intent(this , Employee_Profile_Page.class).putExtra("userName",userName));
        }
        if(button.getId() == R.id.employeePageviewcustomerButton){
            startActivity(new Intent(this , CustomerListViewActivity.class));
        }
        if(button.getId() == R.id.employeePageviewroomButton){
            startActivity(new Intent(this , Room_ListView_Activity.class));
        }
        if(button.getId() == R.id.employeePageViewnoticebutton){
            startActivity(new Intent(this , Notice_ListView_Activity.class));
        }
        if(button.getId() == R.id.employeePageHotelInfoButton){
            startActivity(new Intent(this,Hotel_Info_Page.class));
        }
        if(button.getId() == R.id.employeePageviewcommentButton){
            Comment.setComments(commentTBL.getAllComments());
            startActivity(new Intent(this,Comment_List_VIew_Page.class));
        }

    }
}
