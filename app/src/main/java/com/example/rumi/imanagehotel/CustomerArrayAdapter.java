package com.example.rumi.imanagehotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerArrayAdapter extends ArrayAdapter<Customer> {


    private Context context;
    private ArrayList<Customer>customers;

    public CustomerArrayAdapter(@NonNull Context context, ArrayList<Customer> objects) {
        super(context, R.layout.customer_list_layout, objects);
        this.context = context;
        this.customers = objects;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.customer_list_layout,parent,false);

        TextView id = convertView.findViewById(R.id.cusidtv);
        TextView fullname = convertView.findViewById(R.id.cusfullnameTV);
        TextView phone = convertView.findViewById(R.id.cusphnTV);
        TextView email = convertView.findViewById(R.id.cusemailTV);

        id.setText("ID: " + customers.get(position).getCusId());
        fullname.setText("Full Name : " + customers.get(position).getCusFullName());
        phone.setText(customers.get(position).getCusPhoneNumber());
        email.setText(customers.get(position).getCusEmail());

        return convertView;
    }
}
