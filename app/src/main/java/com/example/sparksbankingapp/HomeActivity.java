package com.example.sparksbankingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.room_persistence.MyRepository;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    public static final String KEY_FIRST_TIME_LAUNCH = "firstTimeLaunch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((Button) findViewById(R.id.viewAllCustomersBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CustomerListActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.viewAllTransfersBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TransfersTableActivity.class);
                startActivity(intent);
            }
        });

        setupDefaultCustomers();
    }

    private void setupDefaultCustomers(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean firstLaunch = preferences.getBoolean(KEY_FIRST_TIME_LAUNCH,true);
        if(firstLaunch){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_FIRST_TIME_LAUNCH,false);
            editor.apply();

            MyRepository myRepository = new MyRepository(this);
            myRepository.insertCustomer(new Customer("Remus","lupin@dada.com",100d));
            myRepository.insertCustomer(new Customer("Sirius","black@godfather.com",150d));
            myRepository.insertCustomer(new Customer("James","potter@wizard.com",165.87d));
            myRepository.insertCustomer(new Customer("Dobby","free@elf.com",106.12d));
            myRepository.insertCustomer(new Customer("Bellatrix","creepy@hair.com",100.12d));
            myRepository.insertCustomer(new Customer("Umbridge","dolores@azkaban.com",5.01d));
            myRepository.insertCustomer(new Customer("McGonagall","transfiguration@witch.com",10000.65d));
            myRepository.insertCustomer(new Customer("Albus","moonglasses@gmail.com",103.85d));
            myRepository.insertCustomer(new Customer("Draco","fullmoon@gmail.com",1099.47d));
            myRepository.insertCustomer(new Customer("Harry","ninethreefourth@yahoo.com",100.98d));
        }
    }
}
