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
import com.example.rumi.imanagehotel.Database.RatingDatabaseHelper;

public class CustomerClickedActivity extends AppCompatActivity  {

    private CustomerDatabaseSource cusTBL;
    private CommentDatabaseHelper commentTBL;
    private RatingDatabaseHelper ratingTBL;
    private int cusid;

    private Button viewCustomerComment , viewCustomerrating , deleteCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_clicked);

        viewCustomerComment = findViewById(R.id.customerClickedPagecomments);
        viewCustomerrating = findViewById(R.id.customerClickedPageratings);
        deleteCustomer = findViewById(R.id.customerClickedPageDelete);


        getSecured();


        cusTBL = new CustomerDatabaseSource(this);
        commentTBL = new CommentDatabaseHelper(this);
        ratingTBL = new RatingDatabaseHelper(this);

        Intent incomingIntent = getIntent();
        cusid = incomingIntent.getIntExtra("CusID",0);

        deleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean del = cusTBL.deleteCustomer(cusid);
                if(del == true) {
                    Toast.makeText(CustomerClickedActivity.this, "Deleted ", Toast.LENGTH_SHORT).show();
                    commentTBL.deleteCommnentByCustomerId(cusid);
                    ratingTBL.deleteRatingByCustomerId(cusid);
                }
                else
                    Toast.makeText(CustomerClickedActivity.this,"Failed" + cusid ,Toast.LENGTH_SHORT).show();
                    onBackPressed();
            }
        });



        viewCustomerComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment.setComments(commentTBL.getCommentsByCusId(cusid));
                startActivity(new Intent(CustomerClickedActivity.this,Comment_List_VIew_Page.class));
            }
        });

        viewCustomerrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerClickedActivity.this,Rating_ListView_Page.class).putExtra("id",cusid));
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void getSecured(){
        if(LoginPageActivity.getLoginStatus()== 1){
            deleteCustomer.setEnabled(true);
        }
        if(LoginPageActivity.getLoginStatus()== 2 || LoginPageActivity.getLoginStatus()== 3 ){
            deleteCustomer.setEnabled(false);
        }
    }


}
