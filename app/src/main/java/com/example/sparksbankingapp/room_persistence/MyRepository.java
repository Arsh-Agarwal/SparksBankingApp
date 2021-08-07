package com.example.sparksbankingapp.room_persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.sparksbankingapp.database_asyncs.InsertCustomerAsync;
import com.example.sparksbankingapp.database_asyncs.InsertTransferAsync;
import com.example.sparksbankingapp.database_asyncs.UpdateCustomerAsync;
import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.models.Transfer;

import java.util.List;

public class MyRepository {

    private MyDatabase myDatabase;

    public MyRepository(Context context) {
        this.myDatabase = MyDatabase.getInstance(context);
    }

    public void insertCustomer(Customer customer){
        new InsertCustomerAsync(myDatabase.getMyDao()).execute(customer);
    }

    public void insertTransfer(Transfer transfer){
        new InsertTransferAsync(myDatabase.getMyDao()).execute(transfer);
    }

    public void updateCustomer(Customer customer){
        new UpdateCustomerAsync(myDatabase.getMyDao()).execute(customer);
    }

    public LiveData<List<Customer>> getAllCustomers(){
        return myDatabase.getMyDao().getAllCustomers();
    }
}
