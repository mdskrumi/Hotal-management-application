package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;

public class CreateAccountActivity extends AppCompatActivity {


    private CustomerDatabaseSource cusTable;

    private EditText usernameET , passwordET , confirmpassET , fullnameET , phonenumberET , emailET;

    private boolean wantToUpdate;

    private Button createButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        fullnameET = findViewById(R.id.newCusfullnameET);
        usernameET = findViewById(R.id.newCusnameET);
        passwordET = findViewById(R.id.newCuspassET);
        confirmpassET = findViewById(R.id.newCusconfirmpasswordET);
        phonenumberET = findViewById(R.id.newCusphonenumberET);
        emailET = findViewById(R.id.newCusemailET);

        createButton = findViewById(R.id.newCusregisterButton);

        cusTable = new CustomerDatabaseSource(this);

        Customer customer = (Customer) getIntent().getSerializableExtra("customer");

        if(customer != null){
            wantToUpdate = true;
            Customer.setLoggedInCustomer(customer);
            fullnameET.setText(customer.getCusFullName());
            usernameET.setText(customer.getCusUserName());
            phonenumberET.setText(customer.getCusPhoneNumber());
            emailET.setText(customer.getCusEmail());
            usernameET.setEnabled(false);
            createButton.setText("Change");
        }
        else{
            wantToUpdate = false;
            usernameET.setEnabled(true);
            createButton.setText("Create Account");
        }

    }

    public void createAccountClicked(View view) {

        String fullName = fullnameET.getText().toString();
        String userName = usernameET.getText().toString();
        String pass = passwordET.getText().toString();
        String confirmpass = confirmpassET.getText().toString();
        String phnNumber = phonenumberET.getText().toString();
        String email = emailET.getText().toString();

        if(fullName.isEmpty()){
            fullnameET.setError(getString(R.string.blankET));return;
        }
        if(userName.isEmpty()){
            usernameET.setError(getString(R.string.blankET));return;
        }
        if(pass.isEmpty()){
            passwordET.setError(getString(R.string.blankET));return;
        }
        if(confirmpass.isEmpty()){
            confirmpassET.setError(getString(R.string.blankET));return;
        }
        if(phnNumber.isEmpty()){
            phonenumberET.setError(getString(R.string.blankET));return;
        }
        if(email.isEmpty()){
            emailET.setError(getString(R.string.blankET));return;
        }

        if(!pass.equals(confirmpass)){
            confirmpassET.setError("Password Doesnt Match"); return;
        }
        if(wantToUpdate!= true && cusTable.searchDuplicateUserName(userName) ){
            usernameET.setError("User Name Already Exists"); return;
        }

        Customer cus = new Customer( fullName, userName, pass, phnNumber, email);

        if(wantToUpdate){
            boolean in = cusTable.updateCustomerByID(cus,Customer.getLoggedInCustomer().getCusId());
            if(in==true){
                Toast.makeText(this,"Account Updated Successfully" , Toast.LENGTH_SHORT).show();
                Customer.setLoggedInCustomer(cusTable.getCustomerDetailForLogin(userName));
            }else{
                Toast.makeText(this,"Failed To Update Account" , Toast.LENGTH_SHORT).show();
            }
        }
        else{
            boolean in = cusTable.insertCustomer(cus);
            if(in==true){
                Toast.makeText(this,"Account Created Successfully" , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Failed To Create Account" , Toast.LENGTH_SHORT).show();
            }
        }
        onBackPressed();
    }
}
