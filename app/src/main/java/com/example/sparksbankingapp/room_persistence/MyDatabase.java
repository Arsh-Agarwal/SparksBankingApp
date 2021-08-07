package com.example.sparksbankingapp.room_persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.models.Transfer;

@Database(entities = {Customer.class, Transfer.class} , version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static final String TAG = "MyDatabase";
    private static final String DATABASE_NAME = "my_database";

    private static MyDatabase instance;

    public static MyDatabase getInstance(final Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class,DATABASE_NAME).build();
        }
        return instance;
    }

    public abstract MyDao getMyDao();
}
