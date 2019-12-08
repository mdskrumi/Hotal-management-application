package com.example.rumi.imanagehotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;

public class Create_Employee_Account extends AppCompatActivity {

    private  EditText newEmpFullName , newEmpUSerName , newEmppass , newEmpSalary , newEmpAge , newEmpPosition , newEmpPhone , newEmpEmail , newEmpComfirmPass;
    private  Button newEmpCreateButton;

    private boolean wantToUpdate;

    private  EmployeeDatabaseSource empTBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__employee__account);

        newEmpFullName = findViewById(R.id.newEmployeeFullName);
        newEmpUSerName = findViewById(R.id.newEmployeeUserName);
        newEmppass = findViewById(R.id.newEmployeePassword);
        newEmpComfirmPass = findViewById(R.id.newEmployeeConfirmPassword);
        newEmpSalary = findViewById(R.id.newEmployeeSalary);
        newEmpAge = findViewById(R.id.newEmployeeAge);
        newEmpPosition = findViewById(R.id.newEmployeeDesignation);
        newEmpPhone = findViewById(R.id.newEmployeePhone);
        newEmpEmail = findViewById(R.id.newEmployeeEmail);
        newEmpCreateButton = findViewById(R.id.newEmployeeCreateButton);


        empTBL = new EmployeeDatabaseSource(this);

        final Employee employee = (Employee) getIntent().getSerializableExtra("employee");

        if(employee != null){
            newEmpCreateButton.setText("Update");
            wantToUpdate = true;

            newEmpFullName.setText(employee.getEmpFullName());
            newEmpUSerName.setText(employee.getEmpUsername());
            newEmpSalary.setText(String.format(Integer.toString(employee.getEmpSalary()), "%d"));
            newEmpAge.setText(String.format(Integer.toString(employee.getEmpage()), "%d"));
            newEmpPosition.setText(employee.getEmpPosition());
            newEmpPhone.setText(employee.getEmpPhone());
            newEmpEmail.setText(employee.getEmpEmail());

            getSecured();

        }
        else {
            wantToUpdate = false;
            newEmpCreateButton.setText("Save");
        }
        newEmpCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String empFullName , empUserName , emppass , empSalary , empAge , empPosition , empPhone , empEmail , empComfirmPass;

                empFullName = newEmpFullName.getText().toString();
                empUserName = newEmpUSerName.getText().toString();
                emppass = newEmppass.getText().toString();
                empComfirmPass = newEmpComfirmPass.getText().toString();
                empSalary = newEmpSalary.getText().toString();
                empAge = newEmpAge.getText().toString();
                empPosition = newEmpPosition.getText().toString();
                empPhone = newEmpPhone.getText().toString();
                empEmail = newEmpEmail.getText().toString();

                if(empFullName.isEmpty()){
                    newEmpFullName.setError(getString(R.string.blankET));return;
                }
                if(empUserName.isEmpty()){
                    newEmpUSerName.setError(getString(R.string.blankET));return;
                }
                if(emppass.isEmpty()){
                    newEmppass.setError(getString(R.string.blankET));return;
                }
                if(empComfirmPass.isEmpty()){
                    newEmpComfirmPass.setError(getString(R.string.blankET));return;
                }
                if(empSalary.isEmpty()){
                    newEmpSalary.setError(getString(R.string.blankET));return;
                }
                if(empAge.isEmpty()){
                    newEmpAge.setError(getString(R.string.blankET));return;
                }
                if(empPosition.isEmpty()){
                    newEmpPosition.setError(getString(R.string.blankET));return;
                }
                if(empPhone.isEmpty()){
                    newEmpPhone.setError(getString(R.string.blankET));return;
                }
                if(empEmail.isEmpty()){
                    newEmpEmail.setError(getString(R.string.blankET));return;
                }
                if(!emppass.equals(empComfirmPass)){
                    newEmpComfirmPass.setError("Doesn't Match");return;
                }
                if(empTBL.searchEmployeeByUserName(empUserName) && (employee == null || !employee.getEmpUsername().equals(empUserName) )){
                    newEmpUSerName.setError("Username Not Available");return;
                }

                Employee emp = new Employee(empFullName,  empUserName,  emppass, empPosition, Integer.parseInt(empSalary), Integer.parseInt(empAge),  empPhone,  empEmail);
                if(!wantToUpdate) {
                    boolean insertEmp = empTBL.insertEmployee(emp);
                    if (insertEmp) {
                        Toast.makeText(Create_Employee_Account.this, "Intertion Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Create_Employee_Account.this, "Failed To Insert", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    boolean updateEmp = empTBL.updateEmployee(emp , employee.getEmpid());
                    if (updateEmp) {
                        Toast.makeText(Create_Employee_Account.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Create_Employee_Account.this, "Failed To Update", Toast.LENGTH_SHORT).show();
                    }
                }
                onBackPressed();
            }
        });

    }


    private void getSecured(){
        if(LoginPageActivity.getLoginStatus()== 1){
            newEmpUSerName.setEnabled(true);
            newEmpSalary.setEnabled(true);
            newEmpPosition.setEnabled(true);
        }
        if(LoginPageActivity.getLoginStatus()== 2 || LoginPageActivity.getLoginStatus()== 3 ){
            newEmpUSerName.setEnabled(false);
            newEmpSalary.setEnabled(false);
            newEmpPosition.setEnabled(false);
        }
    }



}
