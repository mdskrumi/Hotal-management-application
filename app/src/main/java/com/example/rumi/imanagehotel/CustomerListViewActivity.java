package com.example.rumi.imanagehotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rumi.imanagehotel.Database.CustomerDatabaseSource;

public class CustomerListViewActivity extends AppCompatActivity {


    private ListView customerlistview;
    private CustomerDatabaseSource cusTBL;

    private TextView dataHave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);

        customerlistview = findViewById(R.id.customerLV);
        cusTBL = new CustomerDatabaseSource(this);

        dataHave = findViewById(R.id.customerHave);

        updateList();

        customerlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer cus = Customer.getCustomers().get(position);
                Intent intent = new Intent(CustomerListViewActivity.this , CustomerClickedActivity.class);
                intent.putExtra("CusID",cus.getCusId());
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        updateList();
    }


    private void updateList(){
        Customer.setCustomers(cusTBL.getCustomerList());
        if(Customer.getCustomers().isEmpty()){
            dataHave.setVisibility(View.VISIBLE);
        }else{
            dataHave.setVisibility(View.GONE);
        }
        CustomerArrayAdapter customerArrayAdapter = new CustomerArrayAdapter(this,Customer.getCustomers());
        customerlistview.setAdapter(customerArrayAdapter);
    }

}
