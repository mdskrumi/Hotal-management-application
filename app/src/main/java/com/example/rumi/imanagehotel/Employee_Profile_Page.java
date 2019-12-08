package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;

public class Employee_Profile_Page extends AppCompatActivity {

    private Employee employee;

    private TextView empProfilePageFullName,
            empProfilePageUserName,
            empProfilePageAge,
            empProfilePagePhone,
            empProfilePageEmail,
            empProfilePageDesignation,
            empProfilePageSalary;

    private Button empProfilePageUpdateButton;

    EmployeeDatabaseSource empTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__profile__page);

        empProfilePageFullName = findViewById(R.id.employeeProfilePageFullName);
        empProfilePageUserName = findViewById(R.id.employeeProfilePageUserName);
        empProfilePageAge = findViewById(R.id.employeeProfilePageAge);
        empProfilePagePhone = findViewById(R.id.employeeProfilePagePhone);
        empProfilePageEmail = findViewById(R.id.employeeProfilePageEmail);
        empProfilePageDesignation = findViewById(R.id.employeeProfilePageDesignation);
        empProfilePageSalary = findViewById(R.id.employeeProfilePageSalary);

        empProfilePageUpdateButton = findViewById(R.id.employeeProfilePageUpdateButton);

        empTBL = new EmployeeDatabaseSource(this);
        updatePage();
        empProfilePageUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Employee_Profile_Page.this,Create_Employee_Account.class)
                        .putExtra("employee",employee));
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        updatePage();
    }
    private void updatePage(){
        employee = empTBL.getEmployeeDetailForLogin(getIntent().getStringExtra("userName"));

        empProfilePageFullName.setText(employee.getEmpFullName());
        empProfilePageUserName.setText(employee.getEmpUsername());
        empProfilePageAge.setText(Integer.toString(employee.getEmpage()));
        empProfilePagePhone.setText(employee.getEmpPhone());
        empProfilePageEmail.setText(employee.getEmpEmail());
        empProfilePageDesignation.setText(employee.getEmpPosition());
        empProfilePageSalary.setText(Integer.toString(employee.getEmpSalary()));
    }
}
