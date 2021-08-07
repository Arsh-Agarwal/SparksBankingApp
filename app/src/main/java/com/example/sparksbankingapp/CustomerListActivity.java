package com.example.sparksbankingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.sparksbankingapp.adapters.CustomerRecyclerAdapter;
import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.room_persistence.MyRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements RecyclerInterface{

    private static final String TAG = "MainActivity";

    //widgets
    private RecyclerView recyclerView;

    //vars
    private CustomerRecyclerAdapter adapter;
    private ArrayList<Customer> customers = new ArrayList<>();
    private MyRepository myRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        getSupportActionBar().setTitle("Customer List");
        bindViews();
        setupData();
    }

    private void bindViews(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomerRecyclerAdapter(this,customers,this);
        recyclerView.setAdapter(adapter);
    }

    private void setupData(){
        myRepository = new MyRepository(this);
        myRepository.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> mCustomers) {
                customers.clear();
                customers.addAll(mCustomers);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //interface methods
    @Override
    public void itemClicked(int position) {
        Intent intent = new Intent(CustomerListActivity.this,CustomerViewActivity.class);
        intent.putExtra(CustomerViewActivity.KEY_CURRENT_CUSTOMER,customers.get(position));
        startActivity(intent);
    }
}