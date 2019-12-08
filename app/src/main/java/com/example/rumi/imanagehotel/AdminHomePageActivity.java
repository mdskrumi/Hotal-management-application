package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;
import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;

public class AdminHomePageActivity extends AppCompatActivity {


    private CommentDatabaseHelper commentTBL;
    private int warningForLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        warningForLogOut = 0;
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
        Button whatClicked = (Button) view;

        if(whatClicked.getId() == R.id.adminpageviewemployeeButton){
            startActivity(new Intent(this,EmployeeListViewActivity.class));
        }
        if(whatClicked.getId() == R.id.adminpageviewcustomerButton){
            startActivity(new Intent(this,CustomerListViewActivity.class));
        }
        if(whatClicked.getId() == R.id.adminpageviewroomButton){
            startActivity(new Intent(this,Room_ListView_Activity.class));
        }
        if(whatClicked.getId() == R.id.adminpagesendnoticebutton){
            startActivity(new Intent(this,Notice_ListView_Activity.class));
        }
        if(whatClicked.getId() == R.id.adminpageviewcommentButton){
           Comment.setComments(commentTBL.getAllComments());
           startActivity(new Intent(this,Comment_List_VIew_Page.class));
        }
        if(whatClicked.getId() == R.id.adminpageHotelDetails){
            startActivity(new Intent(this,Hotel_Info_Page.class));
        }

    }
}
