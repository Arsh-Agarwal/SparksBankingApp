package com.example.sparksbankingapp.room_persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.models.Transfer;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    long[] insertCustomer(Customer... customers);

    @Update
    int updateCustomer(Customer... customers);

    @Insert
    long[] insertTransfer(Transfer... transfers);

    @Query("SELECT * FROM customers ORDER BY name ASC")
    LiveData<List<Customer>> getAllCustomers();

    @Query("SELECT * FROM transfers ORDER BY id DESC")
    LiveData<List<Transfer>> getAllTransfers();
}
