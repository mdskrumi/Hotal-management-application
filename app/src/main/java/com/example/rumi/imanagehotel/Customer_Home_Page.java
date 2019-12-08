package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CommentDatabaseHelper;

public class Customer_Home_Page extends AppCompatActivity {

    private CommentDatabaseHelper commentTBL;
    private int warningForLogOut ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__home__page);
        commentTBL = new CommentDatabaseHelper(this);
        warningForLogOut = 0;
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
        if(button.getId() == R.id.custormerPageMyProfileButton){
            startActivity(new Intent(Customer_Home_Page.this,Customer_Profile_Page.class));
        }
        if(button.getId() == R.id.custormerPageviewroomButton){
            startActivity(new Intent(Customer_Home_Page.this,Room_ListView_Activity.class));
        }
        if(button.getId() == R.id.custormerPageViewnoticebutton){
            startActivity(new Intent(this , Notice_ListView_Activity.class));
        }
        if(button.getId() == R.id.custormerPageviewcommentButton){
            Comment.setComments(commentTBL.getCommentsByCusId(Customer.getLoggedInCustomer().getCusId()));
            startActivity(new Intent(this,Comment_List_VIew_Page.class));
        }
        if(button.getId() == R.id.custormerPageHotelInformationButton){
            startActivity(new Intent(this,Hotel_Info_Page.class));
        }
    }
}
