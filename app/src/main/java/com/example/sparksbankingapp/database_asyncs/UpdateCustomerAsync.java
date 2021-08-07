package com.example.sparksbankingapp.database_asyncs;

import android.os.AsyncTask;

import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.room_persistence.MyDao;

public class UpdateCustomerAsync extends AsyncTask<Customer,Void,Void> {

    private static final String TAG = "UpdateCustomerAsync";

    private MyDao myDao;

    public UpdateCustomerAsync(MyDao myDao) {
        this.myDao = myDao;
    }

    @Override
    protected Void doInBackground(Customer... customers) {
        myDao.updateCustomer(customers);
        return null;
    }
}
