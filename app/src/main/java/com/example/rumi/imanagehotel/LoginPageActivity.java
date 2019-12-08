package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;
import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;

import java.util.List;

public class LoginPageActivity extends AppCompatActivity {


    // LOGIN STATUS ADMIN    -> 1
    // LOGIN STATUS EMPLOYEE -> 2
    // LOGIN STATUS CUSTOMER -> 3

    private EditText username , password;
    private Spinner userType;

    private static int loginStatus = 0;

    private final String ADMIN_NAME = "admin";
    private final String ADMIN_PASS = "admin";


    private EmployeeDatabaseSource empTBL;
    private CustomerDatabaseSource cusTBL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.usernameloginET);
        password = findViewById(R.id.userpassloginET);
        userType = findViewById(R.id.usertypeSPR);

        empTBL = new EmployeeDatabaseSource(this);
        cusTBL = new CustomerDatabaseSource(this);

    }

    public void createAccountClicked(View view) {
        Intent intent = new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }

    public void seeCustomers(View view) {
        loginStatus = 2;
        startActivity(new Intent(this,Room_ListView_Activity.class));
    }


    public void loginClicked(View view) {

        //********* ADMIN LOGIN CHECK

        if(userType.getSelectedItem().toString().equals("Admin") && username.getText().toString().equals(ADMIN_NAME) && password.getText().toString().equals(ADMIN_PASS)){
            loginStatus = 1;
            startActivity(new Intent(this,AdminHomePageActivity.class));
            Toast.makeText(this,"LOGGED IN AS ADMIN" , Toast.LENGTH_LONG).show();
        }
        else{
            if(!username.getText().toString().equals(ADMIN_NAME) && userType.getSelectedItem().toString().equals("Admin"))
                Toast.makeText(this,"Wrong User Name" , Toast.LENGTH_SHORT).show();
            else if(userType.getSelectedItem().toString().equals("Admin"))
                Toast.makeText(this,"Wrong Password" , Toast.LENGTH_SHORT).show();
        }

        //*********** EMPLOYEE LOGIN CHECK

        if(userType.getSelectedItem().toString().equals("Employee")){

            String emploginName = username.getText().toString();
            String emploginPass = password.getText().toString();

            Employee employee = empTBL.getEmployeeDetailForLogin(emploginName);

            if(employee!=null && employee.getEmpPassword().equals(emploginPass)){                   //FOR EMPLOYEE SUCCESSFUL LOGIN
                loginStatus = 2;
                startActivity(new Intent(this,Employee_Home_Page.class).putExtra("userName",emploginName));
                Toast.makeText(this,"LOGGED IN AS EMPLOYEE" , Toast.LENGTH_LONG).show();

            }
            else if(employee!=null && employee.getEmpPassword() != emploginPass){
                Toast.makeText(this,"Wrong Password" , Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Wrong User Name" , Toast.LENGTH_SHORT).show();
            }

        }

        if(userType.getSelectedItem().toString().equals("Customer")){

            String cusloginName = username.getText().toString();
            String cusloginPass = password.getText().toString();

            Customer customer = cusTBL.getCustomerDetailForLogin(cusloginName);

            if(customer!=null && customer.getCusPassword().equals(cusloginPass)){                   //FOR CUSTOMER SUCCESSFUL LOGIN
                loginStatus = 3;
                Toast.makeText(this,"LOGGED IN AS CUSTOMER" , Toast.LENGTH_LONG).show();
                Customer.setLoggedInCustomer(customer);
                startActivity(new Intent(this,Customer_Home_Page.class));

            }
            else if(customer!=null && customer.getCusPassword() != cusloginPass){
                Toast.makeText(this,"Wrong Password" , Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Wrong User Name" , Toast.LENGTH_SHORT).show();
            }

        }
    }

    public static int getLoginStatus() {
        return loginStatus;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loginStatus = 0;
        username.setText("");
        password.setText("");
    }
}




















