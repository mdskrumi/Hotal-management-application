package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;

public class EmployeeListViewActivity extends AppCompatActivity{

    private ListView empListView;
    private ImageView addButton;
    private EmployeeDatabaseSource empTBL;

    private TextView dataHave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_view);
        empListView = findViewById(R.id.employeeListView);
        addButton = findViewById(R.id.addEmployeeButton);
        empTBL = new EmployeeDatabaseSource(this);

        dataHave = findViewById(R.id.employeeHave);

        updateEmployeeList();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeListViewActivity.this,Create_Employee_Account.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateEmployeeList();
    }

    private void updateEmployeeList(){
        Employee.setEmployess(empTBL.getAllEmployeeList());

        if(Employee.getEmployess().isEmpty()){
            dataHave.setVisibility(View.VISIBLE);
        }else{
            dataHave.setVisibility(View.GONE);
        }

        EmployeeArrayAdapter employeeArrayAdapter = new EmployeeArrayAdapter(this,Employee.getEmployess());
        empListView.setAdapter(employeeArrayAdapter);
    }

}
