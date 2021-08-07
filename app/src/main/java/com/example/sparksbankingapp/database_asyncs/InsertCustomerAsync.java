package com.example.sparksbankingapp.database_asyncs;

import android.os.AsyncTask;

import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.room_persistence.MyDao;

public class InsertCustomerAsync extends AsyncTask<Customer,Void,Void> {

    private static final String TAG = "InsertCustomerAsync";

    private MyDao myDao;

    public InsertCustomerAsync(MyDao myDao) {
        this.myDao = myDao;
    }

    @Override
    protected Void doInBackground(Customer... customers) {
        myDao.insertCustomer(customers);
        return null;
    }
}
