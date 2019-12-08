package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Customer_Profile_Page extends AppCompatActivity {

    private TextView cusProfilePageFullName,
            cusProfilePageUserName,
            cusProfilePagePhone,
            cusProfilePageEmail;
    private  Button cusProfilePageUpdateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__profile__page);

        cusProfilePageFullName = findViewById(R.id.customerProfilePageFullName);
        cusProfilePageUserName = findViewById(R.id.customerProfilePageUserName);
        cusProfilePagePhone = findViewById(R.id.customerProfilePagePhone);
        cusProfilePageEmail = findViewById(R.id.customerProfilePageEmail);

        cusProfilePageUpdateButton = findViewById(R.id.customerProfilePageUpdateButton);

        Update();

        cusProfilePageUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Customer_Profile_Page.this,CreateAccountActivity.class).putExtra("customer",Customer.getLoggedInCustomer()));
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Update();
    }

    private void Update(){
        cusProfilePageFullName.setText(Customer.getLoggedInCustomer().getCusFullName());
        cusProfilePageUserName.setText(Customer.getLoggedInCustomer().getCusUserName());
        cusProfilePagePhone.setText(Customer.getLoggedInCustomer().getCusPhoneNumber());
        cusProfilePageEmail.setText(Customer.getLoggedInCustomer().getCusEmail());
    }
}
