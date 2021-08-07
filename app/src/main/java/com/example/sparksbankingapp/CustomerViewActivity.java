package com.example.sparksbankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sparksbankingapp.models.Customer;

import java.util.Locale;

public class CustomerViewActivity extends AppCompatActivity {

    private static final String TAG = "CustomerViewActivity";

    //consts and keys
    public static final String KEY_CURRENT_CUSTOMER = "currentCustomer";
    public static final int TRANSFER_ACTIVITY_REQUEST_CODE = 4578;

    //vars
    private Customer primeCustomer;

    //widgets
    private TextView name,email,currentBalance,transferMoneyBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        primeCustomer = getIntent().getParcelableExtra(KEY_CURRENT_CUSTOMER);

        getSupportActionBar().setTitle("Customer View");
        bindViews();
        initViews();
    }

    private void bindViews(){
        name = findViewById(R.id.customerName);
        email = findViewById(R.id.customerEmail);
        currentBalance = findViewById(R.id.currentBalance);
        transferMoneyBtn = findViewById(R.id.transferMoneyBtn);
        transferMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerViewActivity.this,TransferActivity.class);
                intent.putExtra(TransferActivity.KEY_TRANSFER_FROM_CUSTOMER,primeCustomer);
                startActivityForResult(intent,TRANSFER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void initViews(){
        name.setText(primeCustomer.getCustomerName());
        email.setText(primeCustomer.getCustomerEmail());
        currentBalance.setText(String.format(Locale.US,"%.2f",primeCustomer.getCurrenctBalance()));
    }

    //activity overrides
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TRANSFER_ACTIVITY_REQUEST_CODE:{
                if(resultCode == RESULT_OK){
                    exitActivity();
                }
                break;
            }
        }
    }

    //utils
    private void exitActivity(){
        setResult(RESULT_OK);
        super.finish();
    }
}
