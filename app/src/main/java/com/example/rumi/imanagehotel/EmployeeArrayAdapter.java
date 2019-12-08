package com.example.rumi.imanagehotel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.EmployeeDatabaseSource;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeArrayAdapter extends ArrayAdapter<Employee>  {

    private Context context;
    private ArrayList<Employee>employees;
    private EmployeeDatabaseSource empTBL;

    public EmployeeArrayAdapter(@NonNull Context context, ArrayList<Employee> employees) {
        super(context, R.layout.employee_list_layout, employees);
        this.context = context;
        this.employees = employees;
        empTBL = new EmployeeDatabaseSource(context);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.employee_list_layout,parent,false);

        TextView empFullName = convertView.findViewById(R.id.employeefullnameTV);
        TextView empEmail = convertView.findViewById(R.id.employeeemailTV);
        TextView empPhone = convertView.findViewById(R.id.employeephnTV);
        TextView empPosition = convertView.findViewById(R.id.employeepositionTV);
        TextView empSalary = convertView.findViewById(R.id.employeesalaryTV);
        TextView empAge = convertView.findViewById(R.id.employeeageTV);

        empFullName.setText("Full Name : " + employees.get(position).getEmpFullName());
        empEmail.setText(employees.get(position).getEmpEmail());
        empPhone.setText(employees.get(position).getEmpPhone());
        empPosition.setText(employees.get(position).getEmpPosition());
        empSalary.setText("Salary : " + Integer.toString(employees.get(position).getEmpSalary()));
        empAge.setText("Age : " +Integer.toString(employees.get(position).getEmpage()));

        final ImageView empMenuButton = convertView.findViewById(R.id.employeeOptionsButton);

        empMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu empMenu = new PopupMenu(context , empMenuButton);
                empMenu.getMenuInflater().inflate(R.menu.employee_options_menu,empMenu.getMenu());

                empMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.employee_delete_menu ){
                            empTBL.deleteEmployeeById(employees.get(position).getEmpid());
                            employees.remove(position);
                            notifyDataSetChanged();
                        }
                        if(item.getItemId() == R.id.employee_update_menu){
                            context.startActivity(new Intent(context,Create_Employee_Account.class)
                                    .putExtra("employee",employees.get(position)));
                        }
                        return false;
                    }
                });


                empMenu.show();

            }
        });


        return convertView;
    }
}
